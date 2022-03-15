package com.linrty.ctransaction.fragment.index.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentIndexUserBinding;
import com.linrty.ctransaction.plugin.BottomSheetUtil;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

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

    FragmentIndexUserBinding fragmentIndexUserBinding;

    public IndexUserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentIndexUserBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_index_user,container,false);
        fragmentIndexUserBinding.button45.setOnClickListener(new View.OnClickListener() {
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
        });
        return fragmentIndexUserBinding.getRoot();
    }

}