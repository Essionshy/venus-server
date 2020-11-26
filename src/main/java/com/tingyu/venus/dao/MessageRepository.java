package com.tingyu.venus.dao;

import com.tingyu.venus.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/25 11:32
 * @Version venus-server
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "select m from Message m where m.status=0")
    List<Message> getAllUnAckMessageList();

    @Modifying
    @Query(value = "update tbl_message set status=? where id =?", nativeQuery = true)
    void updateStatus(int status, Long id);

    @Modifying
    @Query(value = "delete  from Message m where m.status=1")
    void removeAllAck();

}
