package com.linrty.ctransaction.fragment.index.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.blankj.utilcode.util.ToastUtils;
import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentIndexUserBinding;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.widget.grouplist.XUICommonListItemView;
import com.xuexiang.xui.widget.grouplist.XUIGroupListView;
import com.xuexiang.xui.widget.grouplist.XUIGroupListView.*;

import java.util.ArrayList;
import java.util.List;

/**
  * @ClassName:      IndexUserFragment
  * @Description:    用于展示用户个人信息的Fragment
  * @Author:         Linrty
  * @CreateDate:     2022/3/10
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/10
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class IndexUserFragment extends Fragment {

    FragmentIndexUserBinding binding;


    /**
     * 是否第一次渲染视图
     */
    private boolean isFirst = true;

    /**
     * 保存渲染好的视图，避免重新加载而卡顿,仅仅针对Index及其子页面这些围绕整个Activity都不需要重新加载的页面
     */
    private View saveView = null;

    /**
     * 全局路由
     */
    private NavController navController;

    /**
     * 最顶部的Activity
     */
    private FragmentActivity topActivity;


    public IndexUserFragment() {
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

        /*fragmentIndexUserBinding.button45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 这种弹窗从 1.0.0版本开始实现了优雅的手势交互和智能嵌套滚动
                new XPopup.Builder(getContext())
                        .asBottomList("请选择一项", new String[]{"条目1", "条目2", "条目3", "条目4", "条目5"},
                                new OnSelectListener() {
                                    @Override
                                    public void onSelect(int position, String text) {
                                        Toast.makeText(getActivity(), "click " + text + (position + 1), Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .show();
            }
        });*/

        if (isFirst){
            binding = DataBindingUtil.inflate(inflater,R.layout.fragment_index_user,container,false);
            initView();
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

    }

    private void initView(){
        topActivity = requireParentFragment().requireActivity();
        navController = Navigation.findNavController(topActivity,R.id.mainNavHost);
        // index user 页面中的列表子项集合
        XUICommonListItemView itemView = null;
        int size = DensityUtils.dp2px(getContext(), 20);
        Section section = XUIGroupListView.newSection(getContext())
                .setLeftIconSize(size, size);
        // 初始化各个子项目样式及事件
        // 个人中心
        section.addItemView(createItemView("个人中心", R.drawable.ic_profile_setting), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort("个人中心");
            }
        });
        // 我的收藏
        section.addItemView(createItemView("我的收藏", R.drawable.ic_collect), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort("我的收藏");
                gotoFragment(R.id.action_indexFragment_to_myCollectFragment);
            }
        });
        // 余额
        section.addItemView(createItemView("余额", R.drawable.ic_money), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort("余额");
            }
        });
        // 设置
        section.addItemView(createItemView("设置", R.drawable.ic_setting), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort("设置");
            }
        });
        section.addTo(binding.indexUserGroupList);

        binding.myBuyIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoFragment(R.id.action_indexFragment_to_buyHistoryFragment);
            }
        });
        binding.myBuyTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoFragment(R.id.action_indexFragment_to_buyHistoryFragment);
            }
        });
        binding.myIdentificationIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.myIdentificationTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.mySellIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoFragment(R.id.action_indexFragment_to_sellHistoryFragment);
            }
        });
        binding.mySellTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoFragment(R.id.action_indexFragment_to_sellHistoryFragment);
            }
        });
        binding.appealHandlingIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.appealHandlingTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    /**
     * 对创建ItemView进行简单封装
     * @param titleText  标题
     * @param imageId  标题前的icon
     * @return  item view
     */
    private XUICommonListItemView createItemView(String titleText, int imageId){
        XUICommonListItemView itemView = binding.indexUserGroupList.createItemView(titleText);
        itemView.setImageDrawable(ContextCompat.getDrawable(getContext(),imageId));
        itemView.setAccessoryType(XUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        return  itemView;
    }

    /**
     * 从当前Fragment跳转到指定的Fragment
     * @param desFragment 目的Fragment
     */
    private void gotoFragment(int desFragment){
        navController.navigate(desFragment);
    }


}