package com.tingyu.venus.controller.admin;

import com.tingyu.venus.common.CommonResult;
import com.tingyu.venus.service.SheBaoOutletsService;
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
@RequestMapping("api/admin/shebao")
public class AdminSheBaoOutletsController {

    @Autowired
    private SheBaoOutletsService sheBaoOutletsService;

    @ApiOperation("导入四川省社保卡人社部门服务网点")
    @PostMapping("import")
    public CommonResult importSheBaoData( MultipartFile file){

        boolean isSuccess =   sheBaoOutletsService.importData(file);
        if(isSuccess){
            return CommonResult.ok();
        }else {
            return CommonResult.error();
        }
    }

    @ApiOperation("删除四川省社保卡人社部门服务网点")
    @GetMapping("remove")
    public CommonResult remove(){

        boolean isSuccess = sheBaoOutletsService.remove();
        if(isSuccess){
            return CommonResult.ok();
        }else {
            return CommonResult.error();
        }

    }


}
