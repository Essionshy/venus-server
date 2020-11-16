package com.tingyu.venus.netty.websocket.handler;

import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author essionshy
 * @Create 2020/11/16 11:11
 * @Version venus-server
 */
public interface JsonMessageHandler extends MessageHandler {

     /**
      * 根据责任链模式获取实际的 MessageHandler
      * @param messageType
      * @return
      */
     MessageHandler getHandler(TransportMessageOuterClass.MessageType messageType);

     void handle(ChannelHandlerContext ctx, Object param, String contentJsonStr) throws Exception;

}
