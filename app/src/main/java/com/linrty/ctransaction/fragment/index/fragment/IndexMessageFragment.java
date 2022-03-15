package com.linrty.ctransaction.fragment.index.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentIndexMessageBinding;

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

    FragmentIndexMessageBinding fragmentIndexMessageBinding;

    public IndexMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentIndexMessageBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_index_message,container,false);
        return fragmentIndexMessageBinding.getRoot();
    }
}