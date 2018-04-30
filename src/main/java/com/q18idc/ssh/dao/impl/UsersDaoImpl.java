package com.q18idc.ssh.dao.impl;

import com.q18idc.ssh.dao.UsersDao;
import com.q18idc.ssh.entity.Bank;
import com.q18idc.ssh.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author q18idc.com QQ993143799
 * @date 2018/4/30 11:37
 */
@Repository
public class UsersDaoImpl implements UsersDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 根据用户ID查询用户
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {
        if (id != null) {
            Session session = sessionFactory.getCurrentSession();
            return session.get(User.class, id);
        }
        return null;
    }

    /**
     * 根据银行ID查询银行
     * @param id
     * @return
     */
    @Override
    public Bank getBankById(Integer id) {
        if(id!=null){
            Session session = sessionFactory.getCurrentSession();
            return session.get(Bank.class, id);
        }
        return null;
    }
}
