package com.tingyu.venus.netty.socket.processor;

/**
 * @Author essionshy
 * @Create 2020/11/18 8:35
 * @Version venus-server
 */

import com.tingyu.venus.exception.ResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 构建ProtobufMessageHandlerExecutionChain 执行环形链
 */
@Component
public class ProtobufMessageHandlerExecutionChain {

    private ProtobufMessageHandler handler;

    private List<ProtobufMessageHandler> protobufMessageHandlers;
    //获取IOC容器上下文对象
    @Autowired
    private ApplicationContext context;

    //初始化方法
    @PostConstruct
    public void init() {
        //初始化protobufMessageHandlers集合
        initProtobufMessageHandlers();
        //构建环形链
        buildProtobufMessageHandlerExecutionChain();
    }

    /**
     * 将protobufMessageHandlers 集合中MessageHandler构建成为环形链表
     */
    private void buildProtobufMessageHandlerExecutionChain() {
        if (this.protobufMessageHandlers == null || this.protobufMessageHandlers.size() <= 0) {
            //
            throw new ResultException(200, "没有ProtobufMessage处理器");
        }

        for (int i = 0; i <this.protobufMessageHandlers.size() ; i++) {
            //最后一个指向第一个
            if(i==this.protobufMessageHandlers.size()-1){
                this.protobufMessageHandlers.get(i).setHandler(this.protobufMessageHandlers.get(0));
            }else{
                this.protobufMessageHandlers.get(i).setHandler(this.protobufMessageHandlers.get(i+1));
            }
        }
        //将第一个Handler返回,供外部调用
        this.handler=this.protobufMessageHandlers.get(0);
    }

    /**
     * 从Spring IOC容器中获取所有的Handler,并将其保存在protobufMessageHandlers 集合中
     */
    private void initProtobufMessageHandlers() {
        //TODO 是否可以按照一定的优先级顺序来加载ProtobufMessageHandler
        protobufMessageHandlers = null;
        String[] beanNames = context.getBeanNamesForType(ProtobufMessageHandler.class);
        if (beanNames.length > 0) {
            protobufMessageHandlers = new ArrayList<>();
            for (String beanName : beanNames) {
                ProtobufMessageHandler bean = context.getBean(beanName, ProtobufMessageHandler.class);
                protobufMessageHandlers.add(bean);
            }
        }
    }

    public ProtobufMessageHandler getHandler() {
        return this.handler;
    }
}
