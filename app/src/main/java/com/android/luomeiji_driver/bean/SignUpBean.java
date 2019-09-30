package com.android.luomeiji_driver.bean;

public class SignUpBean {

    /**
     * code : 1
     * msg : 请求成功
     * data : {"driverUserId":"1"}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * driverUserId : 1
         */

        private String driverUserId;

        public String getDriverUserId() {
            return driverUserId;
        }

        public void setDriverUserId(String driverUserId) {
            this.driverUserId = driverUserId;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "driverUserId='" + driverUserId + '\'' +
                    '}';
        }
    }

}
