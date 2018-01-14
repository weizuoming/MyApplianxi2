package com.example.hello.myapplianxi2.view.fragment.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.hello.myapplianxi2.R;
import com.example.hello.myapplianxi2.model.bean.Daizhifu_bean;
import com.example.hello.myapplianxi2.util.My_api;
import com.example.hello.myapplianxi2.util.OkHttp3Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/14.
 */

public class DaizhifuAdapter extends RecyclerView.Adapter<DaizhifuVH> {

    private Handler handler;
    private List<Daizhifu_bean.DataBean> list;
    private Context context;
    private DaizhifuVH daizhifuVH;

    public DaizhifuAdapter(Context context, List<Daizhifu_bean.DataBean> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;
    }

    @Override
    public DaizhifuVH onCreateViewHolder(ViewGroup parent, int viewType) {
        //找到布局
        View view = View.inflate(context, R.layout.daizhifu_vh_layout, null);
        daizhifuVH = new DaizhifuVH(view);
        return daizhifuVH;
    }

    @Override
    public void onBindViewHolder(DaizhifuVH holder, final int position) {
        //赋值
        daizhifuVH.state.setText("待支付");
        daizhifuVH.price.setText(list.get(position).getPrice() + "");
        daizhifuVH.price.setText(list.get(position).getCreatetime());

        daizhifuVH.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", "4123");
                map.put("orderId", position + "");
                map.put("status", "2");
                OkHttp3Util.doPost(My_api.No_formAPI, map, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {

                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = position+"";
                            handler.sendMessage(msg);
                        }
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
