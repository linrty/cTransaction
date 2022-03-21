package com.linrty.ctransaction.fragment.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.BarUtils;
import com.linrty.ctransaction.BR;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentSearchResultBinding;
import com.linrty.ctransaction.fragment.index.IndexViewModel;
import com.linrty.ctransaction.fragment.search.model.SearchResultItemModel;
import com.linrty.ctransaction.fragment.search.model.SearchResultModel;
import com.linrty.ctransaction.plugin.RecyclerViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
  * @ClassName:      SearchResultFragment
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/21
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/21
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class SearchResultFragment extends Fragment {
    FragmentSearchResultBinding binding;

    /**
     * 获取index页面的ViewModel，也就是主页面存储数据的地方
     */
    IndexViewModel indexViewModel;

    /**
     * SearchResult下的数据Model类
     */
    SearchResultModel model;

    public SearchResultFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_result,container,false);
        init();
        return binding.getRoot();
    }

    private void initData(){
        BarUtils.setStatusBarLightMode(getActivity(),true);
        // 初始化ViewModel，该ViewModel是从父级的Activity内获取的，即该ViewModel随Activity的生命周期
        indexViewModel = new ViewModelProvider(requireActivity()).get(IndexViewModel.class);



    }

    private void init(){
        // 初始化本Fragment下的ViewModel
        model = new ViewModelProvider(this).get(SearchResultModel.class);
        // 模拟请求到的数据
        List<Object> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            SearchResultItemModel itemModel;
            itemModel = new SearchResultItemModel();
            itemModel.setItemTitle("ssdfasdfs"+i);
            list.add(itemModel);
        }
        model.setSearchResultList(list);
        binding.setSearchResultData(model);
        binding.setLifecycleOwner(this);
        // 设置RecyclerView列表并且对Item进行dataBinding
        RecyclerViewUtil.INSTANCE.bindingSearchResultList(binding.searchResultRV, BR.searchResultItemData,model.getSearchResultList());
    }
}
