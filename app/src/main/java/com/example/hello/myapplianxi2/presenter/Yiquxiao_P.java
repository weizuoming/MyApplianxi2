package com.example.hello.myapplianxi2.presenter;


import com.example.hello.myapplianxi2.model.Yiquxiao_M;
import com.example.hello.myapplianxi2.presenter.interF.YiquxiaoP_I;
import com.example.hello.myapplianxi2.view.fragment.interF.FormYiquxiaoV_I;

/**
 * Created by Administrator on 2018/1/14.
 */

public class Yiquxiao_P implements YiquxiaoP_I {

    private Yiquxiao_M yiquxiao_m;
    private FormYiquxiaoV_I formYiquxiaoV_i;

    public Yiquxiao_P(FormYiquxiaoV_I formYiquxiaoV_i) {

        this.formYiquxiaoV_i = formYiquxiaoV_i;
        yiquxiao_m = new Yiquxiao_M(this);
    }

    public void getdata(String over_formApi, int page) {
        yiquxiao_m.getdata(over_formApi, page);
    }

    @Override
    public void success(String s) {
        formYiquxiaoV_i.success(s);
    }
}
