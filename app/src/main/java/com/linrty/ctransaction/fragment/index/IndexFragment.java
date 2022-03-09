package com.linrty.ctransaction.fragment.index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.linrty.ctransaction.adapter.ViewPageFragmentAdapter;
import com.linrty.ctransaction.databinding.FragmentIndexBinding;

import java.util.List;

  /**
   * @ClassName:      IndexFragment
   * @Description:    Index总页面的逻辑
   * @Author:         Linrty
   * @CreateDate:     2022/3/9
   * @UpdateUser:     updater
   * @UpdateDate:     2022/3/9
   * @UpdateRemark:   更新内容
   * @Version:        1.0
   */
public class IndexFragment extends Fragment {

    /**
     * IndexFragment内有四个子Fragment分别为user、work、message、home
     */
    List<Fragment> indexFragments;

    /**
     * ViewPager是基于RecyclerView实现的，所以需要对应的Adapter
     */
    ViewPageFragmentAdapter indexFragmentAdapter;

    /**
     * 记录当前所在的Fragment，防止Navigation使用popBackStack方法后，导致ViewPager内的Fragment与底部导航栏不匹配
     */
    Integer currentFragment;

      /**
       * 与视图UI控件进行绑定
       */
    FragmentIndexBinding fragmentIndexBinding;



    public IndexFragment(){
        // Required empty public constructor
    }





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 与指定的视图进行绑定
        fragmentIndexBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_index,container,false);
        init();
        return fragmentIndexBinding.getRoot();
    }


     /**
      * @brief 初始化数据
      * @param
      * @return
      */
    private void init(){

    }
}
