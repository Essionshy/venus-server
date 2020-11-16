package com.tingyu.venus.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author essionshy
 * @Create 2020/11/13 19:16
 * @Version venus-server
 */
@Data
public class SiChuanGaoXiaoInformationResourceData {

    @ExcelProperty(index = 0)
    private String belongs;
    @ExcelProperty(index = 1)
    private String level;
    @ExcelProperty(index = 2)
    private String code;
    @ExcelProperty(index = 3)
    private String name;

    @ExcelProperty(index = 4)
    private String address;
    @ExcelProperty(index = 5)
    private String url;
    @ExcelProperty(index = 6)
    private String remark;

}
