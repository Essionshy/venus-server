package com.tingyu.venus.dao;

import com.tingyu.venus.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * @Author essionshy
 * @Create 2020/11/12 11:00
 * @Version venus-server
 */
@Repository
public interface AreaDao extends JpaRepository<Area,Integer> {
}
