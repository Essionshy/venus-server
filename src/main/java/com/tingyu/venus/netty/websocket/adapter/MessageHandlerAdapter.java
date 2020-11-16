package com.tingyu.venus.netty.websocket.adapter;

import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @Author essionshy
 * @Create 2020/11/15 11:56
 * @Version venus-server
 *
 *
 *
 *
 */
public interface MessageHandlerAdapter {

   boolean support(MessageHandler handler);

   /**
    * 因此传入参数类型比较多，因此需要将
    * @param ctx
    * @param msg
    * @param handler
    */
   void handle(ChannelHandlerContext ctx, TextWebSocketFrame msg, MessageHandler handler) throws Exception;

}
