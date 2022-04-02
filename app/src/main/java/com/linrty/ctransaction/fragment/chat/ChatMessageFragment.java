package com.linrty.ctransaction.fragment.chat;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.LogUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.constants.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.manager.EasePreferenceManager;
import com.hyphenate.easeui.model.EaseEvent;
import com.hyphenate.easeui.modules.chat.EaseChatFragment;
import com.hyphenate.easeui.modules.chat.interfaces.IChatExtendMenu;
import com.hyphenate.easeui.modules.chat.interfaces.OnRecallMessageResultListener;
import com.hyphenate.easeui.modules.menu.EasePopupWindowHelper;
import com.hyphenate.easeui.modules.menu.MenuItemBean;
import com.hyphenate.util.EMFileHelper;
import com.hyphenate.util.EMLog;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.common.livedatas.LiveDataBus;
import com.linrty.ctransaction.common.viewmodel.MessageViewModel;
import com.linrty.ctransaction.util.CodeUtil;

/**
  * @ClassName:      ChatMessageFragment
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/29
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/29
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class ChatMessageFragment extends EaseChatFragment  implements OnRecallMessageResultListener {
     private static final String TAG = ChatFragment.class.getSimpleName();
     private static final int REQUEST_CODE_SELECT_USER_CARD = 20;
     private MessageViewModel viewModel;
     protected ClipboardManager clipboard;

     private static final int REQUEST_CODE_SELECT_AT_USER = 15;
     //private static final String[] calls = {DemoApplication.getInstance().getApplicationContext().getString(R.string.video_call), DemoApplication.getInstance().getApplicationContext().getString(R.string.voice_call)};
     private OnFragmentInfoListener infoListener;
     private Dialog dialog;

     @Override
     public void initView() {
         super.initView();
         clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
         viewModel = new ViewModelProvider(this).get(MessageViewModel.class);

         //获取到聊天列表控件
         //EaseChatMessageListLayout messageListLayout = chatLayout.getChatMessageListLayout();
         //设置聊天列表背景
         //messageListLayout.setBackground(new ColorDrawable(Color.parseColor("#DA5A4D")));
         //设置默认头像
         //messageListLayout.setAvatarDefaultSrc(ContextCompat.getDrawable(mContext, R.drawable.ease_default_avatar));
         //设置头像形状
         //messageListLayout.setAvatarShapeType(1);
         //设置文本字体大小
         //messageListLayout.setItemTextSize((int) EaseCommonUtils.sp2px(mContext, 18));
         //设置文本字体颜色
         //messageListLayout.setItemTextColor(ContextCompat.getColor(mContext, R.color.red));
         //设置时间线的背景
         //messageListLayout.setTimeBackground(ContextCompat.getDrawable(mContext, R.color.gray_normal));
         //设置时间线的文本大小
         //messageListLayout.setTimeTextSize((int) EaseCommonUtils.sp2px(mContext, 18));
         //设置时间线的文本颜色
         //messageListLayout.setTimeTextColor(ContextCompat.getColor(mContext, R.color.black));
         //设置聊天列表样式：两侧及均位于左侧
         //messageListLayout.setItemShowType(EaseChatMessageListLayout.ShowType.LEFT);

         //获取到菜单输入父控件
         //EaseChatInputMenu chatInputMenu = chatLayout.getChatInputMenu();
         //获取到菜单输入控件
         //IChatPrimaryMenu primaryMenu = chatInputMenu.getPrimaryMenu();
         //if(primaryMenu != null) {
         //设置菜单样式为不可用语音模式
         //    primaryMenu.setMenuShowType(EaseInputMenuStyle.ONLY_TEXT);
         //}

     }

     private void resetChatExtendMenu() {
         IChatExtendMenu chatExtendMenu = chatLayout.getChatInputMenu().getChatExtendMenu();
         chatExtendMenu.clear();
         chatExtendMenu.registerMenuItem(R.string.attach_picture, R.drawable.ease_chat_image_selector, R.id.extend_item_picture);
         chatExtendMenu.registerMenuItem(R.string.attach_take_pic, R.drawable.ease_chat_takepic_selector, R.id.extend_item_take_picture);
         chatExtendMenu.registerMenuItem(R.string.attach_file, R.drawable.em_chat_file_selector, R.id.extend_item_file);

     }

     @Override
     public void initListener() {
         super.initListener();
         chatLayout.setOnRecallMessageResultListener(this);
     }

     @Override
     public void initData() {
         super.initData();
         resetChatExtendMenu();

         chatLayout.getChatInputMenu().getPrimaryMenu().getEditText().setText(getUnSendMsg());


     }


     @Override
     public void onUserAvatarLongClick(String username) {

     }


     @Override
     public boolean onBubbleClick(EMMessage message) {
         return false;
     }

     @Override
     public void onChatExtendMenuItemClick(View view, int itemId) {
         super.onChatExtendMenuItemClick(view, itemId);
     }

     @Override
     public void onChatError(int code, String errorMsg) {
         if(infoListener != null) {
             infoListener.onChatError(code, errorMsg);
         }
     }

     @Override
     public void onOtherTyping(String action) {
         if(infoListener != null) {
             infoListener.onOtherTyping(action);
         }
     }



     @Override
     public void onStop() {
         super.onStop();
         //保存未发送的文本消息内容
         if(mContext != null && mContext.isFinishing()) {
             if(chatLayout.getChatInputMenu() != null) {
                 saveUnSendMsg(chatLayout.getInputContent());
                 LiveDataBus.get().with(CodeUtil.MESSAGE_NOT_SEND).postValue(true);
             }
         }
     }

     //================================== for video and voice start ====================================

     /**
      * 保存未发送的文本消息内容
      * @param content
      */
     private void saveUnSendMsg(String content) {
         EasePreferenceManager.getInstance().saveUnSendMsgInfo(conversationId, content);
     }

     private String getUnSendMsg() {
         return EasePreferenceManager.getInstance().getUnSendMsgInfo(conversationId);
     }

     @Override
     public void onPreMenu(EasePopupWindowHelper helper, EMMessage message, View v) {
         //默认两分钟后，即不可撤回
         if(System.currentTimeMillis() - message.getMsgTime() > 2 * 60 * 1000) {
             helper.findItemVisible(R.id.action_chat_recall, false);
         }
         EMMessage.Type type = message.getType();
     }

     @Override
     public boolean onMenuItemClick(MenuItemBean item, EMMessage message) {
         switch (item.getItemId()) {
             case R.id.action_chat_recall :
                 showProgressBar();
                 chatLayout.recallMessage(message);
                 return true;
         }
         return false;
     }

     private void showProgressBar() {
         View view = View.inflate(mContext, R.layout.layout_progress_recall, null);
         dialog = new Dialog(mContext,R.style.dialog_recall);
         ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
         dialog.setContentView(view, layoutParams);
         dialog.setCancelable(false);
         dialog.setCanceledOnTouchOutside(true);
         dialog.show();
     }


     public void setOnFragmentInfoListener(OnFragmentInfoListener listener) {
         this.infoListener = listener;
     }

     @Override
     public void recallSuccess(EMMessage message) {
         if(dialog != null && dialog.isShowing()) {
             dialog.dismiss();
         }
     }

     @Override
     public void recallFail(int code, String errorMsg) {
         if(dialog != null && dialog.isShowing()) {
             dialog.dismiss();
         }
     }

     public interface OnFragmentInfoListener {
         void onChatError(int code, String errorMsg);

         void onOtherTyping(String action);
     }

    @Override
    public void onBackPress() {
        LogUtils.i("chatFragment: onBackPress");
        super.onBackPress();
    }
}
