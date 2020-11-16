package com.tingyu.venus.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.tingyu.venus.entity.SiChuanGaoXiaoInfoResourceEntity;
import com.tingyu.venus.excel.SiChuanGaoXiaoInformationResourceData;
import com.tingyu.venus.service.SiChuanGaoXiaoInfoResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/11/11 21:54
 * @Version venus-server
 */
@Component
public class SiChuanGaoXiaoInfoResourceExcelListener extends AnalysisEventListener<SiChuanGaoXiaoInformationResourceData> {


    @Autowired
    private SiChuanGaoXiaoInfoResourceService siChuanGaoXiaoInfoResourceService;

    @Override
    public void invoke(SiChuanGaoXiaoInformationResourceData data, AnalysisContext analysisContext) {

        SiChuanGaoXiaoInfoResourceEntity entity = new SiChuanGaoXiaoInfoResourceEntity();

        BeanUtils.copyProperties(data,entity);

        siChuanGaoXiaoInfoResourceService.save(entity);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
