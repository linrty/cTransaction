package com.linrty.ctransaction.fragment.index.fragment.model;

import androidx.lifecycle.ViewModel;

/**
  * @ClassName:      IndexHomeItemModel
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/22
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/22
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class IndexHomeItemModel extends ViewModel {
    private String itemImgUrl;
    private String itemTitle;
    private String itemUserName;

    public String getItemImgUrl() {
        return itemImgUrl;
    }

    public IndexHomeItemModel setItemImgUrl(String itemImgUrl) {
        this.itemImgUrl = itemImgUrl;
        return this;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public IndexHomeItemModel setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
        return this;
    }

    public String getItemUserName() {
        return itemUserName;
    }

    public IndexHomeItemModel setItemUserName(String itemUserName) {
        this.itemUserName = itemUserName;
        return this;
    }

    @Override
    public String toString() {
        return "IndexHomeItemModel{" +
                "itemImgUrl='" + itemImgUrl + '\'' +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemUserName='" + itemUserName + '\'' +
                '}';
    }
}
