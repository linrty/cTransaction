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

import com.linrty.ctransaction.BR;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentSwapUploadBinding;
import com.linrty.ctransaction.fragment.swap.model.SwapUploadItemModel;
import com.linrty.ctransaction.plugin.RecyclerViewUtil;

import java.util.ArrayList;
import java.util.List;

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
    FragmentSwapUploadBinding binding;


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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_swap_upload,container,false);
        init();
        return binding.getRoot();
    }

    private void init(){
        List<Object> list = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list.add(new SwapUploadItemModel().setName(String.valueOf(i)));
        }
        RecyclerViewUtil.INSTANCE.bindingSwapUploadList(binding.swapUploadRV, list);
    }

    private void initData(){

    }
}
