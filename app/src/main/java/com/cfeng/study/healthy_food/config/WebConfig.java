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
    String get_type_list=site_url+"get_type_list.html";
    String get_food_list_by_type=site_url+"get_food_list_by_type.html?type=";
    String get_food_info_by_name=site_url+"get_food_info_by_name.html?name=";
    String get_express_info=site_url+"get_wuliu_info_by_id/id/";
}
