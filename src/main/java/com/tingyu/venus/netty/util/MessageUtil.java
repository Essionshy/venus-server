package com.tingyu.venus.netty.util;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import com.tingyu.venus.netty.protobuf.ContactAddNotice;
import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @Author essionshy
 * @Create 2020/11/21 13:36
 * @Version venus-server
 */
public final class MessageUtil {

    /**
     * 发送socket消息
     *
     * @param ctx
     * @param messageType
     * @param accessToken
     * @param messageId
     * @param response
     */
    public static void sendSocketMsg(ChannelHandlerContext ctx, TransportMessageOuterClass.MessageType messageType, String accessToken, Long messageId, Message response) {
        Message transportMessage = getMessage(messageType, accessToken, messageId, response);
        ctx.writeAndFlush(transportMessage);
    }


    /**
     * 发送
     *
     * @param ctx
     * @param messageType
     * @param accessToken
     * @param refMessageId
     * @param response
     */
    public static void sendWebSocketMsg(ChannelHandlerContext ctx, TransportMessageOuterClass.MessageType messageType, String accessToken, Long refMessageId, Message response) {
        //获取消息类型名称
        String messageTypeStr = messageType.name();
        //获取响应消息JSON格式字符串
        String message = null;
        if (response != null) {



            try {

                message = JsonFormat.printer().print(response);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }

            //对返回为添加联系人返回做处理，封装status属性
            if(response instanceof ContactAddNotice.ContactAddNoticeMessage){
                ContactAddNotice.ContactAddNoticeMessage contactAddNoticeMessage= (ContactAddNotice.ContactAddNoticeMessage) response;

                ContactAddNotice.ContactAddNoticeMessage.InvitationStatus status = contactAddNoticeMessage.getStatus();
                JSONObject jsonObject = JSONObject.parseObject(message);
                jsonObject.put("status",status);
                message=jsonObject.toJSONString();

            }

        }
        //构建JSON格式响应消息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageType", messageTypeStr);
        jsonObject.put("message", message);
        ctx.writeAndFlush(new TextWebSocketFrame(jsonObject.toJSONString()));

    }


    /**
     * 构建可传输的消息
     *
     * @param message
     * @return
     */
    private static Message getMessage(TransportMessageOuterClass.MessageType messageType, String accessToken, Long refMsgId, Message message) {


        TransportMessageOuterClass.TransportMessage.Builder builder = TransportMessageOuterClass.TransportMessage.newBuilder();

        builder.setMessageId(MessageIdGenerator.getId());//设置消息ID

        //设置消息类型
        if (messageType != null) {
            builder.setMessageType(messageType);
        }

        if (accessToken != null) {
            builder.setAccessToken(accessToken);
        }

        if (refMsgId != null) {
            builder.setRefMessageId(refMsgId);
        }

        if (message != null) {
            builder.setMessageBody(Any.pack(message)); //封装消息体
        }

        return builder.build();

    }


}
