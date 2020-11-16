package com.tingyu.venus.service;

import com.tingyu.venus.entity.SheBaoServiceOutletsEntity;
import com.tingyu.venus.vo.SheBaoOutletsVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/11 21:59
 * @Version venus-server
 */
public interface SheBaoOutletsService {


    void save(SheBaoServiceOutletsEntity entity);

    boolean importData(MultipartFile file);

    boolean remove();

    List<SheBaoOutletsVo> getOutletsByAreaName(String areaName);
}
