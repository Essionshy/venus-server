package com.tingyu.venus.netty.socket;

import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.socket.processor.ServerHandlerAdapter;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/10/21 9:28
 * @Version venus-server
 */
@ChannelHandler.Sharable
@Component
@Slf4j
public class DispatcherSocketHandler extends SimpleChannelInboundHandler<TransportMessageOuterClass.TransportMessage> {

    @Autowired
    ServerHandlerAdapter serverHandlerAdapter;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Socket 连接成功...");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg) throws Exception {
        log.info("Socket 读取数据中...");
        serverHandlerAdapter.handle(ctx,msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("Server running exception ...{}",cause.getMessage());
        //服务器发生异常时关闭通道
        ctx.close();
    }


}
