package com.tingyu.venus.netty.socket.handler;


import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.socket.processor.AbstractProtobufMessageHandler;
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
public class UserAuthenticationHandler extends AbstractProtobufMessageHandler {


    /**
     * 处理用户认证
     * @param ctx
     * @param msg
     */
    @Override
    protected void handleInternal(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg) {

        //根据消息类型判断是由本Handler处理，还是交由下一个Handler处理
        if(msg.getMessageType().equals(TransportMessageOuterClass.MessageType.UserAuthenticationNotice)){
            //TODO 处理用户认证的业务逻辑
            log.debug("处理用户认证");
            TransportMessageOuterClass.TransportMessage.Builder builder = TransportMessageOuterClass.TransportMessage.newBuilder();
            builder.setMessageType(TransportMessageOuterClass.MessageType.UserAuthenticationNotice);

            TransportMessageOuterClass.TransportMessage transportMessage = builder.build();

            ctx.writeAndFlush(transportMessage);
        }else{
            handler.handle(ctx,msg);
        }
    }

}
