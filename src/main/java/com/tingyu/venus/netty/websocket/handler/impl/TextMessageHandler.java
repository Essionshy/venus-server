package com.tingyu.venus.netty.websocket.handler.impl;

import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.util.Constant;
import com.tingyu.venus.netty.util.MessageUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/11/15 12:49
 * @Version venus-server
 */
@Slf4j
@Component
public class TextMessageHandler extends AbstractTextMessageHandler {


    @Override
    protected void handleInternal(ChannelHandlerContext ctx, String text) {
        log.info("接收到客户端消息，文本类型，文本内容：{}",text);
        //TODO 处理文本消息逻辑

        if(text.equals(Constant.HEARTBEAT_NOTICE)){
            MessageUtil.sendWebSocketMsg(ctx, TransportMessageOuterClass.MessageType.HEARTBEAT_NOTICE,null,null,null);
        }

    }
}
