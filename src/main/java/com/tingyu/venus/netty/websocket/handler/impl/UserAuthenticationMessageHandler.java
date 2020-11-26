package com.tingyu.venus.netty.websocket.handler.impl;

import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

/**
 * @Author essionshy
 * @Create 2020/11/16 11:15
 * @Version venus-server
 */
@Service
public class UserAuthenticationMessageHandler extends AbstractJsonMessageHandler {

    @Override
    protected MessageHandler getHandlerInternal(TransportMessageOuterClass.MessageType messageType) {
        if(messageType.equals(TransportMessageOuterClass.MessageType.UserAuthenticationNotice)){
            return this;
        }else {
            return super.handler.getHandler(messageType);
        }
    }

    /**
     *
     * @param ctx
     * @param msg  根据不同的消息类型，创建对应的实体类
     * @param contentJsonStr
     */
    @Override
    protected void handleInternal(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg, String contentJsonStr) {
        //TODO 处理用户认证

        //TODO 测试是否可以通过websocket channel 发送消息给socket 客户端
        TransportMessageOuterClass.TransportMessage transportMessage = TransportMessageOuterClass.TransportMessage.newBuilder()
                .setMessageType(TransportMessageOuterClass.MessageType.USER_ONLINE_NOTICE).setMessageId(10111).build();

        ctx.writeAndFlush(new TextWebSocketFrame("你是个哈逼吗"));




    }
}
