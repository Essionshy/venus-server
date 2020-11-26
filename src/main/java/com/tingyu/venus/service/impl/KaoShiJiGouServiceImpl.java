package com.tingyu.venus.service.impl;

import com.alibaba.excel.EasyExcel;
import com.tingyu.venus.dao.KaoShiJiGouRepository;
import com.tingyu.venus.entity.KaoShiJiGouEntity;
import com.tingyu.venus.excel.KaoShiJiGouData;
import com.tingyu.venus.exception.ResultException;
import com.tingyu.venus.listener.KaoShiJiGouExcelListener;
import com.tingyu.venus.service.KaoShiJiGouService;
import com.tingyu.venus.vo.KaoShiJiGouVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author essionshy
 * @Create 2020/11/11 22:00
 * @Version venus-server
 */
@Service
public class KaoShiJiGouServiceImpl implements KaoShiJiGouService {


    @Autowired
    private KaoShiJiGouRepository kaoShiJiGouRepository;

    @Autowired
    private KaoShiJiGouExcelListener kaoShiJiGouExcelListener;

    @Override
    public void save(KaoShiJiGouEntity entity) {
        kaoShiJiGouRepository.save(entity);

    }

    @Override
    public boolean importData(MultipartFile file) {

        //读取Excel文件中的数据
        if (file == null) {
            throw new ResultException(20005, "文件未找到");
        }

        try {
            EasyExcel.read(file.getInputStream(), KaoShiJiGouData.class, kaoShiJiGouExcelListener).sheet().doRead();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }


    @Override
    public boolean remove() {
        try {
            kaoShiJiGouRepository.deleteAllInBatch();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<KaoShiJiGouVo> getOutletsByAreaName(String areaName) {

        KaoShiJiGouEntity entity = new KaoShiJiGouEntity();

       // entity.setArea(areaName);
        Example<KaoShiJiGouEntity> example = Example.of(entity);

        List<KaoShiJiGouEntity> outletsEntityList = kaoShiJiGouRepository.findAll(example);

        List<KaoShiJiGouVo> list = outletsEntityList.stream().map(entity1 -> {
            KaoShiJiGouVo kaoShiJiGouVo = new KaoShiJiGouVo();

            BeanUtils.copyProperties(entity1,kaoShiJiGouVo);

            return kaoShiJiGouVo;
        }).collect(Collectors.toList());
        return list;
    }
}
