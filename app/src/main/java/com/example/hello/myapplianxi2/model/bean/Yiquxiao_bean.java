package com.example.hello.myapplianxi2.model.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/14.
 */

public class Yiquxiao_bean {

    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2017-12-20T21:09:24","orderid":4436,"price":829.73,"status":2,"title":"订单测试标题4123","uid":4123},{"createtime":"2017-12-20T21:14:02","orderid":4454,"price":829.73,"status":2,"title":"订单测试标题4123","uid":4123}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2017-12-20T21:09:24
         * orderid : 4436
         * price : 829.73
         * status : 2
         * title : 订单测试标题4123
         * uid : 4123
         */

        private String createtime;
        private int orderid;
        private double price;
        private int status;
        private String title;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
