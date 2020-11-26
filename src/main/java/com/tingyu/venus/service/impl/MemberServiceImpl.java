package com.tingyu.venus.service.impl;

import com.tingyu.venus.dao.MemberRepository;
import com.tingyu.venus.entity.Member;
import com.tingyu.venus.service.MemberService;
import com.tingyu.venus.vo.MemberVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author essionshy
 * @Create 2020/11/22 10:36
 * @Version venus-server
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Override
    public void save(MemberVo memberVo) {
        Member member = new Member();
        BeanUtils.copyProperties(memberVo,member);
        //保存到数据库
        memberRepository.save(member);

    }
}
