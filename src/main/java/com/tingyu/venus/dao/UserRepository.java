package com.tingyu.venus.dao;

import com.tingyu.venus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/10/21 16:30
 * @Version venus-server
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User getByPhone(String phone);

    @Query(value = "select u from User u where u.phone in ?1")
    List<User> findAllByPhone(List<String> memberIds);
}
