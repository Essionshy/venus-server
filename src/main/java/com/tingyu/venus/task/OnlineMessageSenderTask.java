package com.tingyu.venus.task;

import com.google.protobuf.util.JsonFormat;
import com.tingyu.venus.common.enums.MessageStatus;
import com.tingyu.venus.common.enums.MessageType;
import com.tingyu.venus.netty.protobuf.*;
import com.tingyu.venus.netty.util.MessageUtil;
import com.tingyu.venus.netty.util.NettyChannelUtil;
import com.tingyu.venus.service.MessageService;
import com.tingyu.venus.vo.MessageVo;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/25 11:53
 * @Version venus-server
 */
@Service
@Slf4j
public class OnlineMessageSenderTask {


    @Resource
    private MessageService messageService;

    //每30秒执行一次
    @Scheduled(cron = "0/30 * * * * ?")
    public void send() {


        //删除已签收的消息
        messageService.removeAllAck();


        List<MessageVo> messageList = messageService.getUnAckMessageList();


        if (messageList == null || messageList.size() == 0) {
            return;
        }

        for (MessageVo message : messageList) {
            //获取目标联系人

            String toId = message.getToId();

            //查询该联系人是否上线
            ChannelHandlerContext ctx = NettyChannelUtil.USER_ID_CHANNEL_GROUP.get(toId);
            if (ctx == null) {
                log.info("message sender task ：用户未上线");
                continue;
            }

            //判断是发送什么消息

            if (message.getType() == MessageType.CHAT_NOTICE.ordinal()) {
                sendChatMessage(message, ctx);

            } else if (message.getType() == MessageType.SINGLE_INVITE.ordinal()) {
                //联系人邀请消息
                sendContactInvitation(message, ctx);

            } else if (message.getType() == MessageType.GROUP_INVITE.ordinal()) {
                //群邀请消息
                sendGroupChatInvitation(message, ctx);
            } else if (message.getType() == MessageType.NOTICE.ordinal()) {
                //系统通知消息


            }


            //修改该消息状态

            messageService.updateStatus(MessageStatus.ACK.ordinal(), message.getId());


        }


    }

    /**
     * 发送聊天信息
     *
     * @param message
     * @param ctx
     */
    private void sendChatMessage(MessageVo message, ChannelHandlerContext ctx) {

        try {
            String content = message.getContent();
            ChatNotice.ChatNoticeMessage.Builder builder = ChatNotice.ChatNoticeMessage.newBuilder();
            JsonFormat.parser().merge(content, builder);
            ChatNotice.ChatNoticeMessage chatMessage = builder.build();
            MessageUtil.sendWebSocketMsg(ctx, TransportMessageOuterClass.MessageType.CHAT_MSG_NOTICE, null, null, chatMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送群聊邀请
     *
     * @param message
     * @param ctx
     */
    private void sendGroupChatInvitation(MessageVo message, ChannelHandlerContext ctx) {
        String content = message.getContent();

        try {
            GroupChatCreateNotice.GroupChatCreateNoticeMessage.Builder builder = GroupChatCreateNotice.GroupChatCreateNoticeMessage.newBuilder();
            JsonFormat.parser().merge(content, builder);
            GroupChatCreateNotice.GroupChatCreateNoticeMessage noticeMessage = builder.build();

            MessageUtil.sendWebSocketMsg(ctx, TransportMessageOuterClass.MessageType.CREATE_GROUP_CHAT_NOTICE, null, null, noticeMessage);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 发送联系人邀请
     *
     * @param message
     * @param ctx
     */
    private void sendContactInvitation(MessageVo message, ChannelHandlerContext ctx) {
        //TODO 邀请状态字段没有保存，是否考虑将消息序列化后保存到数据库
        String content = message.getContent();

        try {

            ContactAddNotice.ContactAddNoticeMessage.Builder builder = ContactAddNotice.ContactAddNoticeMessage.newBuilder();
            JsonFormat.parser().merge(content, builder);
            ContactAddNotice.ContactAddNoticeMessage addNoticeMessage = builder.buildPartial();

            MessageUtil.sendWebSocketMsg(ctx, TransportMessageOuterClass.MessageType.ADD_CONTACT_NOTICE, null, null, addNoticeMessage);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
