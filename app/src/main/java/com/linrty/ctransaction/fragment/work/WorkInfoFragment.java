package com.linrty.ctransaction.fragment.work;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentWrokInfoBinding;

/**
  * @ClassName:      WorkInfoFragment
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/28
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/28
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class WorkInfoFragment extends Fragment {

    private FragmentWrokInfoBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initView();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wrok_info,container,false);
        return binding.getRoot();
    }

    private void initData(){

    }

    private void initView(){

    }


}
