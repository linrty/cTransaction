package com.linrty.ctransaction.fragment.index.fragment;

import static androidx.core.view.ViewKt.postDelayed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drake.brv.annotaion.AnimationType;
import com.linrty.ctransaction.BR;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentIndexWorkBinding;
import com.linrty.ctransaction.fragment.index.fragment.model.IndexWorkListItemModel;
import com.linrty.ctransaction.fragment.swap.model.SwapUploadItemModel;
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

    /**
     * index work页面的布局控件
     */
    FragmentIndexWorkBinding fragmentIndexWorkBinding;

    /**
     * App的全局路由
     */
    NavController navController;

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
            list.add(new SwapUploadItemModel().setName(String.valueOf(i)));
        }
        List<Object> list2 = new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(new SwapUploadItemModel().setName(String.valueOf(i)));
        }
        // 设置RecyclerView列表并且对Item进行dataBinding
        RecyclerViewUtil.INSTANCE.bindingIndexWorkList(fragmentIndexWorkBinding.indexWorkRV, list, fragmentIndexWorkBinding.indexWorkRL);
        // 设置下拉刷新监听
        fragmentIndexWorkBinding.indexWorkRL.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                fragmentIndexWorkBinding.indexWorkRL.finishRefresh(2000);
            }
        });
        init();
        return fragmentIndexWorkBinding.getRoot();
    }

    private void init(){
        // 获取NavHost对应的NavController实例，用来控制这个activity内的NavHost页面的导航
        navController = Navigation.findNavController(requireParentFragment().requireActivity(),R.id.mainNavHost);


        // 设置work页面的传输图标点击事件
        fragmentIndexWorkBinding.indexWorkToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 进入传输列表页面
                navController.navigate(R.id.action_indexFragment_to_swapFragment);
            }
        });
    }
}