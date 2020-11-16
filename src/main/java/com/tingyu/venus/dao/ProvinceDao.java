package com.tingyu.venus.dao;

import com.tingyu.venus.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author essionshy
 * @Create 2020/11/12 10:59
 * @Version venus-server
 */
@Repository
public interface ProvinceDao extends JpaRepository<Province,Integer> {
}
