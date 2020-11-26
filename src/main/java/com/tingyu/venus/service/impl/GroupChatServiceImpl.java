package com.tingyu.venus.service.impl;

import com.tingyu.venus.dao.GroupChatRepository;
import com.tingyu.venus.dao.GroupContactRelationRepository;
import com.tingyu.venus.entity.GroupChat;
import com.tingyu.venus.entity.GroupContactRelation;
import com.tingyu.venus.service.GroupChatService;
import com.tingyu.venus.service.UserService;
import com.tingyu.venus.vo.GroupChatVo;
import com.tingyu.venus.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 创建群聊
 *
 * @Author essionshy
 * @Create 2020/11/22 11:14
 * @Version venus-server
 */
@Service
@Slf4j
@Transactional
public class GroupChatServiceImpl implements GroupChatService {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupChatRepository groupChatRepository;

    @Autowired
    private GroupContactRelationRepository groupContactRelationRepository;

    @Override
    public void save(GroupChatVo groupChatVo) {


        GroupChat groupChat = new GroupChat();
        BeanUtils.copyProperties(groupChatVo, groupChat);
        groupChat.setGmtCreate(new Date());
        groupChat.setGmtModified(new Date());

        //保存群成员关系
        saveRelation(groupChatVo);


        groupChatRepository.save(groupChat);


    }

    @Override
    public void deleteById(String groupId) {

        //删除群成员关系
        deleteRelation(groupId);
        //删除群信息
        GroupChat group = groupChatRepository.findByGroupId(groupId);
        groupChatRepository.delete(group);
    }

    @Override
    public void remove(String groupId, Long contactId) {
        GroupContactRelation relation = new GroupContactRelation();
        relation.setGroupId(groupId);
        relation.setContactId(contactId);
        Example<GroupContactRelation> example = Example.of(relation);
        Optional<GroupContactRelation> groupContactRelation = groupContactRelationRepository.findOne(example);
        GroupContactRelation contactRelation = groupContactRelation.get();
        groupContactRelationRepository.delete(contactRelation);
    }

    @Override
    public List<UserVo> getAllMembersByGroupId(String groupId) {

        List<String> memberIds = groupContactRelationRepository.findAllMemberIdsByGroupId(groupId);

        List<UserVo> userList = userService.getUserList(memberIds);


        return userList;
    }

    /**
     * 删除群成员
     *
     * @param groupId
     */
    private void deleteRelation(String groupId) {


        List<GroupContactRelation> relations = findAllByGroupId(groupId);

        groupContactRelationRepository.deleteInBatch(relations);


    }

    private List<GroupContactRelation> findAllByGroupId(String groupId) {
        GroupContactRelation relation = new GroupContactRelation();
        relation.setGroupId(groupId);
        Example<GroupContactRelation> example = Example.of(relation);
        List<GroupContactRelation> relations = groupContactRelationRepository.findAll(example);
        return relations;
    }

    /**
     * 保存群成员关系
     *
     * @param groupChatVo
     */
    private void saveRelation(GroupChatVo groupChatVo) {

        List<GroupContactRelation> relationList = new ArrayList<>();
        List<String> members = groupChatVo.getMemberIds();

        Iterator<String> iterator = members.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            GroupContactRelation relation = new GroupContactRelation();
            relation.setGroupId(groupChatVo.getGroupId());
            relation.setContactId(Long.valueOf(next));
            relation.setGmtCreate(new Date());
            relation.setGmtModified(new Date());
            relationList.add(relation);
        }
        //保存

        groupContactRelationRepository.saveAll(relationList);

    }
}
