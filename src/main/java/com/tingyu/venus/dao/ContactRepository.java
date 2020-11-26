package com.tingyu.venus.dao;

import com.tingyu.venus.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author essionshy
 * @Create 2020/11/19 19:55
 * @Version venus-server
 */
public interface ContactRepository extends JpaRepository<Contact,Long> {
    Contact findByUserPhoneAndContactPhone(String userPhone, String contactPhone);
}
