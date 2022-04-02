package com.linrty.ctransaction;

import static com.blankj.utilcode.util.BarUtils.transparentStatusBar;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.drake.brv.utils.BRV;
import com.drake.net.NetConfig;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseIM;
import com.linrty.ctransaction.plugin.NetUtil;
import com.linrty.ctransaction.util.CodeUtil;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;

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
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    public static Context context;

    private LogUtils.Config logConfig;





    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        initIM();

        initBRV();

        initLog();

        initNet();
    }

    private void initIM(){
        //EaseIM初始化
        if(EaseIM.getInstance().init(context, null)){
            //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
            EMClient.getInstance().setDebugMode(true);
            //EaseIM初始化成功之后再去调用注册消息监听的代码 ...
        }
    }

    private void initBRV(){
        // 设置BRV的数据绑定modelID
        BRV.INSTANCE.setModelId(BR.data);
        // 点击防抖,单位毫秒
        BRV.INSTANCE.setClickThrottle(1000);
    }

    private void initLog(){
        logConfig = LogUtils.getConfig();
        // 配置全局的TAG,以及一些选项
        logConfig.setGlobalTag(CodeUtil.TAG_LOG)
                .setBorderSwitch(true)
                .setLogSwitch(true)
                .setLogHeadSwitch(true)
                .setSingleTagSwitch(true);
    }

    private void initNet(){
        // 初始化网络相关参数，具体参数在NetUtil中
        NetUtil.INSTANCE.netConfig();
    }
}
