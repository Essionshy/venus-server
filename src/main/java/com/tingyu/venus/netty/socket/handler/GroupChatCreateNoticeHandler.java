package com.tingyu.venus.netty.socket.handler;

import com.google.protobuf.util.JsonFormat;
import com.tingyu.venus.common.enums.MessageStatus;
import com.tingyu.venus.common.enums.MessageType;
import com.tingyu.venus.netty.protobuf.GroupChatCreateNotice;
import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.socket.processor.AbstractProtobufMessageHandler;
import com.tingyu.venus.netty.util.MessageUtil;
import com.tingyu.venus.netty.util.NettyChannelUtil;
import com.tingyu.venus.service.GroupChatService;
import com.tingyu.venus.service.MessageService;
import com.tingyu.venus.vo.GroupChatVo;
import com.tingyu.venus.vo.MessageVo;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 创建群通知
 *
 * @Author essionshy
 * @Create 2020/11/25 14:46
 * @Version venus-server
 */
@Service
@Slf4j
public class GroupChatCreateNoticeHandler extends AbstractProtobufMessageHandler {

    @Autowired
    private GroupChatService groupChatService;

    @Autowired
    private MessageService messageService;

    @Override
    protected void handleInternal(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg) {

        if (msg.getMessageType().equals(TransportMessageOuterClass.MessageType.CREATE_GROUP_CHAT_NOTICE)) {
            try {

                GroupChatCreateNotice.GroupChatCreateNoticeMessage createNoticeMessage = msg.getMessageBody().unpack(GroupChatCreateNotice.GroupChatCreateNoticeMessage.class);
                //创建群记录

                createGroupChat(createNoticeMessage);

                //发送群邀请给群成员

                sendInvitation(createNoticeMessage.getMemberIds(), msg.getMessageId(), createNoticeMessage);

                //通知客户端消息已签收


                MessageUtil.sendSocketMsg(ctx, TransportMessageOuterClass.MessageType.MESSAGE_RECEIVED_ACK, null, null, null);


            } catch (Exception e) {
                e.printStackTrace();
                log.error("unpack message异常");
            }

        } else {
            super.handler.handle(ctx, msg);
        }


    }

    /**
     * 创建群记录
     *
     * @param createInfo
     */
    private void createGroupChat(GroupChatCreateNotice.GroupChatCreateNoticeMessage createInfo) {
        if (createInfo == null) {
            return;
        }
        GroupChatVo groupChatVo = new GroupChatVo();
        groupChatVo.setGroupId(createInfo.getGroupId());//群ID
        groupChatVo.setGroupName(createInfo.getGroupName()); //群名称
        groupChatVo.setGroupHolder(createInfo.getCreateId()); //群发起人
        groupChatVo.setGroupNotice(createInfo.getNotice()); //群公告
        groupChatVo.setMemberIds(getMemberList(createInfo.getMemberIds()));
        groupChatService.save(groupChatVo);


        //保存群成员关系表


    }

    /**
     * 获取群成员
     *
     * @param memberIds
     * @return
     */
    private List<String> getMemberList(String memberIds) {

        String[] members = memberIds.split(",");
        List<String> list = Arrays.asList(members);
        return list;
    }

    /**
     * 遍历群成员发送群邀请
     *
     * @param memberIds
     */
    private void sendInvitation(String memberIds, Long msgId, GroupChatCreateNotice.GroupChatCreateNoticeMessage createNoticeMessage) {
        List<String> memberList = getMemberList(memberIds);

        if (memberList != null && memberList.size() > 0) {

            for (String contactId : memberList) {
                ChannelHandlerContext ctx = NettyChannelUtil.USER_ID_CHANNEL_GROUP.get(contactId);

                if (ctx != null) {
                    //发送消息
                    MessageUtil.sendWebSocketMsg(ctx, TransportMessageOuterClass.MessageType.CREATE_GROUP_CHAT_NOTICE, null, null, createNoticeMessage);

                } else {
                    //发送离线消息
                    sendOfflineNotice(contactId, msgId, createNoticeMessage);

                }

            }


        }


    }

    /**
     * 发送离线消息
     *
     * @param contactId
     * @param message
     */
    private void sendOfflineNotice(String contactId, Long messageId, GroupChatCreateNotice.GroupChatCreateNoticeMessage message) {

        try {
            MessageVo messageVo = new MessageVo();
            messageVo.setFromId(message.getCreateId()); //创建人
            messageVo.setToId(contactId);
            messageVo.setMsgId(messageId);
            messageVo.setStatus(MessageStatus.UN_ACK.ordinal());//未签收的消息
            messageVo.setType(MessageType.GROUP_INVITE.ordinal());//群邀请
            String content = JsonFormat.printer().print(message);
            messageVo.setContent(content);
            messageService.save(messageVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
