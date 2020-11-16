package com.tingyu.venus.test.mapper;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 核心业务处理线程池，单例模式
 */
public class CoreThreadPool extends ThreadPoolExecutor {
    //1.构造方法私有化

    private static volatile CoreThreadPool instance;

    //线程池的七大参数

    /**
     * int corePoolSize,
     * int maximumPoolSize,
     * long keepAliveTime,
     * TimeUnit unit,
     * BlockingQueue<Runnable> workQueue,
     * ThreadFactory threadFactory,
     * RejectedExecutionHandler handler
     */
    private static final int corePoolSize = Runtime.getRuntime().availableProcessors();
    private static final int maximumPoolSize = corePoolSize << 1;

    private static final long keepAliveTime = 60L;
    private static final int workQueueSize = 100;


    private CoreThreadPool() {
        super(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingDeque<>(workQueueSize));
    }

    //2.对外提供实例接口

    public static CoreThreadPool newInstance() {


        if (instance == null) {

            synchronized (CoreThreadPool.class) {
                if (instance == null) {
                    instance = new CoreThreadPool();
                }
            }

        }
        return instance;

    }



}
