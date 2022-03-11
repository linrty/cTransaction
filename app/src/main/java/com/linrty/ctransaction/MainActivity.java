package com.linrty.ctransaction;

import static com.blankj.utilcode.util.BarUtils.transparentStatusBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavControllerKt;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.linrty.ctransaction.databinding.ActivityMainBinding;
import com.linrty.ctransaction.fragment.index.IndexViewModel;

/**
  * @ClassName:      MainActivity
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/9
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/9
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class MainActivity extends AppCompatActivity {

    NavController navController;
    IndexViewModel indexViewModel;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        init();
    }

     /**
      * @brief 初始化主Activity
      * @param
      * @return
      */

    private void init(){
        // 获取NavHost对应的NavController实例，用来控制这个activity内的NavHost页面的导航
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mainNavHost);
        navController = navHostFragment.getNavController();
        // 创建一个ViewModel，并且该ViewModel的生命周期是随着自身Activity的
        indexViewModel = new ViewModelProvider(this).get(IndexViewModel.class);
        transparentStatusBar(this);
    }
}