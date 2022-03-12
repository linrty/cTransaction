package com.linrty.ctransaction.fragment.index.fragment.model;


import androidx.lifecycle.ViewModel;

/**
  * @ClassName:      IndexWorkListItemModel
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/12
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/12
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class IndexWorkListItemModel extends ViewModel {
    public String name;

    public String getName() {
        return name;
    }

    public IndexWorkListItemModel setName(String name) {
        this.name = name;
        return this;
    }
}
