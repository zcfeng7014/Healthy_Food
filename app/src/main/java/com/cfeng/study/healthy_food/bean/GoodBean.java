package com.cfeng.study.healthy_food.bean;

/**
 * Created by Administrator on 2017/9/27 0027.
 */

public class GoodBean {

    /**
     * good_id : 1
     * product_id : 1
     * wuliu_id : pg01
     * datetime : 2017-09-27 11:44:20
     */

    private int good_id;
    private int product_id;
    private String wuliu_id;
    private String datetime;

    public int getGood_id() {
        return good_id;
    }

    public void setGood_id(int good_id) {
        this.good_id = good_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getWuliu_id() {
        return wuliu_id;
    }

    public void setWuliu_id(String wuliu_id) {
        this.wuliu_id = wuliu_id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
