package com.tingyu.venus.netty.websocket.handler.impl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/11/15 12:49
 * @Version venus-server
 */
@Slf4j
@Component
public class TextMessageHandler extends AbstractTextMessageHandler {


    @Override
    protected void handleInternal(ChannelHandlerContext ctx, String text) {
        log.info("接收到客户端消息，文本类型，文本内容：{}",text);
        //TODO 处理文本消息逻辑


        ctx.writeAndFlush(new TextWebSocketFrame(text));
    }
}
