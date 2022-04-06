package com.linrty.ctransaction.bean;
 /**
  * @ClassName:      Identification
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/4/6
  * @UpdateUser:     updater
  * @UpdateDate:     2022/4/6
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class Identification {

     /**
      * 认证编号
      */
    private Long identificationId;

     /**
      * 用户ID
      */
    private Long userId;

     /**
      * 认证目前状态，共原创性检验中、认证失败、签名确认、认证成功
      * 0：原创性检验中
      * -1：认证失败，查重率过高
      * 1：待确认
      * 2：认证成功
      */
    private int state;

     /**
      * 作品文件Url
      */
    private String fileUrl;

     /**
      * 图片文件Url
      */
    private String imageUrl;


     /**
      * 作品标题
      */
    private String workTitle;

     /**
      * 认证创建时间
      */
    private String createTime;

     /**
      * 失败时间
      */
    private String failTime;

     /**
      * 成功时间
      */
    private String successTime;

     /**
      * 确认时间
      */
    private String confirmTime;

     /**
      * 证书文件Url
      */
    private String certificateFileUrl;

     /**
      * 证书文件ID，目的和签名文件一样
      */
    private String certificateFileId;

     /**
      * 作品文件ID，将ID保存至区块链中防止篡改
      */
    private String fileId;

     /**
      * 查重率，相似度是多少，取值0-1
      */
     private double similar;

     public Long getIdentificationId() {
         return identificationId;
     }

     public Identification setIdentificationId(Long identificationId) {
         this.identificationId = identificationId;
         return this;
     }

     public Long getUserId() {
         return userId;
     }

     public Identification setUserId(Long userId) {
         this.userId = userId;
         return this;
     }

     public int getState() {
         return state;
     }

     public Identification setState(int state) {
         this.state = state;
         return this;
     }

     public String getFileUrl() {
         return fileUrl;
     }

     public Identification setFileUrl(String fileUrl) {
         this.fileUrl = fileUrl;
         return this;
     }

     public String getImageUrl() {
         return imageUrl;
     }

     public Identification setImageUrl(String imageUrl) {
         this.imageUrl = imageUrl;
         return this;
     }

     public String getWorkTitle() {
         return workTitle;
     }

     public Identification setWorkTitle(String workTitle) {
         this.workTitle = workTitle;
         return this;
     }

     public String getCreateTime() {
         return createTime;
     }

     public Identification setCreateTime(String createTime) {
         this.createTime = createTime;
         return this;
     }

     public String getFailTime() {
         return failTime;
     }

     public Identification setFailTime(String failTime) {
         this.failTime = failTime;
         return this;
     }

     public String getSuccessTime() {
         return successTime;
     }

     public Identification setSuccessTime(String successTime) {
         this.successTime = successTime;
         return this;
     }

     public String getConfirmTime() {
         return confirmTime;
     }

     public Identification setConfirmTime(String confirmTime) {
         this.confirmTime = confirmTime;
         return this;
     }

     public String getCertificateFileUrl() {
         return certificateFileUrl;
     }

     public Identification setCertificateFileUrl(String certificateFileUrl) {
         this.certificateFileUrl = certificateFileUrl;
         return this;
     }

     public String getCertificateFileId() {
         return certificateFileId;
     }

     public Identification setCertificateFileId(String certificateFileId) {
         this.certificateFileId = certificateFileId;
         return this;
     }

     public String getFileId() {
         return fileId;
     }

     public Identification setFileId(String fileId) {
         this.fileId = fileId;
         return this;
     }

     public double getSimilar() {
         return similar;
     }

     public Identification setSimilar(double similar) {
         this.similar = similar;
         return this;
     }

     @Override
     public String toString() {
         return "Identification{" +
                 "identificationId=" + identificationId +
                 ", userId=" + userId +
                 ", state=" + state +
                 ", fileUrl='" + fileUrl + '\'' +
                 ", imageUrl='" + imageUrl + '\'' +
                 ", workTitle='" + workTitle + '\'' +
                 ", createTime='" + createTime + '\'' +
                 ", failTime='" + failTime + '\'' +
                 ", successTime='" + successTime + '\'' +
                 ", confirmTime='" + confirmTime + '\'' +
                 ", certificateFileUrl='" + certificateFileUrl + '\'' +
                 ", certificateFileId='" + certificateFileId + '\'' +
                 ", fileId='" + fileId + '\'' +
                 ", similar=" + similar +
                 '}';
     }
 }
