package com.wangkeke.weexandroidhelp.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.wangkeke.weexandroidhelp.WeexActivity;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;


/**
 * Created by chenlei on 16/12/25.
 */
public class WXEventModule extends WXModule {

    @JSMethod
    public void getStatusBarHeight(JSCallback callback) {
        int result = 0;
        int resourceId = mWXSDKInstance.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mWXSDKInstance.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("stHeight", result);
        callback.invokeAndKeepAlive(map);
    }


    @JSMethod
    public void gotoedit(String url) {
        if (mWXSDKInstance.getContext() instanceof Activity) {
            Intent intent = new Intent(mWXSDKInstance.getContext(), WeexActivity.class);
            intent.putExtra("url", url);
            mWXSDKInstance.getContext().startActivity(intent);
        }
    }

    @JSMethod
    public void gotJump(String url,String param) {
        if (mWXSDKInstance.getContext() instanceof Activity) {
            Intent intent = new Intent(mWXSDKInstance.getContext(), WeexActivity.class);
            intent.putExtra("url", url);
            intent.putExtra("param", param);
            mWXSDKInstance.getContext().startActivity(intent);
        }
    }

    @Override
    public void onActivityCreate() {
        super.onActivityCreate();
        Log.e("weex","onActivityCreate");
    }

    @JSMethod
    public void backtohome(String msg) {
        if (mWXSDKInstance.getContext() instanceof Activity) {
            ((Activity) mWXSDKInstance.getContext()).finish();
        }
    }

    @JSMethod
    public void comment(String obj, String content) {
        if (mWXSDKInstance.getContext() instanceof Activity) {

            /*Intent intent = new Intent(mWXSDKInstance.getContext(), CommentActivity.class);
            intent.putExtra("objectid",obj);
            intent.putExtra("content",content);
            mWXSDKInstance.getContext().startActivity(intent);*/
        }
    }

    @JSMethod
    public void share(String msg) {

        if (mWXSDKInstance.getContext() instanceof Activity) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, msg);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "好友分享");
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mWXSDKInstance.getContext().startActivity(Intent.createChooser(shareIntent, "分享到"));
        }
    }


    @JSMethod
    public void sp(String name, String pass) {
        if (mWXSDKInstance.getContext() instanceof Activity) {
            SharedPreferences sp = mWXSDKInstance.getContext().getSharedPreferences("userinfo", Context.MODE_WORLD_WRITEABLE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("username", name);
            edit.putString("password", pass);
            edit.commit();
           /* Intent intent = new Intent(mWXSDKInstance.getContext(), HomeActivity.class);
            mWXSDKInstance.getContext().startActivity(intent);*/
        }
    }

    @JSMethod
    public void showsp() {

        if (mWXSDKInstance.getContext() instanceof Activity) {
            SharedPreferences sp = mWXSDKInstance.getContext().getSharedPreferences("userinfo", Context.MODE_WORLD_WRITEABLE);
            String a = sp.getString("username", "default");
            Toast.makeText(mWXSDKInstance.getContext(), a, LENGTH_SHORT).show();
        }

    }

    @JSMethod
    public void quit() {
        if (mWXSDKInstance.getContext() instanceof Activity) {
            SharedPreferences sp = mWXSDKInstance.getContext().getSharedPreferences("userinfo", Context.MODE_WORLD_WRITEABLE);
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("username", "");
            edit.putString("password", "");
            edit.commit();
            /*Intent intent = new Intent(mWXSDKInstance.getContext(), LoginActivity.class);
            mWXSDKInstance.getContext().startActivity(intent);
            ((Activity) mWXSDKInstance.getContext()).finish();*/
        }
    }

    @JSMethod
    public void uploadimage() {
        int REQUESTCODE_PICK = 0;
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            Log.d("chenlei api level", "Your api is lower than 19");
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
        } else {
            Log.d("chenlei api level", "Your api is higher than 19");
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        ((Activity) mWXSDKInstance.getContext()).startActivityForResult(intent, REQUESTCODE_PICK);
    }

}
