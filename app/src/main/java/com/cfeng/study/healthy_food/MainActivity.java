package com.cfeng.study.healthy_food;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.acker.simplezxing.activity.CaptureActivity;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.cfeng.study.healthy_food.bean.FoodBean;
import com.cfeng.study.healthy_food.config.WebConfig;
import com.google.gson.Gson;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.lzx.autoverticallibrary.bean.AutoVerticalViewDataData;
import com.lzx.autoverticallibrary.utils.AutoVerticalViewView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE_PERMISSION = 0x1111;
    @BindView(R.id.gv)
    GridView gv;
    @BindView(R.id.roll_view_pager)
    RollPagerView rollViewPager;
    @BindView(R.id.tb)
    AutoVerticalViewView autoVerticalViewView;
    TestNormalAdapter tna;
    private int[] imgs = {
            R.drawable._new,
 R.drawable.chaxun,
            R.drawable.wl,
            R.drawable.jd,
            R.drawable.sm,
            R.drawable.cm,
    };
    private int[] ppt = {
            R.drawable.i1,
            R.drawable.i2,
            R.drawable.i3,
            R.drawable.i4,
    };
    private String[] title = {
            "资讯",
            "饮食安全查询",
            "物流查询",
            "产品列表",
            "扫一扫",
            "联系我们",
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("首页");
        ButterKnife.bind(this);
        getActionBar();
        initview();
        initdata();

    }

    private void initview() {
        rollViewPager.setAnimationDurtion(500);
        tna=new TestNormalAdapter(ppt);
        rollViewPager.setAdapter(tna);
        rollViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE));
        gv.setAdapter(new MyBaseAdapter());
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        jumpTonew();
                        break;
                    case 1:
                        jumpTocheck();
                        break;
                    case 2:
                        jumpTowuliu();
                        break;
                    case 3:
                        jumpToproduct();
                        break;
                    case 4:
                        get_product_info("0");
                      //  JumpToQR();
                        break;
                    case 5:
                        JumpToCm();
                        break;
                }
            }
        });
    }

    private void JumpToCm() {startActivity(new Intent(this,ConnectMeActivity.class));
    }

    public void JumpToQR() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Do not have the permission of camera, request it.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQ_CODE_PERMISSION);
        } else {
            // Have gotten the permission
            startCaptureActivityForResult();
        }
    }
    private void jumpToproduct() {startActivity(new Intent(this,ProductActivity.class));}

    private void jumpTowuliu() {startActivity(new Intent(this,WuliuActivity.class));}

    private void jumpTocheck() {startActivity(new Intent(this,FoodCheckActivity.class));}

    private void jumpTonew() {
        startActivity(new Intent(this,NewActivity.class));
    }

    private void initdata() {
        final List<AutoVerticalViewDataData> data = new ArrayList();
        data.add(new AutoVerticalViewDataData("疯传", "不动产统一登记年底全国联网 可以“以人查房”吗？", "1"));
        data.add(new AutoVerticalViewDataData("头条", "男科医院聘美女主播直播招揽病人 当地介入调查", "2"));
        data.add(new AutoVerticalViewDataData("热议", "年轻人专属 15万内高人气两厢家用车推荐", "3"));
        data.add(new AutoVerticalViewDataData("哈哈", "韩检方第4次看守所中讯问朴槿惠 集中调查受贿嫌疑,韩检方第4次看守所中讯问朴槿惠 集中调查受贿嫌疑韩检方第4次看守所中讯问朴槿惠 集中调查受贿嫌疑", "4"));
        data.add(new AutoVerticalViewDataData("呵呵", "特朗普任内对朝采取军事行动？ 美或为此付出代价", "5"));
        autoVerticalViewView.setViews(data);
        autoVerticalViewView.setOnItemClickListener(new AutoVerticalViewView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "你点击了" + data.get(position).getValue(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startCaptureActivityForResult() {
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(CaptureActivity.KEY_NEED_BEEP, CaptureActivity.VALUE_BEEP);
        bundle.putBoolean(CaptureActivity.KEY_NEED_VIBRATION, CaptureActivity.VALUE_VIBRATION);
        bundle.putBoolean(CaptureActivity.KEY_NEED_EXPOSURE, CaptureActivity.VALUE_NO_EXPOSURE);
        bundle.putByte(CaptureActivity.KEY_FLASHLIGHT_MODE, CaptureActivity.VALUE_FLASHLIGHT_OFF);
        bundle.putByte(CaptureActivity.KEY_ORIENTATION_MODE, CaptureActivity.VALUE_ORIENTATION_AUTO);
        bundle.putBoolean(CaptureActivity.KEY_SCAN_AREA_FULL_SCREEN, CaptureActivity.VALUE_SCAN_AREA_FULL_SCREEN);
        bundle.putBoolean(CaptureActivity.KEY_NEED_SCAN_HINT_TEXT, CaptureActivity.VALUE_SCAN_HINT_TEXT);
        intent.putExtra(CaptureActivity.EXTRA_SETTING_BUNDLE, bundle);
        startActivityForResult(intent, CaptureActivity.REQ_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_CODE_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // User agree the permission
                    startCaptureActivityForResult();
                } else {
                    // User disagree the permission
                    Toast.makeText(this, "You must agree the camera permission request before you use the code scan function", Toast.LENGTH_LONG).show();
                }
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CaptureActivity.REQ_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        String str=data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT);
                        get_product_info(str);
                        break;
                    case RESULT_CANCELED:
                        if (data != null) {
                            // for some reason camera is not working correctly

                        }
                        break;
                }
                break;
        }
    }

    private void get_product_info(String str) {


        ((App)getApplication()).requestQueue.add(new StringRequest(WebConfig.get_product_info+str, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String resonse) {
                        System.out.println(resonse);
                        Gson gson = new Gson();
                        FoodBean bean = gson.fromJson(resonse, FoodBean.class);
                        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                        intent.putExtra("bean", bean);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }));

    }

    class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LinearLayout ll = (LinearLayout) View.inflate(getApplicationContext(), R.layout.item, null);
            ImageView iv = ll.findViewById(R.id.image);
            TextView tv = ll.findViewById(R.id.text);
            tv.setText(title[i]);
            Glide.with(getApplicationContext()).load(imgs[i]).into(iv);
            return ll;
        }
    }
}
