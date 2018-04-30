package com.q18idc.ssh.service.impl;

import com.q18idc.ssh.dao.UsersDao;
import com.q18idc.ssh.entity.Bank;
import com.q18idc.ssh.entity.BankCard;
import com.q18idc.ssh.entity.User;
import com.q18idc.ssh.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author q18idc.com QQ993143799
 * @date 2018/4/30 11:34
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao;

    /**
     * 一对一查询  根据用户ID查询用户的卡
     * @param id 用户id
     * @return
     */
    @Override
    public User oneToOne(Integer id) {
        if(id!=null){
            User user = usersDao.getUserById(id);
            user.setBankCards(null);
            return user;
        }
        return null;
    }

    /**
     * 一对多 根据用户ID查询用户下的银行卡
     * @param id 用户ID
     * @return
     */
    @Override
    public User oneToMany(Integer id) {
        if(id!=null){
            User user = usersDao.getUserById(id);
            List<BankCard> bankCards = new ArrayList<>();
            for (BankCard bankCard : user.getBankCards()) {
//                User users = bankCard.getUsers();
//                int ids = users.getId();
//                users=new User();
//                users.setId(ids);
//                users.setBankCards(null);
                User users = new User();
                users.setId(bankCard.getUsers().getId());
                bankCard.setUsers(users);
                bankCards.add(bankCard);
            }
            user.setBankCards(bankCards);
            return user;
        }
        return null;
    }

    /**
     * 多对多 根据用户ID查询用户的银行  根据银行ID查询银行的用户
     * @param uid 用户ID
     * @param bid 银行ID
     * @return
     */
    @Override
    public Map<String, Object> manyToMany(Integer uid, Integer bid) {
        if(uid!=null & bid!=null){
            Map<String, Object> map = new HashMap<>();
            List list = new ArrayList();
            List list2 = new ArrayList();

            //根据银行ID 查询银行下面所有的用户
            Bank bank = usersDao.getBankById(bid);
            for (User user : bank.getUsers()) {
                list.add(user.getUsername());
            }
            map.put("ID为"+bid+"的银行下面的用户列表", list);


            System.out.println("ID为1的用户下面的银行");
            User user = usersDao.getUserById(uid);
            for (Bank bank1 : user.getBanks()) {
                list2.add(bank1.getBankName());
            }
            map.put("ID为"+uid+"的用户下面的银行", list2);
            return map;
        }
        return null;
    }
}
