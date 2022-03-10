package com.linrty.ctransaction.fragment.index.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linrty.ctransaction.R;

 /**
  * @ClassName:      IndexWorkFragment
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/10
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/10
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class IndexWorkFragment extends Fragment {


    public IndexWorkFragment() {
        // Required empty public constructor
    }

    public static IndexWorkFragment newInstance(String param1, String param2) {
        IndexWorkFragment fragment = new IndexWorkFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_index_work, container, false);
    }
}