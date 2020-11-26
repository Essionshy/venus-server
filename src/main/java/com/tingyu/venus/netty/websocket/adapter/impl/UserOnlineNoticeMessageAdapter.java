package com.tingyu.venus.netty.websocket.adapter.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.Any;
import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.protobuf.UserOnlineNotice;
import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import com.tingyu.venus.netty.websocket.handler.impl.UserOnLineNoticeMessageHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author essionshy
 * @Create 2020/11/19 22:31
 * @Version venus-server
 */
@Service
@Slf4j
public class UserOnlineNoticeMessageAdapter extends AbstractJsonMessageAdapter {
    @Override
    public boolean supportInternal(MessageHandler handler) {
        return handler instanceof UserOnLineNoticeMessageHandler;
    }

    @Override
    protected void handleInternal(ChannelHandlerContext ctx, TextWebSocketFrame msg, MessageHandler handler) throws Exception {

        //解析msg参数
        String text = msg.text();
        TransportMessageOuterClass.TransportMessage.Builder builder = TransportMessageOuterClass.TransportMessage.newBuilder();

        if (!StringUtils.isEmpty(text)) {
            //解析消息
            JSONObject jsonObject = (JSONObject) JSONObject.parse(text);
            String jsonString = jsonObject.toJSONString();

            log.info("解析后的JSONObject {}",jsonString);
           // JsonFormat.parser().merge(jsonString, builder);

            //提取 消息体 messageBody
            String messageBody = jsonObject.getString("messageBody");
            log.info("messageBody:{}", messageBody);

            JSONObject jsonMessageBody = JSONObject.parseObject(messageBody);
            String userPhone = (String) jsonMessageBody.get("userPhone");

            //封装用户上线通知消息
            UserOnlineNotice.UserOnlineNoticeMessage.Builder onlineNotice = UserOnlineNotice.UserOnlineNoticeMessage.newBuilder();

            onlineNotice.setUserPhone(userPhone);
            UserOnlineNotice.UserOnlineNoticeMessage onlineNoticeMessage = onlineNotice.build();
            //封装数据传输对象
            builder.setMessageBody(Any.pack(onlineNoticeMessage));
            TransportMessageOuterClass.TransportMessage transportMessage = builder.build();

            //获取处理器执行
            UserOnLineNoticeMessageHandler userOnLineNoticeMessageHandler = (UserOnLineNoticeMessageHandler) handler;
            userOnLineNoticeMessageHandler.handle(ctx, transportMessage, messageBody);

        }
    }
}
