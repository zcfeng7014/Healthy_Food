package com.cfeng.study.healthy_food;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cfeng.study.healthy_food.config.WebConfig;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodCheckActivity extends AppCompatActivity {

    @BindView(R.id.table)
    TextView table;
    @BindView(R.id.sp)
    Spinner sp;
    @BindView(R.id.type)
    Spinner type;
    private ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter=null;
    ArrayAdapter<String>adapter2=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_check);
        ButterKnife.bind(this);
        initview();
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((App)getApplication()).requestQueue.add(new JsonArrayRequest(WebConfig.get_food_list_by_type+ URLEncoder.encode(adapter.getItem(i)), new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        adapter2.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                adapter2.add(response.getString(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter2.notifyDataSetChanged();
                        sp.setSelection(0);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((App)getApplication()).requestQueue.add(new JsonObjectRequest(WebConfig.get_food_info_by_name+ URLEncoder.encode(adapter2.getItem(sp.getSelectedItemPosition())), null,new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            table.setText(response.getString("check_warning"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ((App)getApplication()).requestQueue.add(new JsonArrayRequest(WebConfig.get_type_list, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                adapter.clear();
                for (int i=0;i<response.length();i++){
                    try {
                        adapter.add(response.getString(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));
    }

    private void initview() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter  = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);
        sp.setAdapter(adapter2);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
