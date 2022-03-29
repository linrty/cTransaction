package com.linrty.ctransaction.fragment.index.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.modules.conversation.EaseConversationListFragment;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentIndexMessageBinding;
import com.linrty.ctransaction.fragment.index.IndexViewModel;

import java.util.List;

/**
  * @ClassName:      IndexMessageFragment
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/10
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/10
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class IndexMessageFragment extends Fragment {

    FragmentIndexMessageBinding binding;

    /**
     * 获取index页面的ViewModel，也就是主页面存储数据的地方
     */
    IndexViewModel indexViewModel;

    IndexMessageListFragment messageListFragment;

    public IndexMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_index_message,container,false);
        // EaseConversationListFragment easeConversationListFragment = new EaseConversationListFragment();
        initView();
        return binding.getRoot();
    }

    private void initData(){
        messageListFragment = new IndexMessageListFragment();
    }

    private void initView(){
        getChildFragmentManager().beginTransaction().replace(binding.container.getId(),messageListFragment).commit();
    }

}