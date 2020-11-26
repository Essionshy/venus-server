package com.tingyu.venus.netty.socket.processor;

import com.tingyu.venus.exception.ResultException;
import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/18 8:28
 * @Version venus-server
 * <p>
 * 处理Protobuf类型的消息，采用抽象父类责任链的模式来实现
 */
public abstract class AbstractProtobufMessageHandler implements ProtobufMessageHandler {
    private static List<TransportMessageOuterClass.MessageType> supportMessageTypes = new ArrayList<>();
    public ProtobufMessageHandler handler;


    static {
        supportMessageTypes.add(TransportMessageOuterClass.MessageType.USER_ONLINE_NOTICE);
        supportMessageTypes.add(TransportMessageOuterClass.MessageType.USER_OFFLINE_NOTICE);
        supportMessageTypes.add(TransportMessageOuterClass.MessageType.CHAT_MSG_NOTICE);
        supportMessageTypes.add(TransportMessageOuterClass.MessageType.ADD_CONTACT_NOTICE);
        supportMessageTypes.add(TransportMessageOuterClass.MessageType.DELETE_CONTACT_NOTICE);
        supportMessageTypes.add(TransportMessageOuterClass.MessageType.CREATE_GROUP_CHAT_NOTICE);
        //TODO 每添加一个MessageType 将其添加进来

    }

    public void setHandler(ProtobufMessageHandler handler) {
        this.handler = handler;
    }


    @Override
    public void handle(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg) {
        //TODO 是否支持处理该消息类型
        if (!supportMessageTypes.contains(msg.getMessageType())) {
            throw new ResultException(200, "can not support message type...");
        }

        handleInternal(ctx, msg);
    }

    //具体如何处理交给子类去实现
    protected abstract void handleInternal(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg);


}
