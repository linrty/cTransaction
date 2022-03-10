package com.linrty.ctransaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavControllerKt;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

     /**
      * @brief 初始化主Activity
      * @param
      * @return
      */

    private void init(){
        // 创建一个ViewModel，并且该ViewModel的生命周期是随着自身Activity的
        indexViewModel = new ViewModelProvider(this).get(IndexViewModel.class);
    }
}