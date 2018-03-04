package com.q18idc.ssh.entity;

import java.io.Serializable;

/**
 * @author q18idc.com QQ993143799
 * Created by q18idc.com QQ993143799 on 2018/3/2
 */
public class JsonResult implements Serializable{
    private static final long serialVersionUID = 3994139355894978030L;
    private boolean flag = false;
    private String msg = "未知错误";

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "flag=" + flag +
                ", msg='" + msg + '\'' +
                '}';
    }
}
