# WeexAndroid
weex android集成的例子

####1.如何创建项目

- 使用```weex init 项目名称```命令；
- 进入项目目录，执行```npm install```安装相关依赖库；
- 查看页面执行效果：使用```weex debug 文件名字```，然后使用[playground Apk](http://weex.apache.org/cn/playground.html)扫描浏览器生成的二维码即可看到运行效果；

####2.集成到android

1.添加必要的依赖
```
   compile 'com.taobao.android:weex_sdk:0.16.0'
   compile 'com.taobao.android:weex_inspector:0.10.0.5'
   compile 'com.alibaba:fastjson:1.1.46.android'
   compile 'com.squareup.picasso:picasso:2.5.2'
```
2.在application里进行初始化
```
   InitConfig config = new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
   WXSDKEngine.initialize(this,config);
```
3.加载js
```
   WXSDKInstance mInstance = new WXSDKInstance(this);
   //加载本地assets下的js
   mInstance.render("WeexQuickStart", WXFileUtils.loadAsset(URL, this), options, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
   //加载服务器js的url
   mInstance.renderByUrl("weex", URL, options, null, WXRenderStrategy.APPEND_ASYNC);
```

4.配置androidManifest.xml

<br>
配置activity：

```
   <intent-filter>
       <action android:name="android.intent.action.VIEW" />
       <category android:name="android.intent.category.DEFAULT" />
       <category android:name="com.taobao.android.intent.category.WEEX" />
       <data android:scheme="http" />
       <data android:scheme="https" />
       <data android:scheme="file" />
   </intent-filter>
```

如果是访问服务器url，最后别忘了添加网络权限：

```
<uses-permission android:name="android.permission.INTERNET"/>
```


####3.更多

[http://weex.apache.org/cn/](http://weex.apache.org/cn/)


