package com.tingyu.venus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author essionshy
 * @Create 2020/11/12 10:55
 * @Version venus-server
 */
@Entity
@Data
@Table(name = "city")
public class City {

    @Id
    private Integer id;

    private String provinceCode;

    private String cityCode;

    private String cityName;

}
