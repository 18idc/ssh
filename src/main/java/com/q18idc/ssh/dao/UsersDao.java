package com.q18idc.ssh.dao;

import com.q18idc.ssh.entity.Bank;
import com.q18idc.ssh.entity.User;

/**
 * @author q18idc.com QQ993143799
 * @date 2018/4/30 11:36
 */
public interface UsersDao {
    /**
     * 根据用户ID查询用户
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 根据银行ID查询银行
     * @param id
     * @return
     */
    Bank getBankById(Integer id);
}
