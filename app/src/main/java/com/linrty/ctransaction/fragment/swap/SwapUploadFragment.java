package com.linrty.ctransaction.fragment.swap;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentSwapUploadBinding;

/**
  * @ClassName:      SwapUploadFragment
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/19
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/19
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class SwapUploadFragment extends Fragment {

    /**
     * 上传文件列表的dataBinding
     */
    FragmentSwapUploadBinding fragmentSwapUploadBinding;


    public SwapUploadFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentSwapUploadBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_swap_upload,container,false);
        init();
        return fragmentSwapUploadBinding.getRoot();
    }

    private void init(){

    }

    private void initData(){

    }
}
