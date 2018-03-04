package com.q18idc.ssh.dao.impl;

import com.q18idc.ssh.dao.UserDao;
import com.q18idc.ssh.entity.User;
import com.q18idc.ssh.utils.MyUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author q18idc.com QQ993143799
 * Created by q18idc.com QQ993143799 on 2018/3/3
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> queryForPage(User user) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User where username like :keys",User.class).setParameter("keys",String.format("%%%s%%", user.getKey())).setFirstResult((user.getPage() - 1) * user.getRows()).setMaxResults(user.getRows()).list();
    }

    @Override
    public int countUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count (*) from User where username like :keys").setParameter("keys",String.format("%%%s%%", user.getKey()));
        return Integer.parseInt(query.list().get(0).toString());
    }

    /**
     * 添加或修改用户
     * @param user
     * @return
     */
    @Override
    public boolean addUpdate(User user) {
        if(user!=null){
            Session session = sessionFactory.getCurrentSession();
            System.out.println(user);
            session.saveOrUpdate(user);
            return true;
        }
        return false;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public boolean del(int id) {
        if(id!=0){
            User user = new User();
            user.setId(id);
            sessionFactory.getCurrentSession().delete(user);
            return true;
        }
        return false;
    }

    /**
     * 性别统计
     * @param sex
     * @return
     */
    @Override
    public int countSex(String sex) {
        if(MyUtils.isNotEmpty(sex)){
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("select count(*) from User where sex = :sexs").setParameter("sexs",sex);
            return Integer.parseInt(query.list().get(0).toString());
        }
        return 0;
    }
}
