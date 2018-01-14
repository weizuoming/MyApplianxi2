package com.example.hello.myapplianxi2.view.fragment.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.hello.myapplianxi2.R;
import com.example.hello.myapplianxi2.model.bean.Yizhifu_bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/14.
 */

public class YizhifuAdapter extends RecyclerView.Adapter<YizhifuVH> {

    private final List<Yizhifu_bean.DataBean> list;
    private Handler handler;

    private Context context;

    public YizhifuAdapter(Context context, List<Yizhifu_bean.DataBean> data, Handler handler) {
        this.context = context;
        this.list = data;
        this.handler = handler;
    }


    @Override
    public YizhifuVH onCreateViewHolder(ViewGroup parent, int viewType) {
        //找到布局
        View view = View.inflate(context, R.layout.yizhifu_vh_layout, null);
        YizhifuVH yizhifuVH = new YizhifuVH(view);
        return yizhifuVH;
    }

    @Override
    public void onBindViewHolder(YizhifuVH yizhifuVH, final int position) {
        //赋值
        yizhifuVH.state.setText("已支付");
        yizhifuVH.price.setText(list.get(position).getPrice() + "");
        yizhifuVH.price.setText(list.get(position).getCreatetime());

        yizhifuVH.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = 1;
                msg.obj = position+"";
                handler.sendMessage(msg);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
