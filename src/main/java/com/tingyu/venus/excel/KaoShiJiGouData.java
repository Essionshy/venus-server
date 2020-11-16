package com.tingyu.venus.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author essionshy
 * @Create 2020/11/13 19:16
 * @Version venus-server
 */
@Data
public class KaoShiJiGouData {

    @ExcelProperty(index = 0)
    private String name;
    @ExcelProperty(index = 1)
    private String tel;
    @ExcelProperty(index = 2)
    private String address;
    @DateTimeFormat(value = "yyyy-MM-dd")
    @ExcelProperty(index = 3)
    private Date updateTime;
}
