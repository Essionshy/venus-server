package com.tingyu.venus.netty.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author essionshy
 * @Create 2020/10/21 9:18
 * @Version venus-server
 */
@Service
@Slf4j
public class SocketServer {

    @Autowired
    SocketServerInitializer socketServerInitializer;



    @Autowired
    Environment environment;

    @Autowired
    ApplicationContext context;

    @PostConstruct
    public void run(){


        new Thread(()->{
            EventLoopGroup bossGroup=new NioEventLoopGroup(1);
            EventLoopGroup workerGroup=new NioEventLoopGroup();

            try{

                ServerBootstrap bootstrap=new ServerBootstrap();
                bootstrap.group(bossGroup,workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG,1024)
                        .option(ChannelOption.SO_KEEPALIVE,true)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .childHandler(socketServerInitializer);
                ChannelFuture cf = bootstrap.bind(getPort()).sync();
                Channel channel = cf.channel();
                cf.addListener(future -> {
                    if(future.isSuccess()){
                        log.info("[Server start successfully...Listen tcp port at {}]",getPort());
                    }else {
                        log.info("[Server start failed...]");
                    }
                });
                channel.closeFuture().sync();


            }catch (Exception e){
                log.error("Server error...[{}]",e.getMessage());

            }finally {

                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        },"ServerBootStrap").start();


    }

    private int getPort() {
      return Integer.valueOf(environment.getProperty("com.tingyu.venus.socket.port"))  ;

    }
}
