package com.tingyu.venus.netty.socket.handler;

import com.google.protobuf.Any;
import com.google.protobuf.util.JsonFormat;
import com.tingyu.venus.common.enums.MessageStatus;
import com.tingyu.venus.common.enums.MessageType;
import com.tingyu.venus.netty.protobuf.ChatNotice;
import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.socket.processor.AbstractProtobufMessageHandler;
import com.tingyu.venus.netty.util.MessageUtil;
import com.tingyu.venus.netty.util.NettyChannelUtil;
import com.tingyu.venus.service.ContactService;
import com.tingyu.venus.service.GroupChatService;
import com.tingyu.venus.service.MessageService;
import com.tingyu.venus.vo.MessageVo;
import com.tingyu.venus.vo.UserVo;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/21 10:51
 * @Version venus-server
 */
@Slf4j
@Service
public class ChatMessageHandler extends AbstractProtobufMessageHandler {


    @Autowired
    private ContactService contactService;

    @Autowired
    private GroupChatService groupChatService;

    @Autowired
    private MessageService messageService;

    @Override
    protected void handleInternal(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg) {
        if (msg.getMessageType().equals(TransportMessageOuterClass.MessageType.CHAT_MSG_NOTICE)) {

            try {
                Any messageBody = msg.getMessageBody();
                ChatNotice.ChatNoticeMessage chatMessage = messageBody.unpack(ChatNotice.ChatNoticeMessage.class);

                //判断是群聊还是单聊
                String groupId = chatMessage.getGroupId();
                if (groupId == null || StringUtils.isEmpty(groupId)) {
                    //单聊
                    sendSingleChat(ctx, msg.getMessageId(), chatMessage);
                } else {
                    //群聊
                    sendGroupChat(ctx, groupId, msg.getMessageId(), chatMessage);

                }


            } catch (Exception e) {
                e.printStackTrace();
                log.error("消息解析异常");
            }

        } else {
            super.handler.handle(ctx, msg);
        }
    }

    /**
     * 发送单聊
     *
     * @param ctx
     * @param chatMessage
     */
    private void sendSingleChat(ChannelHandlerContext ctx, Long msgId, ChatNotice.ChatNoticeMessage chatMessage) {


        //获取消息的发送者
        String fromId = chatMessage.getFromId();
        //获取消息接收者
        String toId = chatMessage.getToId();

        //判断对方是否为联系人，如果是则转发，如果不是则提示，她不是好友

        boolean isContact = contactService.isContact(fromId, toId);

        if (!isContact) {
            log.info("对方不是你好友");
            MessageUtil.sendSocketMsg(ctx, TransportMessageOuterClass.MessageType.MESSAGE_RECEIVED_ACK, null, null, null);
            return;
        }

        //获取消息接收者通道信息
        ChannelHandlerContext toChannelContext = NettyChannelUtil.USER_ID_CHANNEL_GROUP.get(toId);                //
        if (null != toChannelContext) {

            MessageUtil.sendWebSocketMsg(toChannelContext, TransportMessageOuterClass.MessageType.CHAT_MSG_NOTICE, null, null, chatMessage);
        } else {
            //对方不在线
            log.info("联系人不在线");
            //发送离线消息
            sendOfflineMessage(chatMessage, toId, msgId);


        }

        //通知消息发送者，消息已经收到
        MessageUtil.sendSocketMsg(ctx, TransportMessageOuterClass.MessageType.MESSAGE_RECEIVED_ACK, null, null, null);


    }

    /**
     * 发送群消息
     */
    private void sendGroupChat(ChannelHandlerContext ctx, String groupId, Long msgId, ChatNotice.ChatNoticeMessage chatNoticeMessage) {

        //根据群ID查询所有群成员
        List<UserVo> userVos = groupChatService.getAllMembersByGroupId(groupId);
        //转发当前群消息
        if (userVos != null && userVos.size() > 0) {


            for (UserVo user : userVos) {

                String phone = user.getPhone();

                //本人发送的消息，不需要再转发
                if (phone.equals(chatNoticeMessage.getFromId())) {
                    continue;
                }

                ChannelHandlerContext handlerContext = NettyChannelUtil.USER_ID_CHANNEL_GROUP.get(phone);

                if (handlerContext != null) {
                    //转发群消息
                    MessageUtil.sendWebSocketMsg(handlerContext, TransportMessageOuterClass.MessageType.CHAT_MSG_NOTICE, null, null, chatNoticeMessage);

                } else {
                    log.info("{}该成员不在线", phone);
                    //发送离线消息
                    sendOfflineMessage(chatNoticeMessage, phone, msgId);
                }


            }
        }
        //告诉客户端消息已经接收
        MessageUtil.sendSocketMsg(ctx, TransportMessageOuterClass.MessageType.MESSAGE_RECEIVED_ACK, null, null, null);


    }

    /**
     * 发送离线消息
     *
     * @param msg
     */
    private void sendOfflineMessage(ChatNotice.ChatNoticeMessage msg, String toId, Long msgId) {

        if (msg == null) {
            return;
        }
        try {
            JsonFormat.printer().print(msg);
            MessageVo messageVo = new MessageVo();
            messageVo.setFromId(msg.getFromId());
            messageVo.setToId(toId);
            String content = JsonFormat.printer().print(msg);
            messageVo.setContent(content);
            messageVo.setMsgId(msgId);
            messageVo.setStatus(MessageStatus.UN_ACK.ordinal());
            messageVo.setType(MessageType.CHAT_NOTICE.ordinal());
            //保存到数据库
            messageService.save(messageVo);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
