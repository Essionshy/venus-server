package com.tingyu.venus.service;

import com.tingyu.venus.form.RegisterForm;
import com.tingyu.venus.query.UserQuery;
import com.tingyu.venus.vo.UserVo;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/10/21 16:19
 * @Version venus-server
 */
public interface UserService {

    UserVo getById(Long id);

    int create(UserQuery user);


    UserVo getByPhone(String phone);


    boolean save(RegisterForm registerForm);

    List<UserVo> findContactsByUserPhone(String userPhone);

    List<UserVo> getUserList(List<String> memberIds);
}
