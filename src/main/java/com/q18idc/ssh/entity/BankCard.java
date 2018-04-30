package com.q18idc.ssh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * 银行卡  实体类
 * @author q18idc.com QQ993143799
 * @date 2018/4/30 13:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bank_card")
public class BankCard extends Condition{

    /**
     * 主键id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 卡的名字
     */
    @Column(name = "name")
    private String name;

    /**
     * 关联的用户id  多对一
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;
}
