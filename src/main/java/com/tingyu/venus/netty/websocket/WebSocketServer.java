package com.tingyu.venus.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author essionshy
 * @Create 2020/11/14 11:50
 * @Version venus-server
 */
@Service
@Slf4j
public class WebSocketServer {


    private static final String PORT="com.tingyu.venus.websocket.port";

    @Autowired
    Environment environment;

    @Autowired
    private WebSocketHandler webSocketHandler;

    @PostConstruct
    public void run() {
        new Thread(() ->{
            EventLoopGroup bossGroup = new NioEventLoopGroup(1);
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap serverBootstrap = new ServerBootstrap();
                serverBootstrap.group(bossGroup, workerGroup)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_KEEPALIVE,true)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
                                pipeline.addLast(new HttpServerCodec());
                                //以块的方式来写的处理器
                                pipeline.addLast(new ChunkedWriteHandler());
                                //netty是基于分段请求的，HttpObjectAggregator的作用是将请求分段再聚合,参数是聚合字节的最大长度
                                pipeline.addLast(new HttpObjectAggregator(65536))
                                        .addLast(new WebSocketServerProtocolHandler("/ws"))
                                        .addLast(webSocketHandler);
                            }
                        });

                ChannelFuture cf = serverBootstrap.bind(getPort()).sync();

                cf.addListener(future -> {

                    if (future.isSuccess()) {
                        log.info("Server websocket start successfully... listen port at" + getPort());
                    } else {
                        log.info("Server websocket start fail...");
                    }
                });

                cf.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放资源
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }

        },"WebSocket Server").start();

    }

    private int getPort() {
        return Integer.valueOf(environment.getProperty(PORT));
    }

}
