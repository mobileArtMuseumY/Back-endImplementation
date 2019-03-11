package com.artmall.DO;

/**
 * 基础信息
 *
 * @author mllove
 * @create 2018-09-10 20:47
 **/

public class BaseEntity {
    private  Integer page = 1;
    private  Integer rous = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRous() {
        return rous;
    }

    public void setRous(Integer rous) {
        this.rous = rous;
    }
}
