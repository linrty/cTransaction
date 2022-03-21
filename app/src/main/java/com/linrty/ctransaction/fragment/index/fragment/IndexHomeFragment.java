package com.linrty.ctransaction.fragment.index.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.blankj.utilcode.util.BarUtils;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentIndexHomeBinding;
import com.linrty.ctransaction.fragment.index.IndexViewModel;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.xuexiang.xui.utils.StatusBarUtils;

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

    /**
     * 获取index页面的ViewModel，也就是主页面存储数据的地方
     */
    IndexViewModel indexViewModel;

    /**
     * App的全局路由
     */
    NavController navController;

    public IndexHomeFragment() {
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

        fragmentIndexHomeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_index_home,container,false);
        init();
        return fragmentIndexHomeBinding.getRoot();
    }

    private void initData(){
        // 初始化ViewModel，该ViewModel是从父级的Activity内获取的，即该ViewModel随Activity的生命周期
        indexViewModel = new ViewModelProvider(requireParentFragment().requireActivity()).get(IndexViewModel.class);
    }

    private void init(){
        // 设置该页面的Model
        fragmentIndexHomeBinding.setIndexData(indexViewModel);
        fragmentIndexHomeBinding.setLifecycleOwner(requireParentFragment().requireActivity());
        // 获取NavHost对应的NavController实例，用来控制这个activity内的NavHost页面的导航
        navController = Navigation.findNavController(requireParentFragment().requireActivity(),R.id.mainNavHost);
        // 设置SearchBar的各类监听事件
        fragmentIndexHomeBinding.indexWorkSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                navController.navigate(R.id.action_indexFragment_to_searchResultFragment);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });
    }

}