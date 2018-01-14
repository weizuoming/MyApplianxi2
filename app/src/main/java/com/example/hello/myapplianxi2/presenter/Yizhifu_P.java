package com.example.hello.myapplianxi2.presenter;


import com.example.hello.myapplianxi2.model.Yizhifu_M;
import com.example.hello.myapplianxi2.presenter.interF.YizhifuP_I;
import com.example.hello.myapplianxi2.view.fragment.interF.FormYizhifuV_I;

/**
 * Created by Administrator on 2018/1/14.
 */

public class Yizhifu_P implements YizhifuP_I {

    private Yizhifu_M yizhifu_m;
    private FormYizhifuV_I formYizhifuV_i;

    public Yizhifu_P(FormYizhifuV_I formYizhifuV_i) {
        this.formYizhifuV_i = formYizhifuV_i;
        yizhifu_m = new Yizhifu_M(this);
    }

    public void getdata(String over_formApi, int page) {
        yizhifu_m.getdata(over_formApi, page);
    }

    @Override
    public void success(String s) {
        //给view
        formYizhifuV_i.success(s);

    }
}
