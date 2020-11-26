package com.tingyu.venus.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 联系关联表，多对多的关系
 * @Author essionshy
 * @Create 2020/11/19 19:49
 * @Version venus-server
 */
@Data
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //用户手机号
    private String userPhone;

    //联系人手机号
    private String contactPhone;
    //创建时间
    private Date gmtCreate;
    //修改时间
    private Date gmtModified;
}
