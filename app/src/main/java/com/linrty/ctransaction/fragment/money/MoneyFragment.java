package com.linrty.ctransaction.fragment.money;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentMoneyBinding;
import com.linrty.ctransaction.fragment.index.IndexViewModel;

/**
  * @ClassName:      MoneyFragment
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/4/5
  * @UpdateUser:     updater
  * @UpdateDate:     2022/4/5
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class MoneyFragment extends Fragment {

    FragmentMoneyBinding binding;

    IndexViewModel model;

    @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         initData();
     }

     @Nullable
     @Override
     public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_money,container,false);
        initView();
        return binding.getRoot();
     }

     private void initData(){

     }

     private void initView(){
        binding.addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
     }


 }
