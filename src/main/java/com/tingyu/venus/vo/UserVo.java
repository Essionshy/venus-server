package com.tingyu.venus.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author essionshy
 * @Create 2020/11/10 22:11
 * @Version venus-server
 */
@Data
public class UserVo {
    private Integer id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Date createTime;

    private Date updateTime;

    private int deleteStatus;

    private Date lastLoginTime;



}
