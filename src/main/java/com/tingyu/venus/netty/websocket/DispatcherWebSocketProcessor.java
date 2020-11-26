package com.tingyu.venus.netty.websocket;

import com.alibaba.fastjson.JSONObject;
import com.tingyu.venus.exception.ResultException;
import com.tingyu.venus.netty.MessageHandlerExecutionChain;
import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.websocket.adapter.MessageHandlerAdapter;
import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/16 10:24
 * @Version venus-server
 */
@Service
@Slf4j
public class DispatcherWebSocketProcessor {


    private MessageHandler processMessageHandler;

    private MessageHandlerAdapter mha;

    private List<MessageHandlerAdapter> messageHandlerAdapters;
    @Autowired
    private ApplicationContext context;

    @Autowired
    private MessageHandlerExecutionChain executionChain;


    /**
     * @param ctx
     * @param msg 客户端发送的消息，类比 request
     */
    public void process(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        log.info("websocket server is ready to read...");
        initMessageHandlerAdapters(context);
        //根据消息类型获取对应的handler
        /**
         * 根据消息类型，找到对应的消息处理器
         */
        processMessageHandler = getMessageHandler(msg);
        mha = getMessageHandlerAdapter(processMessageHandler);
        //实际处理消息，由适配器解析参数，将其传入对应的MessageHandler进行处理
        try {
            mha.handle(ctx, msg, processMessageHandler);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("类型转换异常");
        }


    }


    private void initMessageHandlerAdapters(ApplicationContext context) {
        //清除原来的适配器集合
        this.messageHandlerAdapters = null;
        String[] beanNames = context.getBeanNamesForType(MessageHandlerAdapter.class);
        if (beanNames.length > 0) {
            this.messageHandlerAdapters = new ArrayList<>(beanNames.length);

            for (String beanName : beanNames) {
                this.messageHandlerAdapters.add((MessageHandlerAdapter) context.getBean(beanName));
            }
        }
    }

    private MessageHandler getMessageHandler(TextWebSocketFrame msg) {

        //TODO 根据消息类型获取对应的handler
        MessageHandler handler = null;
        String text = msg.text();
        log.info("客户端发送的消息：{}", text);
        //通过传入的参数构建TransportMessage对象


        //将传入文本转换为JSON对象
        TransportMessageOuterClass.MessageType messageType = null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(text);
            messageType = jsonObject.getObject("messageType", TransportMessageOuterClass.MessageType.class);
        } catch (Exception e) {
            log.error("websocket 参数异常：{}", e.getMessage());
        }
        //如果messageType为null,默认交给文本消息处理器执行
        handler = executionChain.getHandler().getHandler(messageType);//TODO 如果messageType 没有对应的处理器，会导致 StackOverFlowError
        return handler;


    }


    /**
     * 根据handler找到对应的适配器
     *
     * @param handler
     * @return
     */
    private MessageHandlerAdapter getMessageHandlerAdapter(MessageHandler handler) {

        if (this.messageHandlerAdapters != null) {
            for (MessageHandlerAdapter messageHandlerAdapter : this.messageHandlerAdapters) {

                if (messageHandlerAdapter.support(handler)) {
                    return messageHandlerAdapter;
                }

            }

        }

        //没有对应的适配器，则抛出异常
        throw new ResultException(200, "没有找到适配器");

    }


}
