package com.tingyu.venus.service.impl;

import com.tingyu.venus.dao.ContactRepository;
import com.tingyu.venus.entity.Contact;
import com.tingyu.venus.service.ContactService;
import com.tingyu.venus.vo.ContactVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author essionshy
 * @Create 2020/11/19 19:59
 * @Version venus-server
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<ContactVo> findContactsByUserPhone(String userPhone) {

        if (userPhone == null) {
            return null;
        }
        Contact contact = new Contact();

        contact.setUserPhone(userPhone);
        Example<Contact> example = Example.of(contact);

        List<Contact> contacts = contactRepository.findAll(example);

        List<ContactVo> contactVoList = contacts.stream().map(contact1 -> {
            ContactVo contactVo = new ContactVo();
            BeanUtils.copyProperties(contact1, contactVo);
            return contactVo;
        }).collect(Collectors.toList());

        return contactVoList;
    }

    @Override
    public List<String> findContactPhonesByUserPhone(String userPhone) {

        List<ContactVo> contacts = this.findContactsByUserPhone(userPhone);
        Iterator<ContactVo> iterator = contacts.iterator();

        List<String> contactPhones = null;
        while (iterator.hasNext()) {
            contactPhones = new ArrayList<>();
            ContactVo next = iterator.next();
            contactPhones.add(next.getContactPhone());
        }

        return contactPhones;
    }

    @Override
    public void save(ContactVo contactVo) {

        Contact existsContact = contactRepository.findByUserPhoneAndContactPhone(contactVo.getUserPhone(), contactVo.getContactPhone());

        //如果用户存在则不向数据库中添加
        if (existsContact != null) {
            return;
        }

        Contact contact = new Contact();
        BeanUtils.copyProperties(contactVo, contact);
        contact.setGmtCreate(new Date());
        contact.setGmtModified(new Date());
        contactRepository.save(contact);
    }

    @Transactional
    @Override
    public void remove(String from, String to) {

        Contact fromContact = contactRepository.findByUserPhoneAndContactPhone(from, to);
        if(fromContact==null){
            return;
        }
        contactRepository.delete(fromContact);
        Contact toContact = contactRepository.findByUserPhoneAndContactPhone(to, from);
        if(toContact==null){
            return;
        }
        contactRepository.delete(toContact);


    }

    @Override
    public boolean isContact(String fromId, String toId) {
        return contactRepository.findByUserPhoneAndContactPhone(fromId, toId) != null;
    }
}
