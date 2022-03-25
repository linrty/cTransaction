package com.linrty.ctransaction.common.oss;

import com.alibaba.sdk.android.oss.model.PutObjectResult;

/**
  * @ClassName:      AliyunOSSCallBack
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/25
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/25
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */
public interface AliyunOSSCallBack {
     public void oSSRequestSuccess(PutObjectResult object);

     public void oSSRequestFailure(String errorCode,String msg);
}
