package com.linrty.ctransaction.fragment.index.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.blankj.utilcode.util.BarUtils;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentIndexHomeBinding;
import com.linrty.ctransaction.fragment.index.IndexViewModel;
import com.linrty.ctransaction.fragment.index.fragment.model.IndexHomeItemModel;
import com.linrty.ctransaction.plugin.RecyclerViewUtil;
import com.linrty.ctransaction.util.CodeUtil;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.xuexiang.xui.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

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

    FragmentIndexHomeBinding binding;

    /**
     * 获取index页面的ViewModel，也就是主页面存储数据的地方
     */
    IndexViewModel indexViewModel;

    /**
     * App的全局路由
     */
    NavController navController;

    /**
     * 是否是第一次加载视图
     */
    private boolean isFirst = true;


    /**
     *  保存渲染好的视图，避免重新加载
     */
    private View saveView = null;

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
        if (isFirst){
            binding = DataBindingUtil.inflate(inflater,R.layout.fragment_index_home,container,false);
            init();
            saveView = binding.getRoot();
        }
        return saveView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (isFirst){
            super.onViewCreated(view, savedInstanceState);
            isFirst = false;
        }
    }

    private void initData(){
        // 初始化ViewModel，该ViewModel是从父级的Activity内获取的，即该ViewModel随Activity的生命周期
        indexViewModel = new ViewModelProvider(requireParentFragment().requireActivity()).get(IndexViewModel.class);
    }

    private void init(){
        // 设置该页面的Model
        binding.setData(indexViewModel);
        binding.setLifecycleOwner(requireParentFragment().requireActivity());
        // 模拟列表数据
        List<Object> list = new ArrayList<>();
        for (int i=0;i<20;i++){
            list.add(new IndexHomeItemModel().setItemTitle(String.valueOf(i)).setItemType(CodeUtil.CODE_INDEX_HOME_ITEM_SMALL));
            if (i%8 == 7){
                list.add(new IndexHomeItemModel().setItemTitle(String.valueOf(i)).setItemType(CodeUtil.CODE_INDEX_HOME_ITEM_BIG));
            }
        }
        GridLayoutManager layoutManager = new GridLayoutManager(requireParentFragment().getContext(),2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (((IndexHomeItemModel)(list.get(position))).getItemType() == CodeUtil.CODE_INDEX_HOME_ITEM_SMALL){
                    return 1;
                }else{
                    return 2;
                }
            }
        });
        RecyclerViewUtil.INSTANCE.bindingIndexHomeList(binding.indexHomeRV,list);
        // 获取NavHost对应的NavController实例，用来控制这个activity内的NavHost页面的导航
        navController = Navigation.findNavController(requireParentFragment().requireActivity(),R.id.mainNavHost);
        // 设置SearchBar的各类监听事件
        binding.indexWorkSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
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