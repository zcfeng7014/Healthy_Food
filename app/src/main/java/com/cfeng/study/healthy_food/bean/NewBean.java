package com.cfeng.study.healthy_food.bean;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class NewBean {

    /**
     * title : 测试1
     * author : 测试员1
     * datetime : 2017-9-15
     * intro : 简介
     */

    private String title;
    private String author;
    private String datetime;
    private String intro;
    /**
     * id : 1
     */

    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
