package com.tingyu.venus.netty.socket.processor;

import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
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
public class DispatcherSocketProcessor {

    @Autowired
    ProtobufMessageHandlerExecutionChain handler;

    /**
     * 负责派发消息
     * @param ctx
     * @param msg
     */
    public void process(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg){

        handler.getHandler().handle(ctx,msg);
    }

}
