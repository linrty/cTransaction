package com.linrty.ctransaction.bean;
 /**
  * @ClassName:      AppealHandling
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/4/6
  * @UpdateUser:     updater
  * @UpdateDate:     2022/4/6
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class AppealHandling {
    private Long appealHandlingId;

    private String appealHandlingTime;

    private int state;

    private Long workId;

    private String workImgUrl;

    private String workTitle;

     public Long getAppealHandlingId() {
         return appealHandlingId;
     }

     public AppealHandling setAppealHandlingId(Long appealHandlingId) {
         this.appealHandlingId = appealHandlingId;
         return this;
     }

     public String getAppealHandlingTime() {
         return appealHandlingTime;
     }

     public AppealHandling setAppealHandlingTime(String appealHandlingTime) {
         this.appealHandlingTime = appealHandlingTime;
         return this;
     }

     public int getState() {
         return state;
     }

     public AppealHandling setState(int state) {
         this.state = state;
         return this;
     }

     public Long getWorkId() {
         return workId;
     }

     public AppealHandling setWorkId(Long workId) {
         this.workId = workId;
         return this;
     }

     public String getWorkImgUrl() {
         return workImgUrl;
     }

     public AppealHandling setWorkImgUrl(String workImgUrl) {
         this.workImgUrl = workImgUrl;
         return this;
     }

     public String getWorkTitle() {
         return workTitle;
     }

     public AppealHandling setWorkTitle(String workTitle) {
         this.workTitle = workTitle;
         return this;
     }
 }
