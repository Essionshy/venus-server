package com.tingyu.venus.dao;

import com.tingyu.venus.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author essionshy
 * @Create 2020/11/22 10:34
 * @Version venus-server
 */
public interface MemberRepository extends JpaRepository<Member,String> {
}
