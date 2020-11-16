package com.tingyu.venus.controller.client;

import com.tingyu.venus.common.CommonResult;
import com.tingyu.venus.form.LoginForm;
import com.tingyu.venus.form.RegisterForm;
import com.tingyu.venus.service.LoginService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 通过手机app每天写入数据，由PC端进行数据的处理，分析，生成报表，导出excel
 *
 * @Author essionshy
 * @Create 2020/11/11 8:21
 * @Version venus-server
 */
@Slf4j
@RestController
@RequestMapping("api/client/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("Android 端请求登录")
    @PostMapping(value = "/login")
    public CommonResult login(LoginForm loginForm){

        String token = loginService.login(loginForm);
        log.info("token",token);
        return CommonResult.ok().data("token",token);

    }

    @ApiOperation("Android 端用户注册请求")
    @PostMapping(value = "/register")
    public CommonResult register(RegisterForm registerForm){

        boolean isSuccess = loginService.register(registerForm);
        if(isSuccess){
            return CommonResult.ok();
        }else {
            return CommonResult.error();
        }


    }
}
