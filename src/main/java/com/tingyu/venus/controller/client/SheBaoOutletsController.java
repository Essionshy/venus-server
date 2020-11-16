package com.tingyu.venus.controller.client;

import com.tingyu.venus.common.CommonResult;
import com.tingyu.venus.service.SheBaoOutletsService;
import com.tingyu.venus.vo.SheBaoOutletsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/12 11:31
 * @Version venus-server
 */
@RestController
@RequestMapping("api/client/shebao")
public class SheBaoOutletsController {


    @Autowired
    private SheBaoOutletsService sheBaoOutletsService;
    @GetMapping("outlets/{areaName}")
    public CommonResult getAddress(@PathVariable("areaName") String areaName){

        List<SheBaoOutletsVo> outlets = sheBaoOutletsService.getOutletsByAreaName(areaName);
        return CommonResult.ok().data("items",outlets);

    }

}
