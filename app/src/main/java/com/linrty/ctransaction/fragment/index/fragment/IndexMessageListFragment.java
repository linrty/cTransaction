package com.linrty.ctransaction.fragment.index.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.load.engine.Resource;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.manager.EaseSystemMsgManager;
import com.hyphenate.easeui.model.EaseEvent;
import com.hyphenate.easeui.modules.conversation.EaseConversationListFragment;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.common.constent.CTransactionConstant;
import com.linrty.ctransaction.common.livedatas.LiveDataBus;
import com.linrty.ctransaction.common.viewmodel.MessageViewModel;
import com.linrty.ctransaction.fragment.chat.ChatFragment;
import com.linrty.ctransaction.fragment.index.IndexViewModel;

import java.util.List;

/**
  * @ClassName:      IndexMessageListFragment
  * @Description:    用于展示消息列表的Fragment
  * @Author:         Linrty
  * @CreateDate:     2022/3/14
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/14
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class IndexMessageListFragment extends EaseConversationListFragment {


    /**
     * 获取index页面的ViewModel，也就是主页面存储数据的地方
     */
    IndexViewModel indexViewModel;

    /**
     * MainActivity的全局路由
     */
    NavController navController;


    /**
     * 获取全局Activity，该应用只存在一个Activity，即单页面富应用
     */
    FragmentActivity topActivity;



    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        MessageViewModel messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        LiveDataBus messageChange = messageViewModel.getMessageChange();
        messageChange.with(CTransactionConstant.NOTIFY_CHANGE, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(CTransactionConstant.MESSAGE_CHANGE_CHANGE, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(CTransactionConstant.GROUP_CHANGE, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(CTransactionConstant.CHAT_ROOM_CHANGE, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(CTransactionConstant.CONVERSATION_DELETE, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(CTransactionConstant.CONVERSATION_READ, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(CTransactionConstant.CONTACT_CHANGE, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(CTransactionConstant.CONTACT_ADD, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(CTransactionConstant.CONTACT_UPDATE, EaseEvent.class).observe(getViewLifecycleOwner(), this::loadList);
        messageChange.with(CTransactionConstant.MESSAGE_CALL_SAVE, Boolean.class).observe(getViewLifecycleOwner(), this::refreshList);
        messageChange.with(CTransactionConstant.MESSAGE_NOT_SEND, Boolean.class).observe(getViewLifecycleOwner(), this::refreshList);
        EMClient.getInstance().chatManager().addMessageListener(new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                // 接收到消息的监听，立马更新会话列表
                conversationListLayout.loadDefaultData();
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {

            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {

            }

            @Override
            public void onMessageDelivered(List<EMMessage> messages) {

            }

            @Override
            public void onMessageRecalled(List<EMMessage> messages) {

            }
        });
    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public void initData() {
            // 初始化全局Activity
            topActivity = requireParentFragment().requireParentFragment().requireActivity();
            //需要两个条件，判断是否触发从服务器拉取会话列表的时机，一是第一次安装，二则本地数据库没有会话列表数据
            if(AppUtils.isFirstTimeInstalled() && EMClient.getInstance().chatManager().getAllConversations().isEmpty()) {
                // mViewModel.fetchConversationsFromServer();

            }else {
                super.initData();
            }
            // 初始化ViewModel，该ViewModel是从父级的Activity内获取的，即该ViewModel随Activity的生命周期
            indexViewModel = new ViewModelProvider(topActivity).get(IndexViewModel.class);
            // 获取NavHost对应的NavController实例，用来控制这个activity内的NavHost页面的导航
            navController = Navigation.findNavController(topActivity, R.id.mainNavHost);
    }

    private void refreshList(Boolean event) {
        if(event == null) {
            return;
        }
        if(event) {
            conversationListLayout.loadDefaultData();
        }
    }


    private void loadList(EaseEvent change) {
        if(change == null) {
            return;
        }
        if(change.isMessageChange() || change.isNotifyChange()
                || change.isGroupLeave() || change.isChatRoomLeave()
                || change.isContactChange()
                || change.type == EaseEvent.TYPE.CHAT_ROOM || change.isGroupChange()) {
            conversationListLayout.loadDefaultData();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        super.onItemClick(view, position);
        Object item = conversationListLayout.getItem(position).getInfo();
        if(item instanceof EMConversation) {
            if(EaseSystemMsgManager.getInstance().isSystemConversation((EMConversation) item)) {
                // 系统消息，如添加好友之类的
                //SystemMsgsActivity.actionStart(mContext);
            }else {
                // 会话消息，如单聊、群聊
                LogUtils.i("会话列表点击事件");
                indexViewModel.setConversationId("17679374162").setChatType(1);
                navController.navigate(R.id.action_indexFragment_to_chatFragment);
                //ChatActivity.actionStart(mContext, ((EMConversation)item).conversationId(), EaseCommonUtils.getChatType((EMConversation) item));
            }
        }
    }

    @Override
    public void notifyItemChange(int position) {
        super.notifyItemChange(position);
    }

    @Override
    public void notifyAllChange() {
        super.notifyAllChange();
    }

    private boolean isFirst = true;

    private View saveView;

    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (isFirst){
            saveView = super.onCreateView(inflater, container, savedInstanceState);
        }
        return saveView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (isFirst){
            super.onViewCreated(view, savedInstanceState);
            isFirst = false;
        }

    }*/
}
