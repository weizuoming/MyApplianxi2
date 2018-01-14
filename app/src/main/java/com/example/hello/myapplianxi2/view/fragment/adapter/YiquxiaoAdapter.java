package com.example.hello.myapplianxi2.view.fragment.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.hello.myapplianxi2.R;
import com.example.hello.myapplianxi2.model.bean.Yiquxiao_bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/14.
 */

public class YiquxiaoAdapter extends RecyclerView.Adapter<YiquxiaoVH> {

    private List<Yiquxiao_bean.DataBean> list;
    private Handler handler;
    private Context context;

    public YiquxiaoAdapter(Context context, List<Yiquxiao_bean.DataBean> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;
    }


    @Override
    public YiquxiaoVH onCreateViewHolder(ViewGroup parent, int viewType) {
        //找到布局
        View view = View.inflate(context, R.layout.yiquxiao_vh_layout, null);
        YiquxiaoVH yiquxiaoVH = new YiquxiaoVH(view);
        return yiquxiaoVH;
    }

    @Override
    public void onBindViewHolder(YiquxiaoVH holder, final int position) {
        //赋值
        holder.state.setText("已取消");
        holder.price.setText(list.get(position).getPrice() + "");
        holder.price.setText(list.get(position).getCreatetime());

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = 1;
                msg.obj = position + "";
                handler.sendMessage(msg);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
