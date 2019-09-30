package com.android.luomeiji_driver.bean;

public class LoginBean {

    /**
     * code : 1
     * message : 请求成功
     * data : {"driverReviewState":"3","whyNotPass":"","driverUserId":"3"}
     */

    private String code;
    private String message;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * driverReviewState : 3
         * whyNotPass :
         * driverUserId : 3
         */

        private String driverReviewState;//认证状态 0：待审核，1：通过，2：未通过，3：未认证
        private String whyNotPass;//认证不通过的理由
        private String driverUserId;//司机ID

        public String getDriverReviewState() {
            return driverReviewState;
        }

        public void setDriverReviewState(String driverReviewState) {
            this.driverReviewState = driverReviewState;
        }

        public String getWhyNotPass() {
            return whyNotPass;
        }

        public void setWhyNotPass(String whyNotPass) {
            this.whyNotPass = whyNotPass;
        }

        public String getDriverUserId() {
            return driverUserId;
        }

        public void setDriverUserId(String driverUserId) {
            this.driverUserId = driverUserId;
        }
    }
}
