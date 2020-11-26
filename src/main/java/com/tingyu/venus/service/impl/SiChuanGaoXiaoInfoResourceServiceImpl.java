package com.tingyu.venus.service.impl;

import com.alibaba.excel.EasyExcel;
import com.tingyu.venus.dao.SiChuanGaoXiaoInfoResourceRepository;
import com.tingyu.venus.entity.SiChuanGaoXiaoInfoResourceEntity;
import com.tingyu.venus.excel.SiChuanGaoXiaoInformationResourceData;
import com.tingyu.venus.exception.ResultException;
import com.tingyu.venus.listener.SiChuanGaoXiaoInfoResourceExcelListener;
import com.tingyu.venus.service.SiChuanGaoXiaoInfoResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author essionshy
 * @Create 2020/11/13 20:47
 * @Version venus-server
 */
@Service
public class SiChuanGaoXiaoInfoResourceServiceImpl implements SiChuanGaoXiaoInfoResourceService {

    @Autowired
   private SiChuanGaoXiaoInfoResourceRepository siChuanGaoXiaoInfoResourceRepository;

    @Autowired
    private SiChuanGaoXiaoInfoResourceExcelListener siChuanGaoXiaoInfoResourceExcelListener;

    @Override
    public void save(SiChuanGaoXiaoInfoResourceEntity entity) {
        siChuanGaoXiaoInfoResourceRepository.save(entity);
    }

    @Override
    public boolean importData(MultipartFile file) {
        //读取Excel文件中的数据
        if (file == null) {
            throw new ResultException(20005, "文件未找到");
        }

        try {
            EasyExcel.read(file.getInputStream(), SiChuanGaoXiaoInformationResourceData.class, siChuanGaoXiaoInfoResourceExcelListener).sheet().doRead();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove() {
        try{
            siChuanGaoXiaoInfoResourceRepository.deleteAllInBatch();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
