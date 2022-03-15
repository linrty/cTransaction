package com.linrty.ctransaction;

import static com.blankj.utilcode.util.BarUtils.transparentStatusBar;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseIM;

/**
  * @ClassName:      CTransactionApp
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/13
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/13
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class CTransactionApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //EaseIM初始化
        if(EaseIM.getInstance().init(context, null)){
            //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
            EMClient.getInstance().setDebugMode(true);
            //EaseIM初始化成功之后再去调用注册消息监听的代码 ...
        }
    }
}
