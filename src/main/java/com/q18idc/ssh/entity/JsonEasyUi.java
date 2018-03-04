package com.q18idc.ssh.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author q18idc.com QQ993143799
 * Created by q18idc.com QQ993143799 on 2018/3/2
 */
public class JsonEasyUi implements Serializable {
    private static final long serialVersionUID = 6846340243217159989L;
    private int total=-1;
    private List rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
