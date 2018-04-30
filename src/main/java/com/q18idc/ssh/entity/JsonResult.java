package com.q18idc.ssh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author q18idc.com QQ993143799
 * Created by q18idc.com QQ993143799 on 2018/3/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult implements Serializable{
    private static final long serialVersionUID = 3994139355894978030L;
    private boolean flag = false;
    private String msg = "未知错误";
}
