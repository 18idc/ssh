package com.q18idc.ssh.service;

import com.q18idc.ssh.entity.User;

import java.util.Map;

/**
 * @author q18idc.com QQ993143799
 * @date 2018/4/30 11:32
 */
public interface UsersService {
    /**
     * 一对一查询  根据用户ID查询用户的卡
     * @param id 用户id
     * @return
     */
    User oneToOne(Integer id);

    /**
     * 一对多 根据用户ID查询用户下的银行卡
     * @param id 用户ID
     * @return
     */
    User oneToMany(Integer id);


    /**
     * 多对多 根据用户ID查询用户的银行  根据银行ID查询银行的用户
     * @param uid 用户ID
     * @param bid 银行ID
     * @return
     */
    Map<String ,Object> manyToMany(Integer uid,Integer bid);
}
