package com.linrty.ctransaction.common.oss;

import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.linrty.ctransaction.CTransactionApp;
import com.linrty.ctransaction.bean.Credit;

/**
  * @ClassName:      AliyunOSSUtils
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/25
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/25
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

 public class AliyunOSSUtils {

    /**
     * 访问的endpoint地址
     */
    public static final String OSS_ENDPOINT = "http://oss-cn-zhangjiakou.aliyuncs.com";
    public static final String BUCKET_NAME = "test";


    private static OSS oss;

    protected AliyunOSSCallBack ossCallBack;
    private OSSAsyncTask task;

    public void setOssToken(Credit credit) {
        OSSCredentialProvider provider = new OSSStsTokenCredentialProvider(credit.getAccessKeyId(), credit.getAccessKeySecret(), credit.getSecurityToken());
        ClientConfiguration conf = new ClientConfiguration();
        // 连接超时，默认15秒
        conf.setConnectionTimeout(15 * 1000);
        // socket超时，默认15秒
        conf.setSocketTimeout(15 * 1000);
        // 最大并发请求书，默认5个
        conf.setMaxConcurrentRequest(5);
        // 失败后最大重试次数，默认2次
        conf.setMaxErrorRetry(2);
        oss = new OSSClient(CTransactionApp.context, OSS_ENDPOINT, provider, conf);
    }

    /**
     * 同步上传单个文件
     *
     * @param name
     * @param localPath
     */
    public void uploadFile(String name, String localPath, final AliyunOSSCallBack callBack) {

        ossCallBack = callBack;
        // 构造上传请求。
        PutObjectRequest put = new PutObjectRequest(BUCKET_NAME, name, localPath);
        try {
            PutObjectResult putResult = oss.putObject(put);
            ossCallBack.oSSRequestSuccess(putResult);
        } catch (ClientException e) {
            // 本地异常，如网络异常等。
            e.printStackTrace();
            ossCallBack.oSSRequestFailure("", e.getMessage());
        } catch (ServiceException e) {
            // 服务异常。
            ossCallBack.oSSRequestFailure(e.getErrorCode(), e.getRawMessage());
        }
    }

    /**
     * 异步上传单个文件
     *
     * @param name
     * @param localPath
     */
    public void asynploadFile(String name, String localPath) {
        // 构造上传请求。
        PutObjectRequest put = new PutObjectRequest(BUCKET_NAME, name, localPath);
        // 异步上传时可以设置进度回调。
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);

            }
        });

        task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
                ossCallBack.oSSRequestSuccess(result);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // Request exception
                if (clientExcepion != null) {
                    // Local exception, such as a network exception
                    clientExcepion.printStackTrace();
                    ossCallBack.oSSRequestFailure("", clientExcepion.getMessage());
                }
                if (serviceException != null) {
                    ossCallBack.oSSRequestFailure(serviceException.getErrorCode(), serviceException.getRawMessage());
                }
            }
        });
        // task.cancel(); // Cancel the task
        // task.waitUntilFinished(); // Wait till the task is finished
    }

    /**
     * 同步下载文件
     */


    /**
     * 异步下载文件
     */



    /**
     * 取消异步请求
     *
     * @param
     */
    public void asyncCancel() {
        if (task != null && task.isCanceled()) {
            task.cancel();
        }
    }
}
