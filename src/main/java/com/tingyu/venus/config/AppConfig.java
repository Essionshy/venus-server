package com.tingyu.venus.config;

import com.tingyu.venus.filter.LoginAuthenticationFilter;
import com.tingyu.venus.listener.StartupListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

/**
 * @Author essionshy
 * @Create 2020/11/10 21:46
 * @Version venus-server
 */
@EnableAspectJAutoProxy
@Configuration
@ComponentScan(value = "com.tingyu.venus")
public class AppConfig {




    /**
     * 注册启动监听器
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<StartupListener> startupListener(){
        ServletListenerRegistrationBean<StartupListener> startupListener = new ServletListenerRegistrationBean<>();
        startupListener.setListener(new StartupListener());
        return startupListener;
    }


    /**
     * 注册登录验证过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean<LoginAuthenticationFilter> loginAuthenticationFilter(){

        FilterRegistrationBean<LoginAuthenticationFilter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new LoginAuthenticationFilter());

        /**
         * 设置拦截规则
         *  需要用户登录才能进行访问的资源需要加入
         */
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/api/user/**"));
        return filterRegistrationBean;
    }



}
