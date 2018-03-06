package com.q18idc.ssh.action.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.q18idc.ssh.entity.JsonEasyUi;
import com.q18idc.ssh.entity.JsonResult;
import com.q18idc.ssh.entity.PageBean;
import com.q18idc.ssh.entity.User;
import com.q18idc.ssh.service.UserService;
import com.q18idc.ssh.utils.MyUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author q18idc.com QQ993143799
 * Created by q18idc.com QQ993143799 on 2018/3/2
 */
public class UserAction extends ActionSupport {
    @Autowired
    private UserService userService;

    private int page;
    private int rows;
    private String key;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String sex;
    private String birthday;
    private int id;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 添加或修改用户
     * @throws IOException
     */
    public void addUpdate() throws IOException {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);
        user.setSex(sex);
        user.setBirthday(new Timestamp(MyUtils.strTimeToDate(birthday,"yyyy-MM-dd hh:mm:ss").getTime()));
        String b =  userService.addUpdate(user);
        JsonResult jsonResult = new JsonResult();
        if(b.indexOf("成功") !=  -1){
            jsonResult.setFlag(true);
        }else {
            jsonResult.setFlag(false);
        }
        jsonResult.setMsg(b);
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out = response.getWriter();
        out.print(JSON.toJSONString(jsonResult));
        out.close();
    }

    /**
     * 用户列表
     * @throws IOException
     */
    public void list() throws IOException {
        User user = new User();
        user.setPage(getPage());
        user.setRows(getRows());
        if(MyUtils.isEmpty(getKey())){
            user.setKey("");
        }else {
            user.setKey(getKey());
        }
        JsonEasyUi json = new JsonEasyUi();
        PageBean pageBean = userService.queryForPage(user);
        json.setRows(pageBean.getList());
        json.setTotal(pageBean.getAllRow());
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out = response.getWriter();
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        out.print(JSON.toJSONString(json, SerializerFeature.WriteDateUseDateFormat));
        out.close();
    }

    /**
     * 删除用户
     */
    public void del() throws IOException {
        JsonResult jsonResult = new JsonResult();
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out = response.getWriter();
        if(id==0){
            jsonResult.setFlag(false);
            jsonResult.setMsg("删除失败");
        }else {
            if(userService.del(id)){
                jsonResult.setFlag(true);
                jsonResult.setMsg("删除成功");
            }else {
                jsonResult.setFlag(false);
                jsonResult.setMsg("删除失败");
            }
        }
        out.print(JSON.toJSONString(jsonResult));
        out.close();
    }

    /**
     * 性别统计
     * @throws IOException
     */
    public void sex() throws IOException {
        PrintWriter out= ServletActionContext.getResponse().getWriter();
        int nan = userService.countSex("男");
        int nv = userService.countSex("女");
        Map<Object,Object> map = new HashMap<>();
        map.put("name","男");
        map.put("value",nan);
        Map<Object,Object> map2 = new HashMap<>();
        map2.put("name","女");
        map2.put("value",nv);
        List<Map<Object,Object>> mapList = new ArrayList<>();
        mapList.add(map);
        mapList.add(map2);
        out.print(JSON.toJSONString(mapList));//返回json格式数据
        out.close();
    }
}
