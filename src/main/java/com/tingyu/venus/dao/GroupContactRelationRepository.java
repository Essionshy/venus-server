package com.tingyu.venus.dao;

import com.tingyu.venus.entity.GroupContactRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/22 11:09
 * @Version venus-server
 */
public interface GroupContactRelationRepository extends JpaRepository<GroupContactRelation, Long> {

    List<GroupContactRelation> findAllByGroupId(String groupId);


    @Query(value = "select gcr.contactId from GroupContactRelation gcr where gcr.groupId=?1")
    List<String> findAllMemberIdsByGroupId(String groupId);
}
