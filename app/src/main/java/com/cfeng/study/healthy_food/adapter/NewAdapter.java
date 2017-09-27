package com.cfeng.study.healthy_food.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cfeng.study.healthy_food.R;
import com.cfeng.study.healthy_food.bean.NewBean;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;

import Utils.ViewUtils;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class NewAdapter extends BaseAdapter {
    Context mcontext;
    TestNormalAdapter mtna;
    ArrayList<NewBean> mlist;
    public NewAdapter(Context context, ArrayList<NewBean> list, TestNormalAdapter tna){
        mcontext=context;
        mtna=tna;
        mlist=list;
    }
@Override
public int getCount() {
        return mlist.size()+1;
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
        if(i==0)
        {
        RollPagerView ppv= new RollPagerView(mcontext);
        ppv.setAnimationDurtion(500);
        ppv.setAdapter(mtna);
        ppv.setHintView(new ColorPointHintView(mcontext, Color.RED, Color.WHITE));

        ppv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewUtils.dip2px(mcontext,160)));
        return ppv;
        }

        if(view==null||view.getClass()==RollPagerView.class){
        view= (LinearLayout) LinearLayout.inflate(mcontext, R.layout.newitem,null);
        }
        LinearLayout ll= (LinearLayout) view;
        LinearLayout a= (LinearLayout) ll.findViewById(R.id.img_list);
        a.removeAllViews();
        TextView tv= (TextView) ll.findViewById(R.id.title);
        TextView bv= (TextView) ll.findViewById(R.id.bottom);
        TextView dv=(TextView) ll.findViewById(R.id.dist);
        tv.setText(mlist.get(i-1).getTitle());
        dv.setText(mlist.get(i-1).getIntro());
        bv.setText("来 源： "+mlist.get(i-1).getAuthor()+"   时间："+mlist.get(i-1).getDatetime()+"");
        return ll;
        }
        }