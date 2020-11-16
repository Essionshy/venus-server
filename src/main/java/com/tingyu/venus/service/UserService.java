package com.tingyu.venus.service;

import com.tingyu.venus.entity.User;
import com.tingyu.venus.form.RegisterForm;
import com.tingyu.venus.query.UserQuery;
import com.tingyu.venus.vo.UserVo;

/**
 * @Author essionshy
 * @Create 2020/10/21 16:19
 * @Version venus-server
 */
public interface UserService {

    User getById(Integer id);

    int create(UserQuery user);


    UserVo getByPhone(String phone);

    boolean save(RegisterForm registerForm);
}
