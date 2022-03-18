package com.linrty.ctransaction.fragment.index.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentIndexHomeBinding;

/**
  * @ClassName:      IndexHomeFragment
  * @Description:    用于展示Home，App首页的Fragment
  * @Author:         Linrty
  * @CreateDate:     2022/3/10
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/10
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class IndexHomeFragment extends Fragment {

    FragmentIndexHomeBinding fragmentIndexHomeBinding;

    public IndexHomeFragment() {
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
        fragmentIndexHomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_index_home,container,false);
        return fragmentIndexHomeBinding.getRoot();
    }

}