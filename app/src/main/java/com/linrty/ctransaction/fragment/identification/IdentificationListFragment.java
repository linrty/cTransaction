package com.linrty.ctransaction.fragment.identification;

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
import com.linrty.ctransaction.bean.Identification;
import com.linrty.ctransaction.databinding.FragmentWorkListBinding;
import com.linrty.ctransaction.fragment.WorkListViewModel;
import com.linrty.ctransaction.fragment.index.IndexViewModel;
import com.linrty.ctransaction.fragment.index.fragment.model.IndexWorkListItemModel;
import com.linrty.ctransaction.plugin.RecyclerViewUtil;

import java.util.Random;

public class IdentificationListFragment extends Fragment {
    FragmentWorkListBinding binding;

    WorkListViewModel model;

    IndexViewModel indexModel;

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
        indexModel = new ViewModelProvider(requireActivity()).get(IndexViewModel.class);
        model = new ViewModelProvider(this).get(WorkListViewModel.class);
        // 模拟一下数据
        for (int i = 0; i < 10; i++) {
            model.getList().add(new Identification().setState(1));
        }
    }

    private void initView(){
        RecyclerViewUtil.INSTANCE.bindingIdentificationList(binding.workListRV,model.getList());
    }
}
