package com.linrty.ctransaction.fragment.work;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.blankj.utilcode.util.ToastUtils;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentWrokInfoBinding;
import com.linrty.ctransaction.fragment.index.IndexViewModel;
import com.linrty.ctransaction.plugin.RecyclerViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
  * @ClassName:      WorkInfoFragment
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/28
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/28
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class WorkInfoFragment extends Fragment {

    /**
     *  WorkInfo页面的Binding
     */
    private FragmentWrokInfoBinding binding;

    /**
     * 全局路由
     */
    private NavController navController;

    /**
     * 获取全局Activity，该应用只存在一个Activity，即单页面富应用
     */
    FragmentActivity topActivity;


    /**
     * 全局model
     */
    private IndexViewModel indexModel;


    /**
     * workInfo页面的Model
     */
    private WorkInfoViewModel model;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wrok_info,container,false);
        initView();
        return binding.getRoot();
    }

    private void initData(){

        indexModel = new ViewModelProvider(requireActivity()).get(IndexViewModel.class);
        model = new ViewModelProvider(this).get(WorkInfoViewModel.class);
        model.setWorkId(getArguments().getLong("work_id"));
        // 模拟数据
        List<Object> commentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            commentList.add(new CommentModel());
        }
        model.setCommentList(commentList);
    }

    private void initView(){
        topActivity = requireActivity();
        navController = Navigation.findNavController(topActivity,R.id.mainNavHost);

        RecyclerViewUtil.INSTANCE.bindingCommentList(binding.workInfoCommentRV,model.getCommentList(),navController);
    }

}
