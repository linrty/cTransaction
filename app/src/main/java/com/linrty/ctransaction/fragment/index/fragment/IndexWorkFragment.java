package com.linrty.ctransaction.fragment.index.fragment;

import static androidx.core.view.ViewKt.postDelayed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linrty.ctransaction.BR;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentIndexWorkBinding;
import com.linrty.ctransaction.fragment.index.fragment.model.IndexWorkListItemModel;
import com.linrty.ctransaction.plugin.RecyclerViewUtil;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
  * @ClassName:      IndexWorkFragment
  * @Description:    用于展示用于个人拥有的数字版权作评列表
  * @Author:         Linrty
  * @CreateDate:     2022/3/10
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/10
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class IndexWorkFragment extends Fragment {

    FragmentIndexWorkBinding fragmentIndexWorkBinding;

    public IndexWorkFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentIndexWorkBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_index_work,container,false);
        // 模拟列表数据
        List<Object> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(new IndexWorkListItemModel().setName(String.valueOf(i)));
        }
        List<Object> list2 = new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(new IndexWorkListItemModel().setName(String.valueOf(i)));
        }
        // 设置RecyclerView列表并且对Item进行dataBinding
        RecyclerViewUtil.INSTANCE.bindingIndexWorkList(fragmentIndexWorkBinding.indexWorkRV, BR.indexWorkItemData,list, fragmentIndexWorkBinding.indexWorkRL);
        // 设置下拉刷新监听
        fragmentIndexWorkBinding.indexWorkRL.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                fragmentIndexWorkBinding.indexWorkRL.finishRefresh(2000);
            }
        });
        return fragmentIndexWorkBinding.getRoot();
    }
}