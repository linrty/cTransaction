package com.linrty.ctransaction.plugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMChatRoomManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMContactManager;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.chat.EMPushManager;
import com.hyphenate.chat.EMTranslateParams;
import com.hyphenate.easeui.EaseIM;
import com.hyphenate.easeui.delegate.EaseCustomAdapterDelegate;
import com.hyphenate.easeui.delegate.EaseExpressionAdapterDelegate;
import com.hyphenate.easeui.delegate.EaseFileAdapterDelegate;
import com.hyphenate.easeui.delegate.EaseImageAdapterDelegate;
import com.hyphenate.easeui.delegate.EaseLocationAdapterDelegate;
import com.hyphenate.easeui.delegate.EaseTextAdapterDelegate;
import com.hyphenate.easeui.delegate.EaseVideoAdapterDelegate;
import com.hyphenate.easeui.delegate.EaseVoiceAdapterDelegate;
import com.hyphenate.easeui.domain.EaseAvatarOptions;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.manager.EaseMessageTypeSetManager;
import com.hyphenate.easeui.model.EaseNotifier;
import com.hyphenate.push.EMPushConfig;
import com.hyphenate.push.EMPushHelper;
import com.hyphenate.push.EMPushType;
import com.hyphenate.push.PushListener;

import java.util.List;
import java.util.Map;

