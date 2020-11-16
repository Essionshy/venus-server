package com.tingyu.venus.service;

import com.tingyu.venus.vo.AreaVo;
import com.tingyu.venus.vo.CityVo;
import com.tingyu.venus.vo.ProvinceVo;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/12 10:34
 * @Version venus-server
 */
public interface AddressService {
    List<ProvinceVo> listProvince();

    List<CityVo> listCityByProvinceCode(String provinceCode);

    List<AreaVo> listAreaByCityCode(String cityCode);

    List<AreaVo> listAreaByCityName(String cityName);
}
