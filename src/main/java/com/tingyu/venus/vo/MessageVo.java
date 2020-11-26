package com.tingyu.venus.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author essionshy
 * @Create 2020/11/25 11:39
 * @Version venus-server
 */
@Data
public class MessageVo {

    private Long id; //消息记录ID

    private Long msgId;// 消息ID

    private String fromId; //消息发送方

    private String toId; //消息接收方


    private String content; //消息内容

    private Integer type; //消息类型，0 通知，1 邀请，2 聊天

    private Integer status; //消息状态，0 未签收，1 签收 ； 默认未签收

    private Date gmtCreate;//

    private Date gmtModified;//
}
