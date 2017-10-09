package com.example.chenhuamou.meicai.gloable;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by chenhuamou on 2017/9/30.
 */

public class MCApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 图片加载框架
        Fresco.initialize(this);
    }
}
