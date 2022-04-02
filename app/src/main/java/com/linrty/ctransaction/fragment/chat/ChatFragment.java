package com.linrty.ctransaction.fragment.chat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.blankj.utilcode.util.LogUtils;
import com.hyphenate.easeui.constants.EaseConstant;
import com.hyphenate.easeui.modules.chat.EaseChatFragment;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentChatBinding;
import com.linrty.ctransaction.fragment.index.IndexViewModel;

/**
  * @ClassName:      ChatFragment
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/29
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/29
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class ChatFragment extends Fragment {

    FragmentChatBinding binding;

    private ChatMessageFragment chatFragment;

    private IndexViewModel indexViewModel;

    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("ChatFragment : onCreate");
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat,container,false);
        initView();
        LogUtils.i("ChatFragment : onCreateView");
        return binding.getRoot();
    }

    private void initData(){
        // 初始化页面数据
        indexViewModel = new ViewModelProvider(requireActivity()).get(IndexViewModel.class);
        chatFragment = new ChatMessageFragment();
        // 初始化传递参数
        Bundle args = new Bundle();
        // 会话ID,单聊时指对方id，群聊和聊天室时指群和聊天室id
        args.putString(EaseConstant.EXTRA_CONVERSATION_ID, indexViewModel.getConversationId());
        // 聊天类型，整型，分别为单聊（1）、群聊（2）和聊天室（3）
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, indexViewModel.getChatType());
        // (可选参数)消息id，用于查询历史记录时的定位消息id
        //args.putString(EaseConstant.HISTORY_MSG_ID,"");
        // (可选参数)是否开启漫游，布尔类型，用于标记是否优先从服务器拉取消息，默认不开启
        //args.putBoolean(EaseConstant.EXTRA_IS_ROAM,false);
        // 传递会话相关参数
        chatFragment.setArguments(args);
    }

    private void initView(){
        navController = Navigation.findNavController(requireActivity(),R.id.mainNavHost);
        getChildFragmentManager().beginTransaction().add(binding.chatContainer.getId(),chatFragment).commit();
        binding.chatTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        LogUtils.i("ChatFragment : onAttach");
    }


}
