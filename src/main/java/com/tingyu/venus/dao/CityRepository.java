package com.tingyu.venus.dao;

import com.tingyu.venus.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author essionshy
 * @Create 2020/11/12 10:59
 * @Version venus-server
 */

public interface CityRepository extends JpaRepository<City,Integer> {
}
