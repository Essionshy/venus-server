package com.tingyu.venus.service.impl;

import com.tingyu.venus.dao.MessageRepository;
import com.tingyu.venus.entity.Message;
import com.tingyu.venus.service.MessageService;
import com.tingyu.venus.vo.MessageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author essionshy
 * @Create 2020/11/25 11:40
 * @Version venus-server
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<MessageVo> getUnAckMessageList() {

        List<Message> messageList = messageRepository.getAllUnAckMessageList();

        List<MessageVo> messageVoList = messageList.stream().map(message -> {
            MessageVo messageVo = new MessageVo();

            BeanUtils.copyProperties(message, messageVo);
            return messageVo;
        }).collect(Collectors.toList());

        return messageVoList;
    }

    @Override
    public void save(MessageVo messageVo) {
        if (messageVo == null) {
            return;
        }

        Message message = new Message();
        BeanUtils.copyProperties(messageVo, message);

        message.setGmtCreate(new Date());
        message.setGmtModified(new Date());
        messageRepository.save(message);

    }

    @Transactional
    @Override
    public void updateStatus(int status, Long id) {
        messageRepository.updateStatus(status, id);
    }

    @Transactional
    @Override
    public void removeAllAck() {
        messageRepository.removeAllAck();
    }
}
