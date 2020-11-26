package com.tingyu.venus.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 群聊实体类
 *
 * @Author essionshy
 * @Create 2020/11/22 10:49
 * @Version venus-server
 */
@Data
@Entity
@Table(name = "tbl_group_chat")
public class GroupChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //主键ID

    private String groupId;//群ID

    private String groupName; //群名称

    private String groupHolder; //创建人

    private String groupNotice; //群公告

    private String groupRemark;//群备注

    private Date gmtCreate;//创建时间

    private Date gmtModified; //修改时间

}
