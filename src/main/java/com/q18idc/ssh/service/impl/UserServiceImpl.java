package com.q18idc.ssh.service.impl;

import com.q18idc.ssh.dao.UserDao;
import com.q18idc.ssh.entity.PageBean;
import com.q18idc.ssh.entity.User;
import com.q18idc.ssh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author q18idc.com QQ993143799
 * Created by q18idc.com QQ993143799 on 2018/3/2
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 根据条件 分页查询用户
     *
     * @param user
     * @return
     */
    @Override
    public PageBean queryForPage(User user) {
        PageBean pageBean = new PageBean();
        pageBean.setList(userDao.queryForPage(user));
        pageBean.setAllRow(userDao.countUser(user));
        return pageBean;
    }

    /**
     * 添加修改用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean addUpdate(User user) {
        if (user != null) {
            return userDao.addUpdate(user);
        }
        return false;
    }

    /**
     * 根据ID删除用户
     * @param id
     * @return
     */
    @Override
    public boolean del(int id) {
        if(id!=0){
            return userDao.del(id);
        }
        return false;
    }

    @Override
    public int countSex(String sex) {
        return userDao.countSex(sex);
    }
}
