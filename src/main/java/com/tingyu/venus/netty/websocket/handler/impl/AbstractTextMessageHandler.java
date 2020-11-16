package com.tingyu.venus.netty.websocket.handler.impl;

import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import com.tingyu.venus.netty.websocket.handler.TextMessageHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author essionshy
 * @Create 2020/11/16 8:44
 * @Version venus-server
 */
public abstract class AbstractTextMessageHandler implements TextMessageHandler {

   protected MessageHandler handler;

    @Override
    public void nextMessageHandler(MessageHandler handler) {
       this.handler=handler;
    }

    @Override
    public MessageHandler getHandler(TransportMessageOuterClass.MessageType messageType) {
        if(messageType ==null){
            return this;
        }else {
            return this.handler.getHandler(messageType);
        }
    }

    /**
     *  负责将参数强制转换为能够处理的文本类型，强制转换可能出现转换异常，将其抛出
     * @param ctx
     * @param param
     * @
     */
    @Override
    public void handle(ChannelHandlerContext ctx, Object param) throws Exception{
        handleInternal(ctx,(String)param);
    }

    protected abstract void handleInternal(ChannelHandlerContext ctx, String text);

}
