package com.tingyu.venus.utils;

import com.tingyu.venus.entity.User;
import com.tingyu.venus.vo.UserVo;
import org.springframework.beans.BeanUtils;

/**
 * @Author essionshy
 * @Create 2020/11/10 23:28
 * @Version venus-server
 */
public class UserUtil {

    public static UserVo convertFromUserToUserVo(User user){

        if(null==user){
            return null;
        }

        UserVo userVo =new UserVo();;
        BeanUtils.copyProperties(user,userVo);
        return userVo;

    }

}
