package com.q18idc.ssh.Interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * response输出编码 拦截器 解决输出乱码 问号的问题
 * Created by q18idc.com QQ993143799 on 2017/12/22
 */
public class CharacterEncodingInterceptor extends AbstractInterceptor {
    @Getter
    @Setter
    private String code = "UTF-8";

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        request.setCharacterEncoding(code);
        response.setCharacterEncoding(code);
        response.setContentType("application/json;charset="+code+";");
//        response.setContentType("text/html;charset=" + code + ";");
        /** 设置响应头允许ajax跨域访问 **/
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        //放行
        return actionInvocation.invoke();
    }
}
