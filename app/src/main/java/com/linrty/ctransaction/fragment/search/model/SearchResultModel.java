package com.linrty.ctransaction.fragment.search.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
  * @ClassName:      SearchResultModel
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/21
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/21
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class SearchResultModel extends ViewModel {
    private List<Object> searchResultList = new ArrayList<>();

    private CharSequence searchWords;

    public List<Object> getSearchResultList() {
        return searchResultList;
    }

    public SearchResultModel setSearchResultList(List<Object> searchResultList) {
        this.searchResultList = searchResultList;
        return this;
    }

    public CharSequence getSearchWords() {
        return searchWords;
    }

    public SearchResultModel setSearchWords(CharSequence searchWords) {
        this.searchWords = searchWords;
        return this;
    }

}
