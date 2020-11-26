package com.tingyu.venus.netty.websocket.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.tingyu.venus.netty.protobuf.TransportMessageOuterClass;
import com.tingyu.venus.netty.protobuf.UserOnlineNotice;
import com.tingyu.venus.netty.util.NettyChannelUtil;
import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import com.tingyu.venus.service.UserService;
import com.tingyu.venus.vo.UserVo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author essionshy
 * @Create 2020/11/19 19:04
 * @Version venus-server
 */
@Service
@Slf4j
public class UserOnLineNoticeMessageHandler extends AbstractJsonMessageHandler {

    private static final String ON_LINE_USER = "online_user";
    private static final String OFF_LINE_USER = "offline_user";

    @Autowired
    private UserService userService;

    @Override
    protected MessageHandler getHandlerInternal(TransportMessageOuterClass.MessageType messageType) {

        if (messageType.equals(TransportMessageOuterClass.MessageType.USER_ONLINE_NOTICE)) {
            return this;
        } else {
            return super.handler.getHandler(messageType);
        }
    }

    /**
     * 上线通知，服务器负责分发给该用户的所有好友，通知上线
     * @param ctx
     * @param msg
     */


    /**
     * @param ctx
     * @param msg
     * @param contentJsonStr messageBody  JSONString,根据客户端传入的参数解析后获得
     */
    @Override
    protected void handleInternal(ChannelHandlerContext ctx, TransportMessageOuterClass.TransportMessage msg, String contentJsonStr) {
        //解析消息体获取用户信息
        JSONObject messageBody = JSONObject.parseObject(contentJsonStr);
        String userPhone = (String) messageBody.get("userPhone");
        if (userPhone != null) {
            //保存用户通道信息
            NettyChannelUtil.saveUserChannel(ctx,userPhone);
            //TODO 删除
            log.info("CHANNELGROUP size:{}", NettyChannelUtil.USER_ID_CHANNEL_GROUP.size());
            log.info("CHANNELGROUP :{}", NettyChannelUtil.USER_ID_CHANNEL_GROUP);
            log.info("CHANNEL_USER size:{}", NettyChannelUtil.CHANNEL_ID_USER_ID_GROUP.size());
            log.info("CHANNEL_USER :{}", NettyChannelUtil.CHANNEL_ID_USER_ID_GROUP);

            //根据用户ID查询用户列表
            List<UserVo> users = userService.findContactsByUserPhone(userPhone);

            Map<String, List<UserVo>> map = buildContactStatusList(users, NettyChannelUtil.USER_ID_CHANNEL_GROUP);
            //根据用户ID查找出所有在线好友
            if (null != map) {
                List<UserVo> onlineUsers = map.get(ON_LINE_USER);
                //给在线好友发送通知,通知 userPhone上线了
                noticeUser(onlineUsers,userPhone);
            }
        }


    }

    /**
     * 通知所有在线好友，用户已经上线
     *
     * @param onlineUsers
     */
    private void noticeUser(List<UserVo> onlineUsers,String userPhone) {
        Iterator<UserVo> iterator = onlineUsers.iterator();
        while (iterator.hasNext()) {
            UserVo next = iterator.next();
            //通过用户手机号获取通道
            ChannelHandlerContext ctx = NettyChannelUtil.USER_ID_CHANNEL_GROUP.get(next.getPhone());


            //构建上线通知
            //
            UserOnlineNotice.UserOnlineNoticeMessage.Builder onlineNoticeBuilder = UserOnlineNotice.UserOnlineNoticeMessage.newBuilder();
            //为返回的通知消息设值，返回用户名
            onlineNoticeBuilder.setUserPhone(userPhone);
            UserOnlineNotice.UserOnlineNoticeMessage onlineNotice = onlineNoticeBuilder.build();


            //输出JSON格式的上线通知消息
            JSONObject result = new JSONObject();
            String messageType = TransportMessageOuterClass.MessageType.USER_ONLINE_NOTICE.name();

            String message = null;
            try {
                message = JsonFormat.printer().print(onlineNotice);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }

            result.put("messageType", messageType);
            result.put("message", message);


            //通知移动端
            ctx.writeAndFlush(new TextWebSocketFrame(result.toJSONString()));
        }

    }
    private Map<String, List<UserVo>> buildContactStatusList(List<UserVo> users, Map<String, ChannelHandlerContext> channelGroup) {

        if (users == null) {
            return null;
        }
        Map<String, List<UserVo>> map = new HashMap<>(users.size());
        List<UserVo> onLineUsers = new ArrayList<>(); //在线联系人列表
        List<UserVo> offLineUsers = new ArrayList<>();//离线联系人列表

        Iterator<UserVo> iterator = users.iterator();
        while (iterator.hasNext()) {
            UserVo next = iterator.next();
            //判断联系人在线还是离线
            if (channelGroup.containsKey(next.getPhone())) {
                //在线联系人
                onLineUsers.add(next);
            } else {
                //离线联系人
                offLineUsers.add(next);
            }
        }
        map.put(ON_LINE_USER, onLineUsers);
        map.put(OFF_LINE_USER, offLineUsers);
        return map;
    }

}
