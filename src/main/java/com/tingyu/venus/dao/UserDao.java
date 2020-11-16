package com.tingyu.venus.dao;

import com.tingyu.venus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author essionshy
 * @Create 2020/10/21 16:30
 * @Version venus-server
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {


}
