package com.example.hello.myapplianxi2.presenter;

import com.example.hello.myapplianxi2.model.Form_M;
import com.example.hello.myapplianxi2.presenter.interF.FormP_I;
import com.example.hello.myapplianxi2.view.fragment.interF.FormDaizhifuV_I;

/**
 * Created by Administrator on 2018/1/14.
 */

public class From_P implements FormP_I {

    private final Form_M form_m;
    private FormDaizhifuV_I formDaizhifuV_i;

    public From_P(FormDaizhifuV_I formDaizhifuV_i) {
        form_m = new Form_M(this);
        this.formDaizhifuV_i = formDaizhifuV_i;
    }

    public void getdata(String over_formApi, int page) {
        form_m.getdata(over_formApi, page);
    }

    @Override
    public void success(String s) {
        //回调
        formDaizhifuV_i.success(s);
    }

    //销毁  解决MVP内存泄漏问题
    public void destory() {
        if (formDaizhifuV_i != null) {
            formDaizhifuV_i = null;
        }
    }
}
