package com.linrty.ctransaction.fragment.swap;

import static com.linrty.ctransaction.util.CodeUtil.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.linrty.ctransaction.R;
import com.linrty.ctransaction.adapter.ViewPageFragmentAdapter;
import com.linrty.ctransaction.databinding.FragmentSwapBinding;
import com.linrty.ctransaction.fragment.index.IndexViewModel;

import java.util.ArrayList;
import java.util.List;

/**
  * @ClassName:      SwapFragment
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/19
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/19
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class SwapFragment extends Fragment {

    /**
     * 全局VIewModel主要用于记录一些全局需要使用到的数据
     */
    IndexViewModel indexViewModel;

    /**
     * Fragment的binding
     */
    FragmentSwapBinding fragmentSwapBinding;

    /**
     * 传输列表页面的子页面
     */
    List<Fragment> swapFragments;

    /**
     * 子页面对应的ViewPager的Adapter
     */
    ViewPageFragmentAdapter swapFragmentAdapter;

    /**
     * 记录目前的所在的Fragment编码
     */
    Integer currentFragment;


    public SwapFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentSwapBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_swap,container,false);
        init();
        return fragmentSwapBinding.getRoot();
    }

    private void  init(){
        // 初始化ViewModel，该ViewModel是从父级的Activity内获取的，即该ViewModel随Activity的生命周期
        indexViewModel = new ViewModelProvider(requireActivity()).get(IndexViewModel.class);
        // 设置页面的数据
        fragmentSwapBinding.setSwapData(indexViewModel);
        // 设置Fragment生命周期交给谁管
        fragmentSwapBinding.setLifecycleOwner(requireActivity());
        swapFragments = new ArrayList<>();
        // 加入的顺序决定了页面显示的排序
        swapFragments.add(new SwapUploadFragment());
        swapFragments.add(new SwapDownloadFragment());
        // 初始化子页面ViewPager的适配器并指定相应的子Fragment
        swapFragmentAdapter = new ViewPageFragmentAdapter(this,swapFragments);
        // 设置预加载数量
        fragmentSwapBinding.swapVP.setOffscreenPageLimit(2);
        // 绑定适配器
        fragmentSwapBinding.swapVP.setAdapter(swapFragmentAdapter);
        // 设置刚加载进来时的页面
        fragmentSwapBinding.swapVP.setCurrentItem(CODE_FRAGMENT_SWAP_UPLOAD-CODE_FRAGMENT_SWAP-1);
        currentFragment = CODE_FRAGMENT_SWAP_UPLOAD;

    }
}
