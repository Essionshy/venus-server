package com.tingyu.venus.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/22 11:10
 * @Version venus-server
 */
@Data
public class GroupChatVo {

    private String groupId;//群ID

    private String groupName; //群名称

    private String groupHolder; //创建人

    private String groupNotice; //群公告

    private String groupRemark;//群备注

    private Date gmtCreate;//创建时间

    private List<String> memberIds;// 群成员


}
