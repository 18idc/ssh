import com.q18idc.ssh.entity.Bank;
import com.q18idc.ssh.entity.BankCard;
import com.q18idc.ssh.entity.User;
import com.q18idc.ssh.entity.UserCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author q18idc.com QQ993143799
 * @date 2018/4/30 11:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring.xml"})
@Transactional(rollbackFor = Exception.class)
@Rollback(false)
public class UsersTest {
    @Autowired
    private SessionFactory sessionFactory;

    private Session session;


    @Before
    public void before(){
        session = sessionFactory.getCurrentSession();
    }

    /**
     * 添加数据
     */
    @Test
    public void add() {
        for (int i = 0; i < 10; i++) {
            UserCard userCard = new UserCard();
            userCard.setCardName("学生卡"+i);
            int i1 = (int) session.save(userCard);
            System.out.println(i1);
        }
    }

    /**
     * 添加数据
     */
    @Test
    public void add2() {
        for (int i = 0; i < 10; i++) {
            BankCard bankCard = new BankCard();
            bankCard.setName("测试卡"+i);
            User users = new User();
            users.setId(2);
            bankCard.setUsers(users);
            int i1 = (int) session.save(bankCard);
            System.err.println(i1);
        }
    }

    /**
     * 添加数据
     */
    @Test
    public void add3(){
        for (int i = 0; i < 10; i++) {
            Bank bank = new Bank();
            bank.setBankName("银行"+i);
            int i1 = (int) session.save(bank);
            System.out.println(i1);
        }
    }


}
