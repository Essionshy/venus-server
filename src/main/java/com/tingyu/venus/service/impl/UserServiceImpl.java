package com.tingyu.venus.service.impl;

import com.tingyu.venus.common.BizCode;
import com.tingyu.venus.dao.UserDao;
import com.tingyu.venus.entity.User;
import com.tingyu.venus.exception.ResultException;
import com.tingyu.venus.form.RegisterForm;
import com.tingyu.venus.query.UserQuery;
import com.tingyu.venus.service.UserService;
import com.tingyu.venus.utils.MD5Utils;
import com.tingyu.venus.utils.UserUtil;
import com.tingyu.venus.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author essionshy
 * @Create 2020/10/21 16:20
 * @Version venus-server
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getById(Integer id) {
        return userDao.getOne(id);
    }

    @Override
    public int create(UserQuery user) {
        return 0;
    }

    @Override
    public UserVo getByPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        Example<User> example = Example.of(user);
        Optional<User> one = userDao.findOne(example);
        try {
            User result = one.get();
            return UserUtil.convertFromUserToUserVo(result);
        } catch (Exception e) {
           return null;
        }

    }

    @Override
    public boolean save(RegisterForm registerForm) {

        UserVo userVo = getByPhone(registerForm.getPhone());

        if(userVo!=null){
            throw new ResultException(BizCode.USER_EXISTS.getCode(),BizCode.USER_EXISTS.getMessage());
        }


        try {
            User user = new User();
            BeanUtils.copyProperties(registerForm, user);
            user.setPassword(MD5Utils.encrypt(registerForm.getPassword()));
            userDao.save(user);
            return true;
        } catch (Exception e) {

            return false;
        }
    }
}
