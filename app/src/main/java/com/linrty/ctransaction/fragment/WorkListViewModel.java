package com.linrty.ctransaction.fragment;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
  * @ClassName:      WorkListViewModel
  * @Description:    用于展示各类作品列表的页面对应的model，使用泛型能更好的复用
  * @Author:         Linrty
  * @CreateDate:     2022/4/5
  * @UpdateUser:     updater
  * @UpdateDate:     2022/4/5
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class WorkListViewModel extends ViewModel {
    public List<Object> list = new ArrayList<>();

    public List<Object> getList() {
        return list;
    }

    public WorkListViewModel setList(List<Object> list) {
        this.list = list;
        return this;
    }
}
