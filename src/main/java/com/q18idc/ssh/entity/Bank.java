package com.q18idc.ssh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author q18idc.com QQ993143799
 * @date 2018/4/30 15:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bank")
public class Bank extends Condition {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 银行名字
     */
    @Column(name = "bank_name")
    private String bankName;

    /**
     * 多对多  一个银行可以有多个用户
     */
    @ManyToMany
    @JoinTable(name = "user_bank"
            , joinColumns = {@JoinColumn(name = "bank_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private List<User> users;


}
