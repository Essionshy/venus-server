package com.tingyu.venus.netty.socket.handler;

import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.protobuf.UserOfflineNotice;
import com.tingyu.venus.netty.socket.processor.AbstractProtobufMessageHandler;
import com.tingyu.venus.netty.util.MessageUtil;
import com.tingyu.venus.netty.util.NettyChannelUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author essionshy
 * @Create 2020/11/22 9:22
 * @Version venus-server
 */
@Slf4j
@Service
public class UserOfflineNoticeHandler extends AbstractProtobufMessageHandler {
    @Override
    protected void handleInternal(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg) {
        if(msg.getMessageType().equals(TransportMessageOuterClass.MessageType.USER_OFFLINE_NOTICE)){
            //获取消息

            try{

                UserOfflineNotice.UserOfflineNoticeMessage offlineNoticeMessage = msg.getMessageBody().unpack(UserOfflineNotice.UserOfflineNoticeMessage.class);

                //获取用户信息
                String userPhone = offlineNoticeMessage.getUserPhone();
                //获取用户的通道信息
                ChannelHandlerContext userContext = NettyChannelUtil.USER_ID_CHANNEL_GROUP.get(userPhone);
                if(userContext!=null){

                    UserOfflineNotice.UserOfflineNoticeMessage.Reason reason = offlineNoticeMessage.getReason();

                    if(reason.equals(UserOfflineNotice.UserOfflineNoticeMessage.Reason.LOGIN_OUT)){
                        //通知客户端，消息已签收
                        MessageUtil.sendSocketMsg(ctx, TransportMessageOuterClass.MessageType.MESSAGE_RECEIVED_ACK,null,null,null);
                        //清除当前用户通信信息
                        NettyChannelUtil.removeChannel(userContext);

                    }else{
                        //TODO 其他原因



                    }



                }else{
                    log.info("用户不在线");
                }


            }catch (Exception e){
                e.printStackTrace();
                log.error("消息解析异常");
            }



        }else{
            super.handler.handle(ctx,msg);
        }
    }
}
