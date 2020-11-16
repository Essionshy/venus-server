package com.tingyu.venus.netty.websocket.adapter.impl;

import com.tingyu.venus.netty.websocket.adapter.MessageHandlerAdapter;
import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @Author essionshy
 * @Create 2020/11/16 10:35
 * @Version venus-server
 */
public abstract class AbstractTextMessageAdapter  implements MessageHandlerAdapter {
    @Override
    public boolean support(MessageHandler handler) {
        return supportInternal(handler);
    }

    protected abstract boolean supportInternal(MessageHandler handler);

    @Override
    public void handle(ChannelHandlerContext ctx, TextWebSocketFrame msg, MessageHandler handler) throws  Exception {
        handleInternal(ctx,  msg,handler);
    }
    protected abstract void handleInternal(ChannelHandlerContext ctx, TextWebSocketFrame msg, MessageHandler handler);
}
