import com.q18idc.ssh.dao.UserDao;
import com.q18idc.ssh.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

/**
 * @author q18idc.com QQ993143799
 * Created by q18idc.com QQ993143799 on 2018/3/4
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring.xml"})
@Transactional
@Rollback(false)
public class UserTest {
    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Autowired
    private UserDao userDao;

    @Before
    public void before(){
        session = sessionFactory.getCurrentSession();
    }

    @Test
    public void query() throws UnsupportedEncodingException {
        //这里使用JPA规范的CriteriaQuery
        Query<User> query = session.createQuery("from User where username like :keys", User.class);
        query.setParameter("keys",String.format("%%%s%%", "芦"));
        System.err.println(query.list());
    }

    @Test
    public void countSex(){
        System.out.println(userDao.countSex("男"));
    }
}
