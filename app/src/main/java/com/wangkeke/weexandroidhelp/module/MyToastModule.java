package com.wangkeke.weexandroidhelp.module;

import android.widget.Toast;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangkeke on 2017/6/9.
 */

public class MyToastModule extends WXModule {

    private static JSCallback callbackData;

    @JSMethod
    public void printLog(String msg) {
        Toast.makeText(mWXSDKInstance.getContext(), "" + msg, Toast.LENGTH_SHORT).show();

    }

    /*****jscallback*****/
    @JSMethod
    public void scanDataCallNative(String str, JSCallback callback){
        callbackData = callback;
//        Toast.makeText(mWXSDKInstance.getContext(), str, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(mWXSDKInstance.getContext(), ZbarActivity.class);
//        mWXSDKInstance.getContext().startActivity(intent);
    }


    public static void setCallBackData(String data){
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        callbackData.invokeAndKeepAlive(map);
    }
}
