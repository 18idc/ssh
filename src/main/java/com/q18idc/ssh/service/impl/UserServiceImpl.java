package com.q18idc.ssh.service.impl;

import com.q18idc.ssh.dao.UserDao;
import com.q18idc.ssh.entity.PageBean;
import com.q18idc.ssh.entity.User;
import com.q18idc.ssh.service.UserService;
import com.q18idc.ssh.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author q18idc.com QQ993143799
 * Created by q18idc.com QQ993143799 on 2018/3/2
 */
@Service
@Transactional(rollbackFor = Exception.class)
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
    public String addUpdate(User user) {
        if (user != null) {
            if(MyUtils.isEmpty(user.getUsername())){
                return "请输入用户名";
            }else if(MyUtils.isEmpty(user.getPassword())){
                return "请输入密码";
            }else if(MyUtils.isSpecialChar(user.getPassword())){
                return "密码包含特殊字符，请重新输入";
            } else if(MyUtils.isEmpty(user.getPhone())){
                return "请输入电话";
            }else if(!MyUtils.isMobileNum(user.getPhone())){
                return "请输入合法的手机号";
            }else if(MyUtils.isEmpty(user.getEmail())){
                return "请输入邮箱";
            }else if(!MyUtils.isEmail(user.getEmail())){
                return "邮箱格式不正确";
            }else if(MyUtils.isEmpty(user.getSex())){
                return "请选择性别";
            }else if(!("男".equalsIgnoreCase(user.getSex()) || "女".equalsIgnoreCase(user.getSex()))){
                return "请重新选择性别";
            }else {
                boolean b = userDao.addUpdate(user);
                if(b){
                    return "操作成功";
                }else {
                    return "操作失败";
                }
            }
        }
        return "操作失败";
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
