package com.example.hello.myapplianxi2.model;

import com.example.hello.myapplianxi2.presenter.Yizhifu_P;
import com.example.hello.myapplianxi2.presenter.interF.YizhifuP_I;
import com.example.hello.myapplianxi2.util.OkHttp3Util;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/1/14.
 */

public class Yizhifu_M {

    private YizhifuP_I yizhifuP_i;

    public Yizhifu_M(Yizhifu_P yizhifuP_i) {
        this.yizhifuP_i = yizhifuP_i;
    }

    public void getdata(String over_formApi, int page) {

        HashMap<String, String> map = new HashMap<>();
        map.put("uid", "4123");
        map.put("status", "1");
        map.put("page", page + "");
        OkHttp3Util.doPost(over_formApi, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    //回调
                    yizhifuP_i.success(string);
                }
            }
        });
    }
}
