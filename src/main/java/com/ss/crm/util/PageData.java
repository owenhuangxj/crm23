package com.ss.crm.util;

import java.io.Serializable;
import java.util.List;

public class PageData<T> implements Serializable {

    private List<T> list;
    private Integer rows;

    public List<T> getList() {
        return list;
    }

    public Integer getRows() {
        return rows;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public PageData(List<T> list, Integer rows) {
        this.list = list;
        this.rows = rows;
    }
}
