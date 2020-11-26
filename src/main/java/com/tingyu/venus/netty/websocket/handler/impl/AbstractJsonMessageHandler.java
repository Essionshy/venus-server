package com.tingyu.venus.netty.websocket.handler.impl;

import com.tingyu.venus.exception.ResultException;
import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.websocket.handler.JsonMessageHandler;
import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/16 8:56
 * @Version venus-server
 */
public abstract class AbstractJsonMessageHandler implements JsonMessageHandler {

    protected MessageHandler handler;

    private static List<TransportMessageOuterClass.MessageType> supportMessageTypes;


    static {
        supportMessageTypes=new ArrayList<>();
        //TODO 将所有支持的消息类型添加到集合中
        supportMessageTypes.add(TransportMessageOuterClass.MessageType.UserAuthenticationNotice);
        supportMessageTypes.add(TransportMessageOuterClass.MessageType.USER_ONLINE_NOTICE);

    }

    @Override
    public void nextMessageHandler(MessageHandler handler) {

        this.handler=handler;
    }

    @Override
    public MessageHandler getHandler(TransportMessageOuterClass.MessageType messageType) {
        //TODO 为了避免不支持的消息类型导致栈溢出，需要判断
        if(!supportMessageTypes.contains(messageType)){
            throw new ResultException(25001,"不支持该消息类型");
        }

     return   this.getHandlerInternal(messageType);
    }

    @Override
    public void handle(ChannelHandlerContext ctx, Object param,String contentJsonStr) throws Exception {
        handleInternal(ctx, (TransportMessageOuterClass.TransportMessage) param,contentJsonStr);
    }

    protected abstract MessageHandler getHandlerInternal(TransportMessageOuterClass.MessageType messageType);
   protected abstract void handleInternal(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg,String contentJsonStr);
}
