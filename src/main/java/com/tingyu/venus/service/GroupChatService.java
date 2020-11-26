package com.tingyu.venus.service;

import com.tingyu.venus.vo.GroupChatVo;
import com.tingyu.venus.vo.UserVo;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/22 11:10
 * @Version venus-server
 */

public interface GroupChatService {

    void save(GroupChatVo groupChatVo);

    void deleteById(String groupId);

    void remove(String groupId, Long contactId);

    List<UserVo> getAllMembersByGroupId(String groupId);

}
