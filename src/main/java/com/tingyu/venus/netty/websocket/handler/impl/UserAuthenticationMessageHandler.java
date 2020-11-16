package com.tingyu.venus.netty.websocket.handler.impl;

import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

/**
 * @Author essionshy
 * @Create 2020/11/16 11:15
 * @Version venus-server
 */
@Service
public class UserAuthenticationMessageHandler extends AbstractJsonMessageHandler {

    /**
     *
     * @param ctx
     * @param msg  根据不同的消息类型，创建对应的实体类
     * @param contentJsonStr
     */
    @Override
    protected void handleInternal(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg, String contentJsonStr) {
        //TODO 处理用户认证





    }
}
