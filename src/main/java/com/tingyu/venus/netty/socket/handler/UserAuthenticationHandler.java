package com.tingyu.venus.netty.socket.handler;


import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/10/21 10:57
 * @Version venus-server
 */
@Component
@Slf4j
public class UserAuthenticationHandler {

    /**
     * 处理用户认证
     * @param ctx
     * @param msg
     */
    public void handle(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg){




        //响应客户端消息
        //测试过程中发现一个现象：为什么只设置MessageType属性时，服务端不向客户端写入数据呢？



    }

}
