package com.tingyu.venus.netty.util;


import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通道管理器
 *
 * @Author essionshy
 * @Create 2020/11/20 10:26
 * @Version venus-server
 */

@Slf4j
public final class NettyChannelUtil {

    //负责维护所有上线用户

    /**
     * key: 用户手机号
     * value: 通道
     */
    public static Map<String, ChannelHandlerContext> USER_ID_CHANNEL_GROUP = new ConcurrentHashMap<>();

    /**
     * 客户端与服务端建立的连接中，并不都包含用户信息，在移除通道时，需要知道用户所在的通道，因此需要事件保存其映射关系
     * 尝试通过channelId去获取userId，如果userId存在，即为用户所在通道，因此用户通道与客户端保存一一对应关系，所以可以精确通知
     * 客户端，服务器可以利用该通道进行消息推送
     * key: 通道ID
     * value: 用户ID或手机号
     */
    public static Map<String, String> CHANNEL_ID_USER_ID_GROUP = new ConcurrentHashMap<>();


    /**
     * 保存
     *
     * @param ctx
     * @param userId
     */
    public static void saveUserChannel(ChannelHandlerContext ctx, String userId) {
        String channelId = getChannelId(ctx);

        String user = CHANNEL_ID_USER_ID_GROUP.get(channelId);
        if (user != null) {
            //将旧的通道移除
            CHANNEL_ID_USER_ID_GROUP.remove(channelId);
        }

        ChannelHandlerContext channelHandlerContext = USER_ID_CHANNEL_GROUP.get(userId);
        if (channelHandlerContext != null) {
            //将之前的通道移除
            USER_ID_CHANNEL_GROUP.remove(userId);
        }

        //添加新的通道信息
        CHANNEL_ID_USER_ID_GROUP.put(channelId, userId);
        USER_ID_CHANNEL_GROUP.put(userId, ctx);

    }


    /**
     * 清除通道信息
     */
    public static void removeChannel(ChannelHandlerContext ctx) {
        //获取当前通道ID
        String channelId = getChannelId(ctx);
        //获取通道ID获取对应的用户ID
        String userId = getUserIdByChannelId(channelId);

        if (userId != null) {
            //根据用户ID移除对应的通道
            USER_ID_CHANNEL_GROUP.remove(userId);
            CHANNEL_ID_USER_ID_GROUP.remove(channelId);
            //通知客户端，联系人已下线
            log.info("用户{}已下线", userId);
        }


    }


    /**
     * 当客户端退出时，清除数据
     *
     * @param ctx
     */
    public static void exit(ChannelHandlerContext ctx) {
        removeChannel(ctx);
    }


    /**
     * 根据通道ID获取用户ID
     *
     * @param channelId
     * @return
     */
    private static String getUserIdByChannelId(String channelId) {
        return CHANNEL_ID_USER_ID_GROUP.get(channelId);
    }

    public static String getChannelId(ChannelHandlerContext ctx) {
        return ctx.channel().id().asShortText();
    }
}
