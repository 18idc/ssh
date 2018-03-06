package com.q18idc.ssh.service;

import com.q18idc.ssh.entity.PageBean;
import com.q18idc.ssh.entity.User;

/**
 * @author q18idc.com QQ993143799
 * Created by q18idc.com QQ993143799 on 2018/3/2
 */
public interface UserService {
    /**
     * 根据条件 分页查询用户
     * @param user
     * @return
     */
    PageBean queryForPage(User user);

    /**
     * 添加修改用户
     * @param user
     * @return
     */
    String addUpdate(User user);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    boolean del(int id);

    /**
     * 根据性别统计
     * @param sex
     * @return
     */
    int countSex(String sex);
}
