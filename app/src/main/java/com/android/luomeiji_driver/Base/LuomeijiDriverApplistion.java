package com.android.luomeiji_driver.Base;

import android.app.Application;
import android.content.Context;

import com.android.luomeiji_driver.Tools.MyToast;
import com.android.luomeiji_driver.Tools.UiDensity;
import com.vondear.rxtool.RxTool;


public class LuomeijiDriverApplistion extends Application {

    public static LuomeijiDriverApplistion app;

    public static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        UiDensity.setDensity(this);
        MyToast.init(this);
        RxTool.init(this);
        appContext = getApplicationContext();
        app = this;
    }

    public static LuomeijiDriverApplistion getApp() {
        return app;
    }

}
