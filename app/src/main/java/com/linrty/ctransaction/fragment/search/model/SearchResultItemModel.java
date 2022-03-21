package com.linrty.ctransaction.fragment.search.model;


/**
  * @ClassName:      SearchResultIteModel
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/21
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/21
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class SearchResultItemModel {
    private String itemTitle;
    private String itemUserName;
    private Integer itemLookNum;
    private Integer itemLikeNum;
    private String itemImgUrl;


    public String getItemTitle() {
        return itemTitle;
    }

    public SearchResultItemModel setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
        return this;
    }

    public String getItemUserName() {
        return itemUserName;
    }

    public SearchResultItemModel setItemUserName(String itemUserName) {
        this.itemUserName = itemUserName;
        return this;
    }

    public Integer getItemLookNum() {
        return itemLookNum;
    }

    public SearchResultItemModel setItemLookNum(Integer itemLookNum) {
        this.itemLookNum = itemLookNum;
        return this;
    }

    public Integer getItemLikeNum() {
        return itemLikeNum;
    }

    public SearchResultItemModel setItemLikeNum(Integer itemLikeNum) {
        this.itemLikeNum = itemLikeNum;
        return this;
    }

    public String getItemImgUrl() {
        return itemImgUrl;
    }

    public SearchResultItemModel setItemImgUrl(String itemImgUrl) {
        this.itemImgUrl = itemImgUrl;
        return this;
    }

    @Override
    public String toString() {
        return "SearchResultIteModel{" +
                "itemTitle='" + itemTitle + '\'' +
                ", itemUserName='" + itemUserName + '\'' +
                ", itemLookNum=" + itemLookNum +
                ", itemLikeNum=" + itemLikeNum +
                ", itemImgUrl='" + itemImgUrl + '\'' +
                '}';
    }


}
