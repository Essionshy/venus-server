package com.tingyu.venus.service.impl;

import com.alibaba.excel.EasyExcel;
import com.tingyu.venus.dao.SheBaoOutletsDao;
import com.tingyu.venus.entity.SheBaoServiceOutletsEntity;
import com.tingyu.venus.excel.SbrsbmfwdData;
import com.tingyu.venus.exception.ResultException;
import com.tingyu.venus.listener.SheBaoOutletsExcelListener;
import com.tingyu.venus.service.SheBaoOutletsService;
import com.tingyu.venus.vo.SheBaoOutletsVo;
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
public class SheBaoOutletsServiceImpl implements SheBaoOutletsService {


    @Autowired
    private SheBaoOutletsDao sheBaoOutletsDao;

    @Autowired
    private SheBaoOutletsExcelListener sheBaoOutletsExcelListener;

    @Override
    public void save(SheBaoServiceOutletsEntity entity) {
        sheBaoOutletsDao.save(entity);

    }

    @Override
    public boolean importData(MultipartFile file) {

        //读取Excel文件中的数据
        if (file == null) {
            throw new ResultException(20005, "文件未找到");
        }

        try {
            EasyExcel.read(file.getInputStream(), SbrsbmfwdData.class, sheBaoOutletsExcelListener).sheet().doRead();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }


    @Override
    public boolean remove() {
        try {
            sheBaoOutletsDao.deleteAllInBatch();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SheBaoOutletsVo> getOutletsByAreaName(String areaName) {

        SheBaoServiceOutletsEntity entity = new SheBaoServiceOutletsEntity();

        entity.setArea(areaName);
        Example<SheBaoServiceOutletsEntity> example = Example.of(entity);

        List<SheBaoServiceOutletsEntity> outletsEntityList = sheBaoOutletsDao.findAll(example);

        List<SheBaoOutletsVo> list = outletsEntityList.stream().map(entity1 -> {
            SheBaoOutletsVo sheBaoOutletsVo = new SheBaoOutletsVo();
            sheBaoOutletsVo.setOutletsAddress(entity1.getOutlets_address());
            sheBaoOutletsVo.setOutletsName(entity1.getOutlets_name());

            return sheBaoOutletsVo;
        }).collect(Collectors.toList());
        return list;
    }
}
