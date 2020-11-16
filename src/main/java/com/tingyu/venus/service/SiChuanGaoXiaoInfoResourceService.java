package com.tingyu.venus.service;

import com.tingyu.venus.entity.SiChuanGaoXiaoInfoResourceEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author essionshy
 * @Create 2020/11/11 21:59
 * @Version venus-server
 */
public interface SiChuanGaoXiaoInfoResourceService {


    void save(SiChuanGaoXiaoInfoResourceEntity entity);

    boolean importData(MultipartFile file);

    boolean remove();

}
