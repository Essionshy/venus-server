package com.tingyu.venus.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.tingyu.venus.entity.KaoShiJiGouEntity;
import com.tingyu.venus.excel.KaoShiJiGouData;
import com.tingyu.venus.service.KaoShiJiGouService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/11/11 21:54
 * @Version venus-server
 */
@Component
public class KaoShiJiGouExcelListener extends AnalysisEventListener<KaoShiJiGouData> {


    @Autowired
    private KaoShiJiGouService kaoShiJiGouService;

    @Override
    public void invoke(KaoShiJiGouData kaoShiJiGouData, AnalysisContext analysisContext) {

        KaoShiJiGouEntity entity = new KaoShiJiGouEntity();

        BeanUtils.copyProperties(kaoShiJiGouData,entity);

        kaoShiJiGouService.save(entity);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
