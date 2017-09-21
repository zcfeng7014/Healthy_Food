package com.cfeng.study.healthy_food;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cfeng.study.healthy_food.bean.FoodBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductInfoActivity extends AppCompatActivity {

    @BindView(R.id.pname)
    TextView pname;
    @BindView(R.id.pid)
    TextView pid;
    @BindView(R.id.p_price)
    TextView pPrice;
    @BindView(R.id.company_code)
    TextView company_code;
    @BindView(R.id.pclass)
    TextView pclass;
    @BindView(R.id.pdatetime)
    TextView pdatetime;
    @BindView(R.id.pplace)
    TextView pplace;
    @BindView(R.id.pintro)
    TextView pintro;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.companyintro)
    TextView companyintro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        initdata(intent);

    }

    private void initdata(Intent intent) {
        FoodBean bean = (FoodBean) intent.getSerializableExtra("bean");
        pname.setText(bean.getProduct_name());
        pid.setText(bean.getProduct_id() + "");
        pclass.setText(bean.getKind_name());
        pdatetime.setText(bean.getCompany_name());
        pintro.setText(bean.getCompany_intro());
        pplace.setText(bean.getCompany_place());
        pPrice.setText(bean.getProduct_price());
        Glide.with(this).load(bean.getProduct_pic()).centerCrop().into(logo);
        size.setText(bean.getProduct_size());
        company_code.setText(bean.getCompany_code());
        companyintro.setText(bean.getCompany_intro());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

}
