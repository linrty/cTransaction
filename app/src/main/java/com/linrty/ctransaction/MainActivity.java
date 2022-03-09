package com.linrty.ctransaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavControllerKt;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}