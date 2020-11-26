package com.tingyu.venus.netty.socket;

import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.socket.handler.StringServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/10/21 9:26
 * @Version venus-server
 */
@Component
public class SocketServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    SocketServerHandler socketServerHandler;
    @Autowired
    StringServerHandler handler;
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("decoder",new ProtobufDecoder(TransportMessageOuterClass.TransportMessage.getDefaultInstance()))
              //  .addLast("decoder",new ProtobufDecoder(UserAuthenticationNotice.UserAuthenticationMessage.getDefaultInstance()))
                .addLast("encoder",new ProtobufEncoder())
                .addLast(socketServerHandler);


    }
}
