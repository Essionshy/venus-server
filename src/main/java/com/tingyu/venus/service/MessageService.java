package com.tingyu.venus.service;

import com.tingyu.venus.vo.MessageVo;

import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/25 11:38
 * @Version venus-server
 */

public interface MessageService {

    List<MessageVo> getUnAckMessageList();

    void save(MessageVo messageVo);

    void updateStatus(int status, Long id);


    void removeAllAck();
}
