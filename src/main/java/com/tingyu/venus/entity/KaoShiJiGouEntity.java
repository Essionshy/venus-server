package com.tingyu.venus.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author essionshy
 * @Create 2020/11/13 19:20
 * @Version venus-server
 */
@Data
@Entity
@Table(name = "rst_ksjgmc")
public class KaoShiJiGouEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String tel;

    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
