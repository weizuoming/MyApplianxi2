package com.example.hello.myapplianxi2.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hello.myapplianxi2.R;
import com.example.hello.myapplianxi2.model.bean.Daizhifu_bean;
import com.example.hello.myapplianxi2.presenter.From_P;
import com.example.hello.myapplianxi2.util.My_api;
import com.example.hello.myapplianxi2.view.fragment.adapter.DaizhifuAdapter;
import com.example.hello.myapplianxi2.view.fragment.interF.FormDaizhifuV_I;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;


/**
 * Created by Administrator on 2018/1/13.
 */

public class Daizhifu extends Fragment implements FormDaizhifuV_I {

    private RecyclerView recyclerView;
    private From_P from_p;
    private int page = 1;
    private SmartRefreshLayout refreshLayout;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1) {
                String a = (String) msg.obj;
                daizhifuAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "订单已取消" + a, Toast.LENGTH_SHORT).show();
            }
        }
    };
    private DaizhifuAdapter daizhifuAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.daizhifu_layout, null);

        recyclerView = view.findViewById(R.id.recyclerview);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //首先获取数据  MVP
        from_p = new From_P(this);
        from_p.getdata(My_api.Over_formApi, page);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //刷新
                from_p.getdata(My_api.Over_formApi, 1);
                refreshlayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                //加载
                from_p.getdata(My_api.Over_formApi, page);
                refreshlayout.finishLoadmore();
            }
        });
    }

    @Override
    public void success(final String s) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //获取到数据,解析
                Gson gson = new Gson();
                Daizhifu_bean daizhifu_bean = gson.fromJson(s, Daizhifu_bean.class);
                final List<Daizhifu_bean.DataBean> data = daizhifu_bean.getData();
                //设置适配器  和  布局
                daizhifuAdapter = new DaizhifuAdapter(getActivity(), data, handler);
                recyclerView.setAdapter(daizhifuAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        });
    }

    //这个方法为的是解决MVP内存泄漏的问题
    // protected原本是这个,不是public  protected的意思是对所有用户开放

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除绑定
        if (from_p != null) {
            from_p.destory();
        }
    }
}
