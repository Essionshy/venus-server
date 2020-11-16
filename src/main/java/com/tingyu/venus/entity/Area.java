package com.tingyu.venus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author essionshy
 * @Create 2020/11/12 11:01
 * @Version venus-server
 */
@Data
@Entity
@Table(name = "area")
public class Area {
    @Id
    private Integer id;

    private String cityCode;

    private String areaCode;

    private String areaName;

}
