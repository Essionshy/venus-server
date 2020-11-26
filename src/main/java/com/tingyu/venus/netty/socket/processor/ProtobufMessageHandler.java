package com.tingyu.venus.netty.socket.processor;

import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author essionshy
 * @Create 2020/11/18 8:29
 * @Version venus-server
 */
public interface ProtobufMessageHandler {

    void setHandler(ProtobufMessageHandler handler);

    void handle(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg);



}
