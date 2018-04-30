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
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.hswebframework.expands.office.excel.ExcelIO;
import org.hswebframework.expands.office.excel.config.Header;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author q18idc.com QQ993143799
 * Created by q18idc.com QQ993143799 on 2018/3/2
 */
public class UserAction extends ActionSupport {
    @Autowired
    private UserService userService;

    @Getter @Setter private int page;
    @Getter @Setter private int rows;
    @Getter @Setter private String key;
    @Getter @Setter private String username;
    @Getter @Setter private String password;
    @Getter @Setter private String phone;
    @Getter @Setter private String email;
    @Getter @Setter private String sex;
    @Getter @Setter private String birthday;
    @Getter @Setter private int id;

    @Getter @Setter private File file;
    //文件名
    @Getter @Setter private String fileFileName;
    //文件类型
    @Getter @Setter private String fileContentType;

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

    /**
     *Excel上传 导入
     */
    public void upload() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        Map<String,Object> map = new HashMap<>();
        //总条数
        int sun = 0;
        //成功条数
        int success = 0;
        //失败条数
        int error = 0;
        PrintWriter out = response.getWriter();
        if(file!=null){
//            System.err.println(file);
//            System.err.println(fileFileName);
//            System.err.println(fileContentType);

            InputStream inputStream = new FileInputStream(file);

            List<Map<String, Object>> list = ExcelIO.read2Map(inputStream);
            sun = list.size();
            for (Map<String, Object> stringObjectMap : list) {
                User user  = new User();
                user.setUsername(stringObjectMap.get("用户名").toString());
                user.setPassword(stringObjectMap.get("密码").toString());
                user.setPhone(stringObjectMap.get("电话").toString());
                user.setEmail(stringObjectMap.get("邮箱").toString());
                user.setSex(stringObjectMap.get("性别").toString());
                Date birthday = (Date) stringObjectMap.get("生日");
                user.setBirthday(new Timestamp(birthday.getTime()));
                String s = userService.addUpdate(user);
                if(s.indexOf("成功") == -1){
                    error++;
                }else {
                    success++;
                }
            }

            map.put("flag",true);
            map.put("msg","总条数：" + sun + " 成功：" + success + "条 失败：" + error + "条");
        }else {
            map.put("flag",false);
            map.put("msg","导入失败");
        }
        out.print(JSON.toJSONString(map));
    }

    /**
     * Excel导出
     */
    public void export() throws UnsupportedEncodingException {
        HttpServletResponse response = ServletActionContext.getResponse();
        User user = new User();
        user.setPage(1);
        user.setRows(1000);
        user.setKey("");
        PageBean pageBean = userService.queryForPage(user);
        List list = pageBean.getList();

        List<Header> headers = new LinkedList<>();
        List<Object> data = new ArrayList<>();
        for (Object o : list) {
            data.add(o);
        }

        headers.add(new Header("用户名","username"));
        headers.add(new Header("密码","password"));
        headers.add(new Header("电话","phone"));
        headers.add(new Header("邮箱","email"));
        headers.add(new Header("性别","sex"));
        headers.add(new Header("生日","birthday"));

        String fileName = "测试.xlsx";
        //设置响应头和客户端保存文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName*=UTF-8''" + URLEncoder.encode(fileName,"UTF-8"));
        try {
            OutputStream os = response.getOutputStream();
            ExcelIO.write(os, headers, data);
            os.flush();
            os.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
