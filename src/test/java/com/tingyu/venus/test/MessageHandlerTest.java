package com.tingyu.venus.test;

import com.tingyu.venus.VenusServerApplication;
import com.tingyu.venus.netty.MessageHandlerExecutionChain;
import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import com.tingyu.venus.netty.websocket.handler.impl.TextMessageHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Author essionshy
 * @Create 2020/11/15 16:44
 * @Version venus-server
 */
@SpringBootTest
@ContextConfiguration(classes = {VenusServerApplication.class})
@WebAppConfiguration
public class MessageHandlerTest {

    @Autowired
    TextMessageHandler textMessageHandler;

    @Test
    public void testMessageHandlerExecutionChain(){

        MessageHandlerExecutionChain messageHandlerExecutionChain = new MessageHandlerExecutionChain();

        MessageHandler handler = messageHandlerExecutionChain.getHandler();


    }
}
