package com.tingyu.venus.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author essionshy
 * @Create 2020/11/22 10:31
 * @Version venus-server
 */
@Data
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String name;

}
