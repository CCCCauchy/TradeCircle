package com.xiaochui.tradecircle;

import android.app.Application;
import android.content.Context;

/**
 * @author cauchy
 * @date 2018/1/4 13:14
 * @since 1.0.0
 */

public class TradeCircleApplication extends Application {
    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getInstance() {
        return instance;
    }
}
