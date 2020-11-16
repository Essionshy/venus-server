package com.tingyu.venus.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author essionshy
 * @Create 2020/11/11 9:45
 * @Version venus-server
 */
@Slf4j
public class StartupListener implements ServletContextListener {



    @Override
    public void contextInitialized(ServletContextEvent sce) {

        String serverInfo = sce.getServletContext().getServerInfo();

        String ctx = sce.getServletContext().getContextPath();

        log.info("Venus 后台管理系统：http://localhost:9001"+ctx+"/index.html");

        log.info("serverInfo:{}",serverInfo);

        log.info("tomcat start up successfully...{}");
        sce.getServletContext().setAttribute("ctx", ctx);

    }
}
