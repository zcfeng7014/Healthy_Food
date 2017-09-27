package com.cfeng.study.healthy_food.bean;

/**
 * Created by Administrator on 2017/9/27 0027.
 */

public class Express {

    /**
     * no : 2
     * id : pg01
     * time : 2017-09-27 11:42:25
     * event : 宿迁收货，分配到大润发超市
     * check_info : 合格
     */

    private int no;
    private String id;
    private String time;
    private String event;
    private String check_info;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getCheck_info() {
        return check_info;
    }

    public void setCheck_info(String check_info) {
        this.check_info = check_info;
    }
}
