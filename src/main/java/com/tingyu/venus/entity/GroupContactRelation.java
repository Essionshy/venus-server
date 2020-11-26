package com.tingyu.venus.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * 维护群与联系人之间的多对多关系（一个群可以有多个联系人，一个联系人可以在多个群中）
 * @Author essionshy
 * @Create 2020/11/22 10:59
 * @Version venus-server
 */
@Data
@Entity
@Table(name = "tbl_group_contact_relation")
public class GroupContactRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupId; //群ID

    private Long contactId; //联系人ID

    private Date gmtCreate; //创建时间

    private Date gmtModified; //更新时间

}
