package com.q18idc.ssh.action.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.q18idc.ssh.entity.User;
import com.q18idc.ssh.service.UsersService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author q18idc.com QQ993143799
 * @date 2018/4/30 10:57
 */
public class UserRelationAction extends ActionSupport {
    @Getter @Setter HttpServletRequest request = ServletActionContext.getRequest();
    @Getter @Setter HttpServletResponse response = ServletActionContext.getResponse();

    @Autowired
    private UsersService usersService;

    @Getter @Setter private Integer id;

    /**
     * 一对一查询
     * @throws IOException
     */
    public void onetoone() throws IOException {
        PrintWriter out = getResponse().getWriter();
        //一对一查询  根据用户ID查询出用户和用户对应的卡
        User user = usersService.oneToOne(id);
        out.print(JSON.toJSONString(user,SerializerFeature.SkipTransientField));
        out.close();
    }

    /**
     * 一对多查询
     */
    public void onetomany() throws IOException {
        PrintWriter out = getResponse().getWriter();
        //一对多查询  根据用户ID查询出用户和用户对应的卡
        User user = usersService.oneToMany(id);
        out.print(JSON.toJSONString(user,SerializerFeature.SkipTransientField));
        out.close();
    }

    /**
     * 多对多
     */
    public void manytomany() throws IOException {
        PrintWriter out = getResponse().getWriter();
        Map<String, Object> map = usersService.manyToMany(1, 1);
        out.print(JSON.toJSONString(map,SerializerFeature.SkipTransientField));
        out.close();
    }


}
