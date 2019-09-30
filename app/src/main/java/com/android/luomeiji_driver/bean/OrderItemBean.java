package com.android.luomeiji_driver.bean;

public class OrderItemBean {
    private String chufadi;
    private String mudidi;

    public OrderItemBean(String chufadi, String mudidi) {
        this.chufadi = chufadi;
        this.mudidi = mudidi;
    }

    public String getChufadi() {
        return chufadi;
    }

    public void setChufadi(String chufadi) {
        this.chufadi = chufadi;
    }

    public String getMudidi() {
        return mudidi;
    }

    public void setMudidi(String mudidi) {
        this.mudidi = mudidi;
    }
}
