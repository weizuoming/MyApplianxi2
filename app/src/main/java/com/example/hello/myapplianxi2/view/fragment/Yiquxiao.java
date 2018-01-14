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
import com.example.hello.myapplianxi2.model.bean.Yiquxiao_bean;
import com.example.hello.myapplianxi2.presenter.Yiquxiao_P;
import com.example.hello.myapplianxi2.util.My_api;
import com.example.hello.myapplianxi2.view.fragment.adapter.YiquxiaoAdapter;
import com.example.hello.myapplianxi2.view.fragment.interF.FormYiquxiaoV_I;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;


/**
 * Created by Administrator on 2018/1/13.
 */

public class Yiquxiao extends Fragment implements FormYiquxiaoV_I {
    private RecyclerView recyclerView;
    private int page = 1;
    private SmartRefreshLayout refreshLayout;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1) {
                String a = (String) msg.obj;
                yiquxiaoAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "订单已取消" + a, Toast.LENGTH_SHORT).show();
            }
        }
    };
    private Yiquxiao_P yiquxiao_p;
    private YiquxiaoAdapter yiquxiaoAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.yiquxiao_layout, null);

        recyclerView = view.findViewById(R.id.recyclerview);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //首先获取数据  MVP
        yiquxiao_p = new Yiquxiao_P(this);
        yiquxiao_p.getdata(My_api.Over_formApi, page);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //刷新
                yiquxiao_p.getdata(My_api.Over_formApi, 1);
                refreshlayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                //加载
                yiquxiao_p.getdata(My_api.Over_formApi, page);
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
                Yiquxiao_bean yiquxiao_bean = gson.fromJson(s, Yiquxiao_bean.class);
                List<Yiquxiao_bean.DataBean> data = yiquxiao_bean.getData();
                //设置适配器  和  布局
                yiquxiaoAdapter = new YiquxiaoAdapter(getActivity(), data, handler);
                recyclerView.setAdapter(yiquxiaoAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        });
    }
}
