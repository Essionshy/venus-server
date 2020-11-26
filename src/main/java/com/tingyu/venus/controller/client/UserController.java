package com.tingyu.venus.controller.client;

import com.tingyu.venus.common.CommonResult;
import com.tingyu.venus.service.UserService;
import com.tingyu.venus.vo.UserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author essionshy
 * @Create 2020/11/22 14:56
 * @Version venus-server
 */
@RestController
@RequestMapping("api/client/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("根据手机号查询用户")
    @GetMapping("get/{userPhone}")
    public CommonResult get(@PathVariable("userPhone") String userPhone){
        UserVo user = userService.getByPhone(userPhone);

        return  CommonResult.ok().data("item",user);
    }
}
