package com.example.hello.myapplianxi2.view.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hello.myapplianxi2.R;


/**
 * Created by Administrator on 2018/1/14.
 */

public class YizhifuVH extends RecyclerView.ViewHolder {

    public TextView state;
    public TextView price;
    public TextView time;
    public Button btn;

    public YizhifuVH(View itemView) {
        super(itemView);

        //找到控件
        state = itemView.findViewById(R.id.state);
        price = itemView.findViewById(R.id.price);
        time = itemView.findViewById(R.id.time);
        btn = itemView.findViewById(R.id.btn);
    }
}
