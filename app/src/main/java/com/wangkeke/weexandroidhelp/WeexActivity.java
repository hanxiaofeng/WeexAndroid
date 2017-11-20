package com.wangkeke.weexandroidhelp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenlei on 17/1/3.
 */
public class WeexActivity extends Activity implements IWXRenderListener {

    private static final String TAG = "WebActivity";
    private static WXSDKInstance mInstance;
    private String URL;
    private String Path;
    private static final int REQUESTCODE_PICK = 0;
    private String param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weex);


        URL = getIntent().getStringExtra("url");
        param = getIntent().getStringExtra("param");

        mInstance = new WXSDKInstance(this);
        mInstance.registerRenderListener(this);

        Map<String, Object> options = new HashMap<>();
//        options.put(WXSDKInstance.BUNDLE_URL, url);    // 传递bundleUrl
        options.put("data", param);  // 传递自定义参数 aa

        mInstance.render("WeexQuickStart", WXFileUtils.loadAsset(URL, this), options, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);


//        mInstance.renderByUrl("weex", URL, options, null, WXRenderStrategy.APPEND_ASYNC);
    }

    public void show(){
        Toast.makeText(getApplicationContext(),"show....", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(WXSDKInstance wxsdkInstance, View view) {
        Log.v(TAG, "onViewCreated");
        setContentView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
        Log.v(TAG, "onRenderSuccess");
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance wxsdkInstance, int i, int i1) {
        Log.v(TAG, "onRefreshSuccess");
    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        Log.v(TAG, "onException errCode:" + errCode + " msg:" + msg);
    }

}
