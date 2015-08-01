package com.techidea.weiboapp.entity.response;

import com.techidea.weiboapp.entity.Status;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/7/30.
 */
public class StatusTimeLineResponse {

    private ArrayList<Status> statuses;
    private int total_number;

    public ArrayList<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(ArrayList<Status> statuses) {
        this.statuses = statuses;
    }

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }
}
