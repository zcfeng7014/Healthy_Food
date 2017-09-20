package com.cfeng.study.healthy_food.config;

/**
 * Created by Administrator on 2017/7/26.
 */

public interface WebConfig {
    String site_url="http://121.42.161.20:81/";
    String getnews = site_url+"getnews.html";
    String shownew =site_url+"show_news.html?id=";
    String get_product_info=site_url+"get_product_info.html?id=";
    String get_product_list=site_url+"get_product_list.html";
}
