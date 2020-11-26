package com.tingyu.venus.netty.websocket.adapter.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.util.JsonFormat;
import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import com.tingyu.venus.netty.websocket.handler.impl.UserAuthenticationMessageHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;



/**
 * @Author essionshy
 * @Create 2020/11/15 12:05
 * @Version venus-server
 */
@Component
@Slf4j
public class UserAuthenticationMessageAdapter extends AbstractJsonMessageAdapter {



    @Override
    public boolean supportInternal(MessageHandler handler) {
        return handler instanceof UserAuthenticationMessageHandler;
    }

    @Override
    protected void handleInternal(ChannelHandlerContext ctx, TextWebSocketFrame msg, MessageHandler handler) throws Exception {
        //TODO 处理JSON格式的消息
        log.info("处理JSON格式的消息");
        //适配器负责将传入的参数进行转换，转换成对应MessageHandler 能够处理的

        String text = msg.text();
        log.info("TextWebSocketFrame text :{}",text);
        //当消息内容不为空是进行解析
        TransportMessageOuterClass.TransportMessage.Builder builder = TransportMessageOuterClass.TransportMessage.newBuilder();
        TransportMessageOuterClass.TransportMessage transportMessage = builder.build();
        if(!StringUtils.isEmpty(text)){
            //解析消息
            JSONObject jsonObject = (JSONObject) JSONObject.parse(text);
            String jsonString = jsonObject.toJSONString();

            log.info("解析后的JSONObject String");
            JsonFormat.parser().merge(jsonString,builder);

            //提取 消息体 messageBody
            String messageBody = jsonObject.getString("messageBody");

            log.info("messageBody:{}",messageBody);

            UserAuthenticationMessageHandler userAuthenticationHandler= (UserAuthenticationMessageHandler) handler;



            userAuthenticationHandler.handle(ctx, transportMessage,messageBody);

        }





    }

}

