package com.cfeng.study.healthy_food;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.cfeng.study.healthy_food.bean.Product_info;
import com.cfeng.study.healthy_food.bean.ProductBean;
import com.cfeng.study.healthy_food.config.WebConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends AppCompatActivity {
    App app;
    Handler handler=new Handler();
    @BindView(R.id.list)
    PullToRefreshListView listView;
    ArrayList<ProductBean> mlist=new ArrayList<>();
    int page=0;
    int maxpage=0;
    ProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        app= (App) getApplication();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter=new ProductAdapter(this,mlist);
        listView.setAdapter(adapter);
        load();
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //设置下拉时显示的日期和时间
                String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // 更新显示的label
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                // 开始执行异步任务，传入适配器来进行数据改变
                load();


            }});
        listView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                loadmore();

            }
        });
        listView.setRefreshing(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                get_product_info(""+mlist.get(i-1).getProduct_id());
            }
        });
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

    }
    private void get_product_info(String str) {


        ((App)getApplication()).requestQueue.add(new StringRequest(WebConfig.get_product_info+str, new Response.Listener<String>() {
            @Override
            public void onResponse(String resonse) {
                System.out.println(resonse);
                Gson gson = new Gson();
                Product_info bean = gson.fromJson(resonse, Product_info.class);
                Intent intent = new Intent(getApplicationContext(), ProductInfoActivity.class);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }));

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void load() {
        app.requestQueue.add(new JsonObjectRequest(WebConfig.get_product_list,null, new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response) {
                mlist.clear();
                System.out.println("-------------------"+response.toString()+"----------------");
                page=response.optInt("current_page");
                maxpage=response.optInt("last_page");
                JSONArray arr=response.optJSONArray("data");
                Gson gson=new Gson();
                mlist.addAll ((ArrayList)gson.fromJson(arr.toString(),new TypeToken<ArrayList<ProductBean>>() {}.getType()));

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listView.onRefreshComplete();
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        }));
    }

    private void loadmore() {
        app.requestQueue.add(new JsonObjectRequest(WebConfig.get_product_list+"?page="+(page+1),null, new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response) {
                if(page>=response.optInt("current_page"))
                {
                    Toast.makeText(getApplicationContext(),"已经达到最底",Toast.LENGTH_SHORT).show();
                }
                page=response.optInt("current_page");
                maxpage=response.optInt("last_page");
                JSONArray arr=response.optJSONArray("data");
                Gson gson=new Gson();
                mlist.addAll ((ArrayList)gson.fromJson(arr.toString(),new TypeToken<ArrayList<ProductBean>>() {}.getType()));
                adapter.notifyDataSetChanged();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        }));
    }

}
