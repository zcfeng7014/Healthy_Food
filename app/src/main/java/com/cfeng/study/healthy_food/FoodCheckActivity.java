package com.cfeng.study.healthy_food;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodCheckActivity extends AppCompatActivity {

    @BindView(R.id.food1)
    EditText food1;
    @BindView(R.id.food2)
    EditText food2;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.table)
    TextView table;
    @BindView(R.id.sp)
    Spinner sp;
    private ArrayAdapter<CharSequence> adapterCity = null;
    private static String[] cityInfo={"北京","江苏","浙江","上海","北京","江苏","浙江","上海","北京","江苏","浙江","上海","北京","江苏","浙江","上海","北京","江苏","浙江","上海","北京","江苏","浙江","上海"};
    @OnClick(R.id.btn)
    public void click(View view) {
        table.setText(food1.getText().toString() + food2.getText().toString()+sp.getSelectedItem().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_check);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.adapterCity = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_dropdown_item, cityInfo);
        sp.setAdapter(adapterCity);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
