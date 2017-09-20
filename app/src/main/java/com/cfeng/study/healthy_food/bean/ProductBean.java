package com.cfeng.study.healthy_food.bean;

/**
 * Created by Administrator on 2017/9/20 0020.
 */

public class ProductBean {

    /**
     * product_id : 0
     * product_name : 红富士苹果
     * company_name : 测试基地1
     * kind_name : 水果
     * product_size : 50kg/箱
     * product_price : 150
     */

    private int product_id;
    private String product_name;
    private String company_name;
    private String kind_name;
    private String product_size;
    private String product_price;
    /**
     * product_pic : http://p4.so.qhimgs1.com/bdr/_240_/t0108f3e2dd1a2a7894.jpg
     */

    private String product_pic;

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
}
