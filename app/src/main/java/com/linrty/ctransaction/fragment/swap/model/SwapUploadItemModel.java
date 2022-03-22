package com.linrty.ctransaction.fragment.swap.model;

import androidx.lifecycle.ViewModel;

/**
  * @ClassName:      SwapUploadItemModel
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/19
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/19
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class SwapUploadItemModel extends ViewModel {
    private String name;

    public String getName() {
        return name;
    }

    public SwapUploadItemModel setName(String name) {
        this.name = name;
        return this;
    }
}
