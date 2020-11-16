package com.tingyu.venus.service;

import com.tingyu.venus.form.LoginForm;
import com.tingyu.venus.form.RegisterForm;

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
    String login(String username,String password);

    String login(LoginForm loginForm);

    boolean register(RegisterForm registerForm);
}
