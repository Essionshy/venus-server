package com.tingyu.venus.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.tingyu.venus.entity.SheBaoServiceOutletsEntity;
import com.tingyu.venus.excel.SbrsbmfwdData;
import com.tingyu.venus.service.SheBaoOutletsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author essionshy
 * @Create 2020/11/11 21:54
 * @Version venus-server
 */
@Component
public class SheBaoOutletsExcelListener extends AnalysisEventListener<SbrsbmfwdData> {


    @Autowired
    private SheBaoOutletsService sheBaoOutletsService;

    @Override
    public void invoke(SbrsbmfwdData sbrsbmfwdData, AnalysisContext analysisContext) {

        SheBaoServiceOutletsEntity entity = new SheBaoServiceOutletsEntity();

        BeanUtils.copyProperties(sbrsbmfwdData,entity);

        sheBaoOutletsService.save(entity);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
