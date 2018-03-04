package com.q18idc.ssh.dao;

import com.q18idc.ssh.entity.User;

import java.util.List;

/**
 * @author q18idc.com QQ993143799
 * Created by q18idc.com QQ993143799 on 2018/3/3
 */
public interface UserDao {
    int countUser(User user);
    List<User> queryForPage(User user);
    boolean addUpdate(User user);
    boolean del(int id);
    int countSex(String sex);

}
