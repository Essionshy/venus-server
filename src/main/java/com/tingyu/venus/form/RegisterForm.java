package com.tingyu.venus.form;

import lombok.Data;

/**
 * @Author essionshy
 * @Create 2020/11/12 12:30
 * @Version venus-server
 */
@Data
public class RegisterForm {

    private String username;
    private String phone;

    private String password;

    private String verifyCode;


}
