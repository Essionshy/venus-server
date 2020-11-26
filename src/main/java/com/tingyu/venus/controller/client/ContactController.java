package com.tingyu.venus.controller.client;

import com.tingyu.venus.common.CommonResult;
import com.tingyu.venus.service.UserService;
import com.tingyu.venus.vo.UserVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/21 22:49
 * @Version venus-server
 */
@RestController
@RequestMapping("api/client/contact")
@Slf4j
public class ContactController {

    @Autowired
    private UserService userService;

    @ApiOperation("根据手机号查询该用户所有联系人列表")
    @GetMapping("list/{phone}")
    public CommonResult listContactsByUserId(@PathVariable("phone") String phone){

        List<UserVo> contacts = userService.findContactsByUserPhone(phone);

        return CommonResult.ok().data("items",contacts);

    }
}
