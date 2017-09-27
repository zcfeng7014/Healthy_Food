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
import com.cfeng.study.healthy_food.adapter.NewAdapter;
import com.cfeng.study.healthy_food.adapter.TestNormalAdapter;
import com.cfeng.study.healthy_food.bean.NewBean;
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

public class NewActivity extends AppCompatActivity {
    Handler handler=new Handler();
    TestNormalAdapter tna;
    NewAdapter adapter;
    int page=1;
    int maxpage=0;
    ArrayList<NewBean> list=new ArrayList<>();
    @BindView(R.id.list_view)
    PullToRefreshListView listView;
    private int[] imgs = {
            R.drawable.i1,
            R.drawable.i2,
            R.drawable.i3,
            R.drawable.i4,
    };
    App app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        ButterKnife.bind(this);
         app=(App) getApplication();
        tna= new TestNormalAdapter(imgs);
        adapter=new NewAdapter(this,list,tna);
        listView.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        // 添加滑动到底部的监听器
        listView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {

            @Override
            public void onLastItemVisible() {
                    loadmore();

            }
        });
        listView.setRefreshing(true);
        listView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"正在加载,id为"+list.get(position-1).getId(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),WebViewActivity.class);
                intent.putExtra("url", WebConfig.shownew+""+list.get(position-1).getId());
                intent.putExtra("title",list.get(position-1).getTitle());
                startActivity(intent);
            }
        });
    }

    private void loadmore() {
        app.requestQueue.add(new JsonObjectRequest(WebConfig.getnews+"?page="+(page+1),null, new Response.Listener<JSONObject>()
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
                list.addAll ((ArrayList)gson.fromJson(arr.toString(),new TypeToken<ArrayList<NewBean>>() {}.getType()));;
                adapter.notifyDataSetChanged();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        }));
    }
    private void load() {
        app.requestQueue.add(new JsonObjectRequest(WebConfig.getnews,null, new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                list.clear();
                page=response.optInt("current_page");
                maxpage=response.optInt("last_page");
                JSONArray arr=response.optJSONArray("data");
                Gson gson=new Gson();
                list.addAll ((ArrayList)gson.fromJson(arr.toString(),new TypeToken<ArrayList<NewBean>>() {}.getType()));;

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
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
