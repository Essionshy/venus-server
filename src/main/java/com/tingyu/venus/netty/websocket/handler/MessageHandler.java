package com.tingyu.venus.netty.websocket.handler;

import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;


/**
 * @Author essionshy
 * @Create 2020/11/15 12:24
 * @Version venus-server
 *
 * MessageHandler 处理 TextWebSocketFrame 转换为 protobuf 传输对象
 *
 */
public interface MessageHandler {


    /**
     * 设置下一个handler
     * @param handler
     * @return
     */
    void nextMessageHandler(MessageHandler handler);

    /**
     * 根据责任链模式获取实际的 MessageHandler
     * @param messageType
     * @return
     */
    MessageHandler getHandler(TransportMessageOuterClass.MessageType messageType);





}
