package com.tingyu.venus.controller.client;

import com.tingyu.venus.common.CommonResult;
import com.tingyu.venus.service.MemberService;
import com.tingyu.venus.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author essionshy
 * @Create 2020/11/22 10:37
 * @Version venus-server
 */
@RestController
@RequestMapping("api/client/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("save")
    public CommonResult save(@RequestBody MemberVo memberVo){
        memberService.save(memberVo);

        return CommonResult.ok();
    }
}
