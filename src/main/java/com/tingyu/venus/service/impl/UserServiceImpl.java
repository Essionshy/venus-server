package com.tingyu.venus.service.impl;

import com.tingyu.venus.common.BizCode;
import com.tingyu.venus.dao.UserRepository;
import com.tingyu.venus.entity.User;
import com.tingyu.venus.exception.ResultException;
import com.tingyu.venus.form.RegisterForm;
import com.tingyu.venus.query.UserQuery;
import com.tingyu.venus.service.ContactService;
import com.tingyu.venus.service.UserService;
import com.tingyu.venus.utils.MD5Utils;
import com.tingyu.venus.utils.UserUtil;
import com.tingyu.venus.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author essionshy
 * @Create 2020/10/21 16:20
 * @Version venus-server
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactService contactService;


    @Override
    public UserVo getById(Long id) {
        return UserUtil.convertFromUserToUserVo(userRepository.getOne(id));

    }

    @Override
    public int create(UserQuery user) {
        return 0;
    }

    @Override
    public UserVo getByPhone(String phone) {

        User user = userRepository.getByPhone(phone);
        return UserUtil.convertFromUserToUserVo(user);


    }

    @Override
    public boolean save(RegisterForm registerForm) {

        UserVo userVo = getByPhone(registerForm.getPhone());

        if (userVo != null) {
            throw new ResultException(BizCode.USER_EXISTS.getCode(), BizCode.USER_EXISTS.getMessage());
        }


        try {
            User user = new User();
            BeanUtils.copyProperties(registerForm, user);
            user.setPassword(MD5Utils.encrypt(registerForm.getPassword()));
            userRepository.save(user);
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public List<UserVo> findContactsByUserPhone(String userPhone) {
        List<String> phones = contactService.findContactPhonesByUserPhone(userPhone);

        if (null == phones) {
            return null;
        }
        List<UserVo> userVos = new ArrayList<>();

        for (String phone : phones) {
            UserVo user = getByPhone(phone);
            userVos.add(user);
        }
        return userVos;
    }

    @Override
    public List<UserVo> getUserList(List<String> memberIds) {

        List<User> userList = userRepository.findAllByPhone(memberIds);

        List<UserVo> userVoList = userList.stream().map(user -> {
            return UserUtil.convertFromUserToUserVo(user);
        }).collect(Collectors.toList());

        return userVoList;
    }
}