/**
  * @ClassName:      AppHelper
  * @Description:    作为hyphenate-sdk的入口控制类，获取sdk下的基础类均通过此类
  * @Author:         Linrty
  * @CreateDate:     2022/3/29
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/29
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class AppHelper {
     private static final String TAG = AppHelper.class.getSimpleName();

     public boolean isSDKInit;//SDK是否初始化
     private static AppHelper mInstance;
     private Map<String, EaseUser> contactList;

     private Context mianContext;

     private String tokenUrl = "http://a1.easemob.com/token/rtcToken/v1";
     private String uIdUrl = "http://a1.easemob.com/channel/mapper";


     private Thread fetchUserTread;


     private AppHelper() {}

     public static AppHelper getInstance() {
         if(mInstance == null) {
             synchronized (AppHelper.class) {
                 if(mInstance == null) {
                     mInstance = new AppHelper();
                 }
             }
         }
         return mInstance;
     }

     public void init(Context context) {
         //初始化IM SDK
         if(initSDK(context)) {
             // debug mode, you'd better set it to false, if you want release your App officially.
             EMClient.getInstance().setDebugMode(true);
             //注册call Receiver
             //initReceiver(context);
             //注册对话类型
             registerConversationType();

             //启动获取用户信息线程
         }

     }



     /**
      * 初始化SDK
      * @param context
      * @return
      */
     private boolean initSDK(Context context) {
         // 根据项目需求对SDK进行配置
         EMOptions options = initChatOptions(context);
         //配置自定义的rest server和im server
//        options.setRestServer("a1-hsb.easemob.com");
//        options.setIMServer("106.75.100.247");
//        options.setImPort(6717);

//        options.setRestServer("a41.easemob.com");
//        options.setIMServer("msync-im-41-tls-test.easemob.com");
//        options.setImPort(6717);
         // 初始化SDK
         isSDKInit = EaseIM.getInstance().init(context, options);
         //设置删除用户属性数据超时时间
         // demoModel.setUserInfoTimeOut(30 * 60 * 1000);
         //更新过期用户属性列表
         mianContext = context;
         return isSDKInit();
     }


     /**
      *注册对话类型
      */
     private void registerConversationType() {
         EaseMessageTypeSetManager.getInstance()
                 //自定义表情
                 .addMessageType(EaseExpressionAdapterDelegate.class)
                 //文件
                 .addMessageType(EaseFileAdapterDelegate.class)
                 //图片
                 .addMessageType(EaseImageAdapterDelegate.class)
                 //定位
                 .addMessageType(EaseLocationAdapterDelegate.class)
                 //视频
                 .addMessageType(EaseVideoAdapterDelegate.class)
                 //声音
                 .addMessageType(EaseVoiceAdapterDelegate.class)
                 //自定义消息
                 .addMessageType(EaseCustomAdapterDelegate.class)
                 //文本
                 .setDefaultMessageType(EaseTextAdapterDelegate.class);
     }

     /**
      * 判断是否之前登录过
      * @return
      */
     public boolean isLoggedIn() {
         return getEMClient().isLoggedInBefore();
     }

     /**
      * 获取IM SDK的入口类
      * @return
      */
     public EMClient getEMClient() {
         return EMClient.getInstance();
     }

     /**
      * 获取contact manager
      * @return
      */
     public EMContactManager getContactManager() {
         return getEMClient().contactManager();
     }

     /**
      * 获取group manager
      * @return
      */
     public EMGroupManager getGroupManager() {
         return getEMClient().groupManager();
     }

     /**
      * 获取chatroom manager
      * @return
      */
     public EMChatRoomManager getChatroomManager() {
         return getEMClient().chatroomManager();
     }


     /**
      * get EMChatManager
      * @return
      */
     public EMChatManager getChatManager() {
         return getEMClient().chatManager();
     }

     /**
      * get push manager
      * @return
      */
     public EMPushManager getPushManager() {
         return getEMClient().pushManager();
     }

     /**
      * get conversation
      * @param username
      * @param type
      * @param createIfNotExists
      * @return
      */
     public EMConversation getConversation(String username, EMConversation.EMConversationType type, boolean createIfNotExists) {
         return getChatManager().getConversation(username, type, createIfNotExists);
     }

     public String getCurrentUser() {
         return getEMClient().getCurrentUser();
     }



     //Translation Manager 初始化
     public void initTranslationManager() {
         EMTranslateParams params = new EMTranslateParams("46c34219512d4f09ae6f8e04c083b7a3", "https://api.cognitive.microsofttranslator.com", 500);

         EMClient.getInstance().translationManager().init(params);
     }

     /**
      * 统一配置头像
      * @return
      */
     private EaseAvatarOptions getAvatarOptions() {
         EaseAvatarOptions avatarOptions = new EaseAvatarOptions();
         avatarOptions.setAvatarShape(1);
         return avatarOptions;
     }



     /**
      * 根据自己的需要进行配置
      * @param context
      * @return
      */
     private EMOptions initChatOptions(Context context){
         LogUtils.d(TAG, "init HuanXin Options");

         EMOptions options = new EMOptions();
         // 设置是否自动接受加好友邀请,默认是true
         options.setAcceptInvitationAlways(false);
         // 设置是否需要接受方已读确认
         options.setRequireAck(true);
         // 设置是否需要接受方送达确认,默认false
         options.setRequireDeliveryAck(false);

         /**
          * NOTE:你需要设置自己申请的账号来使用三方推送功能，详见集成文档
          */
         EMPushConfig.Builder builder = new EMPushConfig.Builder(context);

         builder.enableVivoPush() // 需要在AndroidManifest.xml中配置appId和appKey
                 .enableMeiZuPush("134952", "f00e7e8499a549e09731a60a4da399e3")
                 .enableMiPush("2882303761517426801", "5381742660801")
                 .enableOppoPush("0bb597c5e9234f3ab9f821adbeceecdb",
                         "cd93056d03e1418eaa6c3faf10fd7537")
                 .enableHWPush() // 需要在AndroidManifest.xml中配置appId
                 .enableFCM("921300338324");
         options.setPushConfig(builder.build());

         return options;
     }


     /**
      * logout
      *
      * @param unbindDeviceToken
      *            whether you need unbind your device token
      * @param callback
      *            callback
      */
     public void logout(boolean unbindDeviceToken, final EMCallBack callback) {
         LogUtils.d(TAG, "logout: " + unbindDeviceToken);
         EMClient.getInstance().logout(unbindDeviceToken, new EMCallBack() {

             @Override
             public void onSuccess() {
                 logoutSuccess();
                 //reset();
                 if (callback != null) {
                     callback.onSuccess();
                 }

             }

             @Override
             public void onProgress(int progress, String status) {
                 if (callback != null) {
                     callback.onProgress(progress, status);
                 }
             }

             @Override
             public void onError(int code, String error) {
                 LogUtils.d(TAG, "logout: onSuccess");
                 //reset();
                 if (callback != null) {
                     callback.onError(code, error);
                 }
             }
         });
     }



     /**
      * 退出登录后，需要处理的业务逻辑
      */
     public void logoutSuccess() {
         LogUtils.d(TAG, "logout: onSuccess");
         EMClient.getInstance().translationManager().logout();
     }

     public EaseAvatarOptions getEaseAvatarOptions() {
         return EaseIM.getInstance().getAvatarOptions();
     }


     /**
      * get instance of EaseNotifier
      * @return
      */
     public EaseNotifier getNotifier(){
         return EaseIM.getInstance().getNotifier();
     }


     /**
      * 设置SDK是否初始化
      * @param init
      */
     public void setSDKInit(boolean init) {
         isSDKInit = init;
     }

     public boolean isSDKInit() {
         return isSDKInit;
     }

     /**
      * Determine if it is from the current user account of another device
      * @param username
      * @return
      */
     public boolean isCurrentUserFromOtherDevice(String username) {
         if(TextUtils.isEmpty(username)) {
             return false;
         }
         if(username.contains("/") && username.contains(EMClient.getInstance().getCurrentUser())) {
             return true;
         }
         return false;
     }



     /**
      * data sync listener
      */
     public interface DataSyncListener {
         /**
          * sync complete
          * @param success true：data sync successful，false: failed to sync data
          */
         void onSyncComplete(boolean success);
     }
}
