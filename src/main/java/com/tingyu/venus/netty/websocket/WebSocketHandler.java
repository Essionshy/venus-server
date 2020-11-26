package com.tingyu.venus.netty.websocket;


import com.tingyu.venus.netty.util.NettyChannelUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author essionshy
 * @Create 2020/11/14 11:59
 * @Version venus-server
 */

@Slf4j
@Service
@ChannelHandler.Sharable
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Autowired
    private DispatcherWebSocketProcessor dispatcherWebSocketProcessor;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        dispatcherWebSocketProcessor.process(ctx,msg);
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerAdded at {}", ctx.channel().id().asLongText());
        //  ctx.writeAndFlush(new TextWebSocketFrame("连接服务器成功..."));
        //客户端断开连接，移除对应的通道



    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerRemoved at {}", ctx.channel().id().asLongText());
        //
        NettyChannelUtil.exit(ctx);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        log.info("异常发生{}", cause.getMessage());
        ctx.close();
    }
}
