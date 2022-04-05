package com.linrty.ctransaction.fragment.buy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.linrty.ctransaction.R;
import com.linrty.ctransaction.bean.Order;
import com.linrty.ctransaction.databinding.FragmentWorkListBinding;
import com.linrty.ctransaction.fragment.WorkListViewModel;
import com.linrty.ctransaction.plugin.RecyclerViewUtil;

/**
  * @ClassName:      BuyHistoryFragment
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/4/5
  * @UpdateUser:     updater
  * @UpdateDate:     2022/4/5
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class BuyHistoryFragment extends Fragment {

    FragmentWorkListBinding binding;

    WorkListViewModel model;

    NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_work_list,container,false);
        initView();
        return binding.getRoot();
    }


    private void initData(){
        model = new ViewModelProvider(this).get(WorkListViewModel.class);
        // 模拟一下数据
        for (int i = 0; i < 10; i++) {
            model.getList().add(new Order().setWorkId((long) i));
        }
    }

    private void initView(){
        navController = Navigation.findNavController(requireActivity(),R.id.mainNavHost);

        RecyclerViewUtil.INSTANCE.bindingBuyHistoryList(binding.workListRV,model.getList(),navController);
    }


}
