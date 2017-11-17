package com.wangkeke.weexandroidhelp;

import android.app.Application;

import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import com.wangkeke.weexandroidhelp.module.MyToastModule;
import com.wangkeke.weexandroidhelp.module.WXEventModule;

/**
 * Created by wangkeke on 2017/5/23.
 */

public class WXApplication extends Application {

    private static WXApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        InitConfig config = new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        WXSDKEngine.initialize(this,config);

        try {
            WXSDKEngine.registerModule("myToastModule", MyToastModule.class);
            WXSDKEngine.registerModule("WXEventModule", WXEventModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }

    public static WXApplication getInstance(){
        return application;
    }
}
