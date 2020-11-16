package com.tingyu.venus.netty;

import com.tingyu.venus.netty.websocket.handler.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/15 16:23
 * @Version venus-server
 */

@Component
@Slf4j
public class MessageHandlerExecutionChain implements ApplicationContextAware, InitializingBean {


    private static final List<MessageHandler> EMPTY_MESSAGE_HANDLE_LIST = new ArrayList<>(0);

    private MessageHandler handler;

    private ApplicationContext context;

    private List<MessageHandler> messageHandlers;


    /**
     * 初始化MessageHandlerChain责任链
     */
    public MessageHandler getHandler() {

        return this.handler;
    }

    private void initMessageHandlers() {
        this.messageHandlers = new ArrayList<>();
        String[] beanNames = context.getBeanNamesForType(MessageHandler.class);

        if (beanNames.length > 0) {
            for (String beanName : beanNames) {
                MessageHandler bean = (MessageHandler) context.getBean(beanName);
                messageHandlers.add(bean);

            }
        } else {
            //确保不为null
            this.messageHandlers = EMPTY_MESSAGE_HANDLE_LIST;
        }


        //构建MessageHandleExecutionChain 执行链
        buildExecutionChain(this.messageHandlers);
        this.handler = this.messageHandlers.get(0);

    }

    private void buildExecutionChain(List<MessageHandler> handlerList) {
        for (int i = 0; i < handlerList.size(); i++) {

            if (i == handlerList.size() - 1) {
                handlerList.get(i).nextMessageHandler(handlerList.get(0));
            } else {
                handlerList.get(i).nextMessageHandler(handlerList.get(i + 1));
            }

        }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;

        log.info("application context {}", applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //调用getHandler()方法时进行初始化，如果在ExecutionChain构造方法中初始化，还没有注入Spring容器，此时无法获取到ApplicationContext
        //会有空指针异常
        initMessageHandlers();
    }
}
