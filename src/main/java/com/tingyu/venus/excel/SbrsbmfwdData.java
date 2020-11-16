package com.tingyu.venus.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 *
 * 四川省社保卡人社部门服务网点
 * @Author essionshy
 * @Create 2020/11/11 21:20
 * @Version venus-server
 */

@Data
public class SbrsbmfwdData {

    @ExcelProperty(index = 0)
    private String city;
    @ExcelProperty(index = 1)
    private String area;
    @ExcelProperty(index = 2)
    private String outlets_name;
    @ExcelProperty(index = 3)
    private String outlets_address;
    @ExcelProperty(index = 4)
    private String update_name;
}
