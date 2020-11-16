package com.tingyu.venus.properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/11/10 22:39
 * @Version venus-server
 */
@Component
public class JWTProperties implements InitializingBean {

    @Value("${jwt.expire}")
    private long jwt_expire;

    @Value("${jwt.secret}")
    private String jwt_secret;


    public static long JWT_EXPIRE;
    public static String JWT_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        JWT_EXPIRE=this.jwt_expire;
        JWT_SECRET=this.jwt_secret;

    }
}
