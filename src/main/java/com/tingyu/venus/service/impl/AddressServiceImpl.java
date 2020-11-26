package com.tingyu.venus.service.impl;

import com.tingyu.venus.dao.AreaRepository;
import com.tingyu.venus.dao.CityRepository;
import com.tingyu.venus.dao.ProvinceRepository;
import com.tingyu.venus.entity.Area;
import com.tingyu.venus.entity.City;
import com.tingyu.venus.entity.Province;
import com.tingyu.venus.service.AddressService;
import com.tingyu.venus.vo.AreaVo;
import com.tingyu.venus.vo.CityVo;
import com.tingyu.venus.vo.ProvinceVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author essionshy
 * @Create 2020/11/12 10:35
 * @Version venus-server
 */
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {


    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public List<ProvinceVo> listProvince() {

        List<Province> provinces = provinceRepository.findAll();

        List<ProvinceVo> provinceVoList = provinces.stream().map(province -> {
            ProvinceVo provinceVo = new ProvinceVo();
            BeanUtils.copyProperties(province, provinceVo);
            return provinceVo;

        }).collect(Collectors.toList());

        return provinceVoList;
    }

    @Override
    public List<CityVo> listCityByProvinceCode(String provinceCode) {

        City city = new City();
        city.setProvinceCode(provinceCode);
        Example<City> example = Example.of(city);
        List<City> cityList = cityRepository.findAll(example);

        List<CityVo> cityVoList = cityList.stream().map(city1 -> {
            CityVo cityVo = new CityVo();
            BeanUtils.copyProperties(city1, cityVo);
            return cityVo;
        }).collect(Collectors.toList());

        return cityVoList;
    }

    @Override
    public List<AreaVo> listAreaByCityCode(String cityCode) {

        Area area = new Area();
        area.setCityCode(cityCode);
        Example<Area> ex = Example.of(area);

        List<Area> areaList = areaRepository.findAll(ex);

        List<AreaVo> areaVoList = areaList.stream().map(area1 -> {
            AreaVo areaVo = new AreaVo();
            BeanUtils.copyProperties(area1, areaVo);
            return areaVo;
        }).collect(Collectors.toList());
        return areaVoList;

    }

    @Override
    public List<AreaVo> listAreaByCityName(String cityName) {
        City city = new City();
        city.setCityName(cityName);
        Example<City> example = Example.of(city);
        Optional<City> one = cityRepository.findOne(example);
        try {
            City city1 = one.get();
            return this.listAreaByCityCode(city1.getCityCode());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
