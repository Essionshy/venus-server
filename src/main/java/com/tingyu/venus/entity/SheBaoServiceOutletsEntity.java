package com.tingyu.venus.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author essionshy
 * @Create 2020/11/11 21:30
 * @Version venus-server
 */
@Data
@Entity
@Table(name = "rst_sbrsbmfwd")
public class SheBaoServiceOutletsEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String city;

    private String area;

    private String outlets_name;

    private String outlets_address;

    private String update_name;
}
