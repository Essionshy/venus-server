package com.tingyu.venus.netty.websocket.adapter.impl;

import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import com.tingyu.venus.netty.websocket.handler.impl.TextMessageHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/11/15 12:00
 * @Version venus-server
 */
@Component
@Slf4j
public class TextMessageAdapter extends AbstractTextMessageAdapter {

    @Override
    protected boolean supportInternal(MessageHandler handler) {
        return handler instanceof TextMessageHandler;
    }


    @Override
    protected void handleInternal(ChannelHandlerContext ctx, TextWebSocketFrame msg, MessageHandler handler) {
        try {
            log.info("处理文本格式的消息");
            TextMessageHandler textMessageHandler= (TextMessageHandler) handler;
            textMessageHandler.handle(ctx,msg.text());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
