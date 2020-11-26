package com.tingyu.venus.netty.socket.handler;

import com.google.protobuf.util.JsonFormat;
import com.tingyu.venus.common.enums.MessageStatus;
import com.tingyu.venus.common.enums.MessageType;
import com.tingyu.venus.netty.protobuf.ContactAddNotice;
import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.socket.processor.AbstractProtobufMessageHandler;
import com.tingyu.venus.netty.util.MessageUtil;
import com.tingyu.venus.netty.util.NettyChannelUtil;
import com.tingyu.venus.service.ContactService;
import com.tingyu.venus.service.MessageService;
import com.tingyu.venus.vo.ContactVo;
import com.tingyu.venus.vo.MessageVo;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 联系添加通知处理器，如果添加信息是被接受，则服务端需要创建联系人的关系记录，只是通知或者拒绝，直接转发即可
 *
 * @Author essionshy
 * @Create 2020/11/18 9:01
 * @Version venus-server
 */
@Component
@Slf4j
public class ContactAddNoticeMessageHandler extends AbstractProtobufMessageHandler {

    @Autowired
    private ContactService contactService;

    @Autowired
    private MessageService messageService;

    @Override
    protected void handleInternal(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg) {
        if (msg.getMessageType().equals(TransportMessageOuterClass.MessageType.ADD_CONTACT_NOTICE)) {

            try {
                ContactAddNotice.ContactAddNoticeMessage message = msg.getMessageBody().unpack(ContactAddNotice.ContactAddNoticeMessage.class);

                if (message != null) {

                    //获取目标联系人
                    String fromPhone = message.getFromPhone();

                    String toPhone = message.getToPhone();

                    //根据目标联系查询其所在通道
                    ChannelHandlerContext toContext = NettyChannelUtil.USER_ID_CHANNEL_GROUP.get(toPhone);
                    if (toContext != null) {

                        //根据邀请的状态转发不同的通知消息
                        ContactAddNotice.ContactAddNoticeMessage.InvitationStatus status = message.getStatus();

                        if (status == ContactAddNotice.ContactAddNoticeMessage.InvitationStatus.INVITE_ACCEPT) {
                            //如果联系添加被接收，服务端需要保存其好友有关系
                            ContactVo contactFrom = new ContactVo();
                            contactFrom.setUserPhone(fromPhone);
                            contactFrom.setContactPhone(toPhone);
                            contactService.save(contactFrom);

                            ContactVo contactTo = new ContactVo();
                            contactTo.setUserPhone(toPhone);
                            contactTo.setContactPhone(fromPhone);
                            contactService.save(contactTo);

                            //转发邀请被接收的消息

                        }
                        MessageUtil.sendWebSocketMsg(toContext, TransportMessageOuterClass.MessageType.ADD_CONTACT_NOTICE, null, null, message);

                        //通知客户端消息已经签收
                        MessageUtil.sendSocketMsg(ctx, TransportMessageOuterClass.MessageType.MESSAGE_RECEIVED_ACK, null, null, null);

                    } else {
                        //目标不在线
                        log.info("该用户目前不在线");
                        sendOfflineMessage(message, msg.getMessageId());
                    }


                }


            } catch (Exception e) {
                e.printStackTrace();
                log.error("解析消息异常");
            }


        } else {
            handler.handle(ctx, msg);
        }
    }

    /**
     * 发送离线联系人邀请
     *
     * @param message
     */
    private void sendOfflineMessage(ContactAddNotice.ContactAddNoticeMessage message, Long msgId) {

        if (message == null) {
            return;
        }
        try {
            MessageVo messageVo = new MessageVo();
            messageVo.setMsgId(msgId);
            messageVo.setFromId(message.getFromPhone());
            messageVo.setToId(message.getToPhone());
            messageVo.setStatus(MessageStatus.UN_ACK.ordinal());
            messageVo.setType(MessageType.SINGLE_INVITE.ordinal());
            String content = JsonFormat.printer().print(message);
            messageVo.setContent(content);
            messageService.save(messageVo);
        } catch (Exception e) {

            e.printStackTrace();
        }


    }
}
