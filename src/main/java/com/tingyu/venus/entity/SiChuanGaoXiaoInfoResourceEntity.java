package com.tingyu.venus.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author essionshy
 * @Create 2020/11/13 20:43
 * @Version venus-server
 */
@Data
@Table(name = "jyt_scsgxxxzy")
@Entity
public class SiChuanGaoXiaoInfoResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String belongs;
    private String level;
    private String code;
    private String name;

    private String address;

    private String url;

    private String remark;
}
