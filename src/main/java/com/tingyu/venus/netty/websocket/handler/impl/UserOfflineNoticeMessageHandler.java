package com.tingyu.venus.netty.websocket.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户离线通知消息处理器
 * @Author essionshy
 * @Create 2020/11/20 13:42
 * @Version venus-server
 */
@Service
@Slf4j
public class UserOfflineNoticeMessageHandler extends AbstractJsonMessageHandler {
    @Override
    protected MessageHandler getHandlerInternal(TransportMessageOuterClass.MessageType messageType) {
       if(messageType.equals(TransportMessageOuterClass.MessageType.USER_OFFLINE_NOTICE)){
           return this;
       }else{
           return  super.handler.getHandler(messageType);
       }

    }

    @Override
    protected void handleInternal(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg, String contentJsonStr) {
        JSONObject messageBody = JSONObject.parseObject(contentJsonStr);

        //获取用户手机号
        String userPhone = messageBody.getString("userPhone");


        ctx.writeAndFlush(new TextWebSocketFrame(userPhone+"已下线..."));

    }
}
