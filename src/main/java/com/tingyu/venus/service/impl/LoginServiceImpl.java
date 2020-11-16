package com.tingyu.venus.service.impl;

import com.tingyu.venus.common.BizCode;
import com.tingyu.venus.exception.ResultException;
import com.tingyu.venus.form.LoginForm;
import com.tingyu.venus.form.RegisterForm;
import com.tingyu.venus.service.LoginService;
import com.tingyu.venus.service.UserService;
import com.tingyu.venus.utils.JwtUtils;
import com.tingyu.venus.utils.MD5Utils;
import com.tingyu.venus.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author essionshy
 * @Create 2020/11/10 22:09
 * @Version venus-server
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Override
    public String login(String phone, String password) {

        UserVo user = userService.getByPhone(phone);

        //验证用户是否存在
        if (null == user) {
            throw new ResultException(BizCode.USER_NOT_EXISTS.getCode(),
                    BizCode.USER_NOT_EXISTS.getMessage());
        }

        if(!user.getPassword().equals(MD5Utils.encrypt(password))){
            throw new ResultException(BizCode.PASSWORD_ERROR.getCode(),
                    BizCode.PASSWORD_ERROR.getMessage());
        }

        //验证成功，根据用户手机号码生成token返回给客户端
        String token = JwtUtils.create(user.getId(),user.getUsername());
        return token;
    }

    @Override
    public String login(LoginForm loginForm) {
        return this.login(loginForm.getPhone(), loginForm.getPassword());
    }

    @Override
    public boolean register(RegisterForm registerForm) {
        return userService.save(registerForm);
    }
}
