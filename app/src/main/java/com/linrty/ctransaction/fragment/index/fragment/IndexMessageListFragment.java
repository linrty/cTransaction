package com.linrty.ctransaction.fragment.index.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.AppUtils;
import com.bumptech.glide.load.engine.Resource;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.manager.EaseSystemMsgManager;
import com.hyphenate.easeui.model.EaseEvent;
import com.hyphenate.easeui.modules.conversation.EaseConversationListFragment;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.linrty.ctransaction.common.constent.CTransactionConstant;
import com.linrty.ctransaction.common.livedatas.LiveDataBus;
import com.linrty.ctransaction.common.viewmodel.MessageViewModel;

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
        //需要两个条件，判断是否触发从服务器拉取会话列表的时机，一是第一次安装，二则本地数据库没有会话列表数据
        if(AppUtils.isFirstTimeInstalled() && EMClient.getInstance().chatManager().getAllConversations().isEmpty()) {
            // mViewModel.fetchConversationsFromServer();

        }else {
            super.initData();
        }
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
            /*if(EaseSystemMsgManager.getInstance().isSystemConversation((EMConversation) item)) {
                SystemMsgsActivity.actionStart(mContext);
            }else {
                ChatActivity.actionStart(mContext, ((EMConversation)item).conversationId(), EaseCommonUtils.getChatType((EMConversation) item));
            }*/
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
}
