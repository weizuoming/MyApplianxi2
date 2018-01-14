package com.example.hello.myapplianxi2.model;

import com.example.hello.myapplianxi2.presenter.Yiquxiao_P;
import com.example.hello.myapplianxi2.presenter.interF.YiquxiaoP_I;
import com.example.hello.myapplianxi2.util.OkHttp3Util;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/14.
 */

public class Yiquxiao_M {

    private YiquxiaoP_I yiquxiaoP_i;

    public Yiquxiao_M(Yiquxiao_P yiquxiaoP_i) {
        this.yiquxiaoP_i = yiquxiaoP_i;
    }

    public void getdata(String over_formApi, int page) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", "4123");
        map.put("status", "2");
        map.put("page", page + "");
        OkHttp3Util.doPost(over_formApi, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    yiquxiaoP_i.success(string);
                }
            }
        });
    }
}
