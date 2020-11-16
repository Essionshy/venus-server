package com.tingyu.venus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author essionshy
 * @Create 2020/11/12 10:48
 * @Version venus-server
 */
@Data
@Entity
public class Province {


    @Id
    private Integer id;

    private String provinceCode;

    private String provinceName;

}
