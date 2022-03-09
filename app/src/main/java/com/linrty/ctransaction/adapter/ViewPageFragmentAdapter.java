package com.linrty.ctransaction.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;
 /**
  * @ClassName:      ViewPageFragmentAdapter
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/9
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/9
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class ViewPageFragmentAdapter extends FragmentStateAdapter {
    private List<Fragment> fragments;

    public ViewPageFragmentAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragments) {
        super(fragmentActivity);
        this.fragments = fragments;
    }

    public ViewPageFragmentAdapter(@NonNull Fragment fragment, List<Fragment> fragments) {
        super(fragment);
        this.fragments = fragments;
    }

    public ViewPageFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Fragment> fragments) {
        super(fragmentManager, lifecycle);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
