package com.linrty.ctransaction;

import static com.blankj.utilcode.util.BarUtils.transparentStatusBar;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

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
    }
}
