package com.tingyu.venus.service;

import com.tingyu.venus.entity.KaoShiJiGouEntity;
import com.tingyu.venus.vo.KaoShiJiGouVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/11 21:59
 * @Version venus-server
 */
public interface KaoShiJiGouService {


    void save(KaoShiJiGouEntity entity);

    boolean importData(MultipartFile file);

    boolean remove();

    List<KaoShiJiGouVo> getOutletsByAreaName(String areaName);
}
