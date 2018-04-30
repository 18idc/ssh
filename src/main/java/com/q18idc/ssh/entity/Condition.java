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
public class Condition implements Serializable {
    private static final long serialVersionUID = 285181753124344551L;
    private String key;
    private Integer page;
    private Integer rows;
}
