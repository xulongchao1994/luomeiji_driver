package com.android.luomeiji_driver.bean;

import java.util.List;

public class TaxiOrdersBean {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"driverOrderId":"6","userId":"15","driverUserId":"0","numbering":"370923328426487808","departure":"我的位置","departureLot":"82.983175","departureLat":"46.750719","destination":"经纬评估测绘","destinationLot":"82.983344","destinationLat":"46.750153","orderState":"0","money":0,"orderTime":"2019-09-16T02:37:59.000+0000","cancelTheReason":""},{"driverOrderId":"7","userId":"15","driverUserId":"0","numbering":"370930803871592448","departure":"我的位置","departureLot":"82.983175","departureLat":"46.750719","destination":"经纬评估测绘","destinationLot":"82.983344","destinationLat":"46.750153","orderState":"0","money":0,"orderTime":"2019-09-16T03:07:41.000+0000","cancelTheReason":""},{"driverOrderId":"8","userId":"15","driverUserId":"0","numbering":"370930972734271488","departure":"我的位置","departureLot":"82.983175","departureLat":"46.750719","destination":"经纬评估测绘","destinationLot":"82.983344","destinationLat":"46.750153","orderState":"0","money":0,"orderTime":"2019-09-16T03:08:21.000+0000","cancelTheReason":""},{"driverOrderId":"9","userId":"15","driverUserId":"0","numbering":"370931027427995648","departure":"我的位置","departureLot":"82.983175","departureLat":"46.750719","destination":"经纬评估测绘","destinationLot":"82.983344","destinationLat":"46.750153","orderState":"0","money":0,"orderTime":"2019-09-16T03:08:34.000+0000","cancelTheReason":""},{"driverOrderId":"10","userId":"15","driverUserId":"0","numbering":"370931306177245184","departure":"我的位置","departureLot":"82.983175","departureLat":"46.750719","destination":"经纬评估测绘","destinationLot":"82.983344","destinationLat":"46.750153","orderState":"0","money":0,"orderTime":"2019-09-16T03:09:41.000+0000","cancelTheReason":""},{"driverOrderId":"11","userId":"15","driverUserId":"0","numbering":"370931352742408192","departure":"我的位置","departureLot":"82.983175","departureLat":"46.750719","destination":"经纬评估测绘","destinationLot":"82.983344","destinationLat":"46.750153","orderState":"0","money":0,"orderTime":"2019-09-16T03:09:52.000+0000","cancelTheReason":""},{"driverOrderId":"12","userId":"15","driverUserId":"0","numbering":"370932285920522240","departure":"我的位置","departureLot":"82.983175","departureLat":"46.750719","destination":"经纬评估测绘","destinationLot":"82.983344","destinationLat":"46.750153","orderState":"0","money":0,"orderTime":"2019-09-16T03:13:34.000+0000","cancelTheReason":""},{"driverOrderId":"13","userId":"15","driverUserId":"0","numbering":"370933861192052736","departure":"我的位置","departureLot":"82.983175","departureLat":"46.750719","destination":"经纬评估测绘","destinationLot":"82.983344","destinationLat":"46.750153","orderState":"0","money":0,"orderTime":"2019-09-16T03:19:50.000+0000","cancelTheReason":""},{"driverOrderId":"14","userId":"15","driverUserId":"0","numbering":"370939785814224896","departure":"我的位置","departureLot":"82.983175","departureLat":"46.750719","destination":"力帕面包糕点房","destinationLot":"82.982578","destinationLat":"46.750658","orderState":"0","money":0,"orderTime":"2019-09-16T03:43:22.000+0000","cancelTheReason":""},{"driverOrderId":"15","userId":"15","driverUserId":"0","numbering":"370974779269984256","departure":"我的位置","departureLot":"82.983175","departureLat":"46.750719","destination":"中国电信(团结路社区)","destinationLot":"82.982992","destinationLat":"46.750864","orderState":"0","money":0,"orderTime":"2019-09-16T06:02:25.000+0000","cancelTheReason":""}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * driverOrderId : 6
         * userId : 15
         * driverUserId : 0
         * numbering : 370923328426487808
         * departure : 我的位置
         * departureLot : 82.983175
         * departureLat : 46.750719
         * destination : 经纬评估测绘
         * destinationLot : 82.983344
         * destinationLat : 46.750153
         * orderState : 0
         * money : 0
         * orderTime : 2019-09-16T02:37:59.000+0000
         * cancelTheReason :
         */

        private String driverOrderId;
        private String userId;
        private String driverUserId;
        private String numbering;
        private String departure;
        private String departureLot;
        private String departureLat;
        private String destination;
        private String destinationLot;
        private String destinationLat;
        private String orderState;
        private int money;
        private String orderTime;
        private String cancelTheReason;

        public String getDriverOrderId() {
            return driverOrderId;
        }

        public void setDriverOrderId(String driverOrderId) {
            this.driverOrderId = driverOrderId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDriverUserId() {
            return driverUserId;
        }

        public void setDriverUserId(String driverUserId) {
            this.driverUserId = driverUserId;
        }

        public String getNumbering() {
            return numbering;
        }

        public void setNumbering(String numbering) {
            this.numbering = numbering;
        }

        public String getDeparture() {
            return departure;
        }

        public void setDeparture(String departure) {
            this.departure = departure;
        }

        public String getDepartureLot() {
            return departureLot;
        }

        public void setDepartureLot(String departureLot) {
            this.departureLot = departureLot;
        }

        public String getDepartureLat() {
            return departureLat;
        }

        public void setDepartureLat(String departureLat) {
            this.departureLat = departureLat;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getDestinationLot() {
            return destinationLot;
        }

        public void setDestinationLot(String destinationLot) {
            this.destinationLot = destinationLot;
        }

        public String getDestinationLat() {
            return destinationLat;
        }

        public void setDestinationLat(String destinationLat) {
            this.destinationLat = destinationLat;
        }

        public String getOrderState() {
            return orderState;
        }

        public void setOrderState(String orderState) {
            this.orderState = orderState;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getCancelTheReason() {
            return cancelTheReason;
        }

        public void setCancelTheReason(String cancelTheReason) {
            this.cancelTheReason = cancelTheReason;
        }
    }
}
