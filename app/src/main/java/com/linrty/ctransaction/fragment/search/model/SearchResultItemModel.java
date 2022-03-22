package com.linrty.ctransaction.fragment.search.model;

import androidx.lifecycle.ViewModel;
 /**
  * @ClassName:      SearchResultItemModel
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/22
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/22
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class SearchResultItemModel extends ViewModel {
    public String itemTitle;
    public String itemImgUrl;
    public Integer itemLookNum;
    public Integer itemLikeNum;
    public String userName;

     @Override
     public String toString() {
         return "SearchResultItemModel{" +
                 "itemTitle='" + itemTitle + '\'' +
                 ", itemImgUrl='" + itemImgUrl + '\'' +
                 ", itemLookNum=" + itemLookNum +
                 ", itemLikeNum=" + itemLikeNum +
                 ", userName='" + userName + '\'' +
                 '}';
     }

     public String getItemTitle() {
         return itemTitle;
     }

     public SearchResultItemModel setItemTitle(String itemTitle) {
         this.itemTitle = itemTitle;
         return this;
     }

     public String getItemImgUrl() {
         return itemImgUrl;
     }

     public SearchResultItemModel setItemImgUrl(String itemImgUrl) {
         this.itemImgUrl = itemImgUrl;
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

     public String getUserName() {
         return userName;
     }

     public SearchResultItemModel setUserName(String userName) {
         this.userName = userName;
         return this;
     }
 }
