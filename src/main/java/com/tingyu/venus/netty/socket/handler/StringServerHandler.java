package com.tingyu.venus.netty.socket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author essionshy
 * @Create 2020/11/19 9:18
 * @Version venus-server
 */
@Service
@Slf4j

public class StringServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info("读取取数据 {}",msg);
        ctx.writeAndFlush("如果时间");

    }
}
