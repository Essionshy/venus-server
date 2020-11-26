package com.tingyu.venus.netty.websocket.adapter.impl;

import com.alibaba.fastjson.JSONObject;
import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import com.tingyu.venus.netty.websocket.handler.impl.UserOfflineNoticeMessageHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author essionshy
 * @Create 2020/11/20 13:47
 * @Version venus-server
 */
@Slf4j
@Service
public class UserOfflineNoticeMessageAdapter extends AbstractJsonMessageAdapter {
    @Override
    public boolean supportInternal(MessageHandler handler) {
        return handler instanceof UserOfflineNoticeMessageHandler;
    }

    @Override
    protected void handleInternal(ChannelHandlerContext ctx, TextWebSocketFrame msg, MessageHandler handler) throws Exception {

        String message = msg.text();
        log.debug("message:{}",message);

        JSONObject jsonObjectMessage = JSONObject.parseObject(message);

        String messageBody = jsonObjectMessage.getString("messageBody");
        UserOfflineNoticeMessageHandler userOfflineNoticeMessageHandler= (UserOfflineNoticeMessageHandler) handler;
        userOfflineNoticeMessageHandler.handle(ctx,null,messageBody);

    }
}
