package com.cfeng.study.healthy_food.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/14.
 */

public class Product_info implements Serializable {

    /**
     * product_id : 0
     * product_name : 红富士苹果
     * product_size : 50kg/箱
     * product_price : 150
     * product_pic1 : http://p4.so.qhimgs1.com/bdr/_240_/t0108f3e2dd1a2a7894.jpg
     * product_pic2 : null
     * product_pic3 : null
     * company_stata_name : 普通基地
     * company_intro : 测试
     * company_code : test1
     * company_place : 测试地点1
     * company_name : 测试基地1
     * kind_name : 水果
     */

    private int product_id;
    private String product_name;
    private String product_size;
    private String product_price;
    private String product_pic;
    private String company_stata_name;
    private String company_intro;
    private String company_code;
    private String company_place;
    private String company_name;
    private String kind_name;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_size() {
        return product_size;
    }

    public void setProduct_size(String product_size) {
        this.product_size = product_size;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_pic() {
        return product_pic;
    }

    public void setProduct_pic(String product_pic) {
        this.product_pic = product_pic;
    }

    public String getCompany_stata_name() {
        return company_stata_name;
    }

    public void setCompany_stata_name(String company_stata_name) {
        this.company_stata_name = company_stata_name;
    }

    public String getCompany_intro() {
        return company_intro;
    }

    public void setCompany_intro(String company_intro) {
        this.company_intro = company_intro;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getCompany_place() {
        return company_place;
    }

    public void setCompany_place(String company_place) {
        this.company_place = company_place;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getKind_name() {
        return kind_name;
    }

    public void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }
}
