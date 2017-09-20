package com.cfeng.study.healthy_food;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cfeng.study.healthy_food.bean.NewBean;
import com.cfeng.study.healthy_food.bean.ProductBean;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;

import Utils.ViewUtils;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class ProductAdapter extends BaseAdapter {
    Context mcontext;
    ArrayList<ProductBean> mlist;
    public ProductAdapter(Context context, ArrayList<ProductBean> list){
        mcontext=context;
        mlist=list;
    }
@Override
public int getCount() {
   if(mlist.size()>0)
       return mlist.size()+1;
    else
        return 0;

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
        if(i==mlist.size()){
            return LinearLayout.inflate(mcontext, R.layout.loadmore,null);
        }
        if(view==null||view.getId()==R.id.loadmore){
        view= (LinearLayout) LinearLayout.inflate(mcontext, R.layout.product_item,null);
        }
        LinearLayout ll= (LinearLayout) view;
        TextView tv=ll.findViewById(R.id.dist);
        tv.setText("名称："+mlist.get(i).getProduct_name()+"   类型："+mlist.get(i).getKind_name());
        tv.append("\n单价："+mlist.get(i).getProduct_price()+"￥   规格："+mlist.get(i).getProduct_size());
        tv.append("\n产地："+mlist.get(i).getCompany_name());
        ImageView iv=ll.findViewById(R.id.logo);
        Glide.with(mcontext).load(mlist.get(i).getProduct_pic()).centerCrop().into(iv);
        return ll;
        }

}