package com.tingyu.venus.controller.admin;

import com.tingyu.venus.netty.websocket.handler.impl.TextMessageHandler;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author essionshy
 * @Create 2020/11/11 12:38
 * @Version venus-server
 */
@Controller
@Slf4j
@RequestMapping("api/admin")
public class AdminIndexController {

    @Autowired
    TextMessageHandler textMessageHandler;


    @ApiOperation("请求首页页面")
    @GetMapping("index")
    public String toIndex(){




        return "index";
    }

}
