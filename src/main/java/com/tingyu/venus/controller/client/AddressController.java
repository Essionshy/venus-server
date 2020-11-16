package com.tingyu.venus.controller.client;

import com.tingyu.venus.common.CommonResult;
import com.tingyu.venus.service.AddressService;
import com.tingyu.venus.vo.AreaVo;
import com.tingyu.venus.vo.CityVo;
import com.tingyu.venus.vo.ProvinceVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/12 10:25
 * @Version venus-server
 */
@RestController
@RequestMapping("api/client/address")
public class AddressController {


    @Autowired
    private AddressService addressService;

    @ApiOperation("获取省级行政列表")
    @GetMapping("province/list")
    public CommonResult listProvince(){
        List<ProvinceVo> provinceVos = addressService.listProvince();
        return CommonResult.ok().data("provinceList",provinceVos);
    }

    @ApiOperation("根据省级行政代码获取下辖地级市列表")
    @GetMapping("city/list/{provinceCode}")
    public CommonResult listCityByProvinceCode(@PathVariable("provinceCode")String provinceCode){
        List<CityVo> cityList = addressService.listCityByProvinceCode(provinceCode);
        return CommonResult.ok().data("cityList",cityList);
    }


    @ApiOperation("根据市级行政代码获取下辖县级列表")
    @GetMapping("area/list/code/{cityCode}")
    public CommonResult listAreaByCityCode(@PathVariable("cityCode") String cityCode){
        List<AreaVo> areaList = addressService.listAreaByCityCode(cityCode);
        return CommonResult.ok().data("areaList",areaList);
    }

    @ApiOperation("根据市级行政代码获取下辖县级列表")
    @GetMapping("area/list/name/{cityName}")
    public CommonResult listAreaByCityName(@PathVariable("cityName") String cityName){
        List<AreaVo> areaList = addressService.listAreaByCityName(cityName);
        return CommonResult.ok().data("areaList",areaList);
    }

}
