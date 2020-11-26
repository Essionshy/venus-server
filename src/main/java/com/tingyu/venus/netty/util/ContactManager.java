package com.tingyu.venus.netty.util;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author essionshy
 * @Create 2020/11/20 14:12
 * @Version venus-server
 */
public class ContactManager {

    private static Map<String, List<Map<String, ChannelHandlerContext>>> onlineContacts=new ConcurrentHashMap<>();


}
