package com.tingyu.venus.netty.websocket.handler.impl;

import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.websocket.handler.JsonMessageHandler;
import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author essionshy
 * @Create 2020/11/16 8:56
 * @Version venus-server
 */
public abstract class AbstractJsonMessageHandler implements JsonMessageHandler {

    protected MessageHandler handler;

    @Override
    public void nextMessageHandler(MessageHandler handler) {

        this.handler=handler;
    }

    @Override
    public MessageHandler getHandler(TransportMessageOuterClass.MessageType messageType) {
        if(messageType.equals(TransportMessageOuterClass.MessageType.UserAuthenticationNotice)){
            return new UserAuthenticationMessageHandler();
        }else{
            return   this.handler.getHandler(messageType);
        }
    }

    @Override
    public void handle(ChannelHandlerContext ctx, Object param,String contentJsonStr) throws Exception {
        handleInternal(ctx, (TransportMessageOuterClass.TransportMessage) param,contentJsonStr);
    }

   protected abstract void handleInternal(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg,String contentJsonStr);
}
