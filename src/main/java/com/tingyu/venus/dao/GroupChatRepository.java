package com.tingyu.venus.dao;

import com.tingyu.venus.entity.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author essionshy
 * @Create 2020/11/22 11:08
 * @Version venus-server
 */
public interface GroupChatRepository extends JpaRepository<GroupChat, Long> {


    GroupChat findByGroupId(String groupId);
}
