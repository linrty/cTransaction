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
    private Integer itemLikeNum;
    private int itemType;


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

    public Integer getItemLikeNum() {
        return itemLikeNum;
    }

    public IndexHomeItemModel setItemLikeNum(Integer itemLikeNum) {
        this.itemLikeNum = itemLikeNum;
        return this;
    }

    public int getItemType() {
        return itemType;
    }

    public IndexHomeItemModel setItemType(int itemType) {
        this.itemType = itemType;
        return this;
    }

    @Override
    public String toString() {
        return "IndexHomeItemModel{" +
                "itemImgUrl='" + itemImgUrl + '\'' +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemUserName='" + itemUserName + '\'' +
                ", itemLikeNum=" + itemLikeNum +
                ", itemType=" + itemType +
                '}';
    }
}
