package com.tingyu.venus.netty.socket.handler;

import com.tingyu.venus.netty.protobuf.ContactDeleteNotice;
import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.socket.processor.AbstractProtobufMessageHandler;
import com.tingyu.venus.netty.util.MessageUtil;
import com.tingyu.venus.netty.util.NettyChannelUtil;
import com.tingyu.venus.service.ContactService;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author essionshy
 * @Create 2020/11/23 23:07
 * @Version venus-server
 */
@Slf4j
@Service
public class ContactDeleteNoticeMessageHandler extends AbstractProtobufMessageHandler {

    @Autowired
    private ContactService contactService;

    @Override
    protected void handleInternal(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg) {
        if(msg.getMessageType().equals(TransportMessageOuterClass.MessageType.DELETE_CONTACT_NOTICE)){

            try{

                ContactDeleteNotice.ContactDeleteNoticeMessage message = msg.getMessageBody().unpack(ContactDeleteNotice.ContactDeleteNoticeMessage.class);

                String from = message.getFrom();
                String to = message.getTo();

                //删除数据库中记录
                contactService.remove(from,to);


                //获取联系人
                ChannelHandlerContext toContext = NettyChannelUtil.USER_ID_CHANNEL_GROUP.get(to);
                if(toContext!=null){

                    //通知联系人已被删除
                    MessageUtil.sendWebSocketMsg(toContext, TransportMessageOuterClass.MessageType.DELETE_CONTACT_NOTICE,null,null,message);
                }

                //通知客户端消息已经收到
                MessageUtil.sendSocketMsg(ctx, TransportMessageOuterClass.MessageType.MESSAGE_RECEIVED_ACK,null,null,null);



            }catch (Exception e){
                e.printStackTrace();
                log.error("解析异常");
            }




        }else{
            super.handler.handle(ctx,msg);
        }
    }
}
