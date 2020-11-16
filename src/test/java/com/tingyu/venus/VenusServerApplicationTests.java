package com.tingyu.venus;

import com.tingyu.venus.test.mapper.CoreThreadPool;
import com.tingyu.venus.utils.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = {VenusServerApplication.class})
class VenusServerApplicationTests {

    @Test
    void contextLoads() {

        System.out.println(MD5Utils.encrypt("123456"));
    }

    @Test
    public void TestMain() throws IOException {

        CoreThreadPool coreThreadPool = CoreThreadPool.newInstance();

        try{
            for (int i = 0; i <10 ; i++) {
                coreThreadPool.execute(()->{


                    System.out.println(Thread.currentThread().getName()+"\t 线程池执行任务开始");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t 线程池执行任务结束");



                });
                if(coreThreadPool.isShutdown()){
                    System.out.println("线程池已经关闭");
                }else{
                    System.out.println("线程池未关闭");
                }
            }
        }catch (Exception e){

            e.printStackTrace();
        }finally {
              coreThreadPool.shutdown();


        }

        if(coreThreadPool.isShutdown()){
            System.out.println("线程池已经关闭");
        }else{
            System.out.println("线程池未关闭");
        }
        System.in.read();



        System.out.println("============="+(8<<1));
    }

}
