package com.tingyu.venus.service;

import com.tingyu.venus.form.LoginForm;
import com.tingyu.venus.form.RegisterForm;

import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/11/10 22:06
 * @Version venus-server
 */
public interface LoginService {

    /**
     * 根据用户名
     * @param username
     * @param password
     * @return
     */
    Map<String,Object> login(String username,String password);

    Map<String,Object> login(LoginForm loginForm);

    boolean register(RegisterForm registerForm);
}
