package com.techidea.weiboapp.entity;

import com.techidea.weiboapp.BaseActivity;

/**
 * Created by zhangchao on 2015/8/29.
 */
public class Ad extends BaseEntity{
    private long id;
    private String mark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

}
