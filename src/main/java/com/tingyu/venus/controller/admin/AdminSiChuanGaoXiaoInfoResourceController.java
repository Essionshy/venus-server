package com.tingyu.venus.controller.admin;

import com.tingyu.venus.common.CommonResult;
import com.tingyu.venus.service.SiChuanGaoXiaoInfoResourceService;
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
@RequestMapping("api/admin/scsgxxxxzy")
public class AdminSiChuanGaoXiaoInfoResourceController {

    @Autowired
    private SiChuanGaoXiaoInfoResourceService siChuanGaoXiaoInfoResourceService;

    @ApiOperation("导入四川省高校信息资源")
    @PostMapping("import")
    public CommonResult importData(MultipartFile file){

        boolean isSuccess =   siChuanGaoXiaoInfoResourceService.importData(file);
        if(isSuccess){
            return CommonResult.ok();
        }else {
            return CommonResult.error();
        }
    }

    @ApiOperation("删除四川省高校信息")
    @GetMapping("remove")
    public CommonResult remove(){

        boolean isSuccess = siChuanGaoXiaoInfoResourceService.remove();
        if(isSuccess){
            return CommonResult.ok();
        }else {
            return CommonResult.error();
        }

    }


}
