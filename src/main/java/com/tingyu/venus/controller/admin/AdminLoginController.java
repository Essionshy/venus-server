package com.tingyu.venus.controller.admin;

import com.tingyu.venus.common.CommonResult;
import com.tingyu.venus.form.LoginForm;
import com.tingyu.venus.service.LoginService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author essionshy
 * @Create 2020/10/21 15:25
 * @Version venus-server
 */
@RestController
@RequestMapping("api/admin")
@Slf4j
public class AdminLoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("系统管理登录验证")
    @PostMapping(value = "/login")
    public CommonResult login(LoginForm loginForm, HttpServletRequest request){
        log.info("登录请求 {}",loginForm);
        Map<String,Object> map = loginService.login(loginForm);
        return CommonResult.ok().data(map);

    }

}
