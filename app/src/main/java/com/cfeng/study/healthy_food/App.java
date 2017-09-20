package com.cfeng.study.healthy_food;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class App extends Application {
    public RequestQueue requestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue= Volley.newRequestQueue(getApplicationContext());
    }
}
