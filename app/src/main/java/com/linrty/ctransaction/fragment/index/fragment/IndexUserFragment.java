package com.linrty.ctransaction.fragment.index.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.linrty.ctransaction.R;
import com.linrty.ctransaction.databinding.FragmentIndexUserBinding;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

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
        fragmentIndexUserBinding.indexUserItem1.setText("Item 3");
        fragmentIndexUserBinding.indexUserItem1.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        fragmentIndexUserBinding.indexUserItem2.setText("Item 4");
        fragmentIndexUserBinding.indexUserItem2.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        fragmentIndexUserBinding.indexUserItem3.setText("Item 5");
        fragmentIndexUserBinding.indexUserItem3.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        fragmentIndexUserBinding.indexUserItem4.setText("Item 6");
        fragmentIndexUserBinding.indexUserItem4.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
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
        return fragmentIndexUserBinding.getRoot();
    }

}