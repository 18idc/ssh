package com.q18idc.ssh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author q18idc.com QQ993143799
 * Created by q18idc.com QQ993143799 on 2018/3/1
 */
@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Condition {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "birthday")
    private Timestamp birthday;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "sex")
    private String sex;

    @Basic
    @Column(name = "username")
    private String username;


    /**
     * 一对一 用户关联用户卡
     */
    @OneToOne
    @JoinColumn(name = "cid")
    private UserCard userCard;

    /**
     * 一对多 用户下的所有银行卡
     */
    @OneToMany(mappedBy = "users")
    private List<BankCard> bankCards;

    /**
     * 多对多  一个用户可以有多个银行
     */
    //创建一个中间表
    @JoinTable(name = "user_bank"
            , joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "bank_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Bank> banks;
}
