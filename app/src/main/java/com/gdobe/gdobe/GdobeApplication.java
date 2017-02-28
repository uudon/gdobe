package com.gdobe.gdobe;

import android.app.Application;

/**
 * Created by Administrator on 2017/2/22.
 */

public class GdobeApplication extends Application{

    private static GdobeApplication gdobeApplication;

    public static GdobeApplication getInstance() {
        return gdobeApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        gdobeApplication = this;
       // HttpUtils.getInstance().setContext(getApplicationContext());
    }
}
