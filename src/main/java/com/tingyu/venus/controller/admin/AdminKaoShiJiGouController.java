package com.tingyu.venus.controller.admin;

import com.tingyu.venus.common.CommonResult;
import com.tingyu.venus.service.KaoShiJiGouService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author essionshy
 * @Create 2020/11/11 22:06
 * @Version venus-server
 */
@RestController
@RequestMapping("api/admin/kaoshijigou")
public class AdminKaoShiJiGouController {

    @Autowired
    private KaoShiJiGouService kaoShiJiGouService;

    @ApiOperation("导入四川省考试机构信息")
    @PostMapping("import")
    public CommonResult importSheBaoData( MultipartFile file){

        boolean isSuccess =   kaoShiJiGouService.importData(file);
        if(isSuccess){
            return CommonResult.ok();
        }else {
            return CommonResult.error();
        }
    }

    @ApiOperation("删除四川省考试机构数据")
    @GetMapping("remove")
    public CommonResult remove(){

        boolean isSuccess = kaoShiJiGouService.remove();
        if(isSuccess){
            return CommonResult.ok();
        }else {
            return CommonResult.error();
        }

    }


}
