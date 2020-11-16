package com.tingyu.venus.dao;

import com.tingyu.venus.entity.SheBaoServiceOutletsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author essionshy
 * @Create 2020/11/11 22:01
 * @Version venus-server
 */
@Repository
public interface SheBaoOutletsDao extends JpaRepository<SheBaoServiceOutletsEntity, Integer> {
}
