package com.q18idc.ssh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 用户卡 实体类
 * @author q18idc.com QQ993143799
 * @date 2018/4/30 11:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_card")
public class UserCard extends Condition {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 卡的名字
     */
    @Column(name = "card_name")
    private String cardName;

    /**
     * 一对一  关联用户表
     */
    @OneToOne(mappedBy = "userCard")
    @Transient
    private User user;

}
