package com.tingyu.venus.netty.socket;

import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.socket.processor.DispatcherSocketProcessor;
import com.tingyu.venus.netty.util.NettyChannelUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.SocketAddress;

/**
 * @Author essionshy
 * @Create 2020/10/21 9:28
 * @Version venus-server
 */
@ChannelHandler.Sharable
@Component
@Slf4j
public class SocketServerHandler extends SimpleChannelInboundHandler<TransportMessageOuterClass.TransportMessage> {

    @Autowired
    private DispatcherSocketProcessor dispatcherSocketProcessor;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketAddress socketAddress = ctx.channel().remoteAddress();
        log.info("SocketServerHandler 连接成功...{}",socketAddress.toString());
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("SocketServerHandler handlerAdded socket");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.error("Server running exception ...{}",cause.getMessage());
        //服务器发生异常时关闭通道
        ctx.close();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg) throws Exception {
        log.info("SocketServerHandler 读取数据中");
        dispatcherSocketProcessor.process(ctx,msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info(" SocketServerHandler 数据读取完成...");
        ctx.flush();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("Socket 退出");
        NettyChannelUtil.exit(ctx);
    }
}
