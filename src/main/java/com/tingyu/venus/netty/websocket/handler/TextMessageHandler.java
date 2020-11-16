package com.tingyu.venus.netty.websocket.handler;

import io.netty.channel.ChannelHandlerContext;

/**
 * @Author essionshy
 * @Create 2020/11/16 11:09
 * @Version venus-server
 */
public interface TextMessageHandler extends MessageHandler {

    /**
     *
     * @param ctx
     * @param param     *
     * @throws Exception
     */
    void handle(ChannelHandlerContext ctx, Object param) throws Exception;
}
