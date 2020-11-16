package com.tingyu.venus.netty.socket.processor;

import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.socket.handler.UserAuthenticationHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/10/21 10:14
 * @Version venus-server
 */
@Component
@Slf4j
public class ServerHandlerAdapter {

    @Autowired
    UserAuthenticationHandler userAuthenticationHandler;

    public void handle(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg){
        //获取消息类型，根据消息类型不同调用不同的Handler来，如何用适配器模式来解决





    }

}
