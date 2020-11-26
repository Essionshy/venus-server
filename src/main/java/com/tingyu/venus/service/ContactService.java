package com.tingyu.venus.service;

import com.tingyu.venus.vo.ContactVo;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/19 19:56
 * @Version venus-server
 */
public interface ContactService {

    List<ContactVo> findContactsByUserPhone(String userPhone);

    List<String> findContactPhonesByUserPhone(String userPhone);

    void save(ContactVo contactVo);

    void remove(String from, String to);

    boolean isContact(String fromId, String toId);
}
