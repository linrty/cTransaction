package com.linrty.ctransaction.common.oss;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.AppendObjectRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.CreateBucketRequest;
import com.alibaba.sdk.android.oss.model.DeleteBucketRequest;
import com.alibaba.sdk.android.oss.model.DeleteBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.HeadObjectRequest;
import com.alibaba.sdk.android.oss.model.HeadObjectResult;
import com.alibaba.sdk.android.oss.model.ImagePersistRequest;
import com.alibaba.sdk.android.oss.model.ImagePersistResult;
import com.alibaba.sdk.android.oss.model.ListObjectsRequest;
import com.alibaba.sdk.android.oss.model.ListObjectsResult;
import com.alibaba.sdk.android.oss.model.MultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.ResumableDownloadRequest;
import com.alibaba.sdk.android.oss.model.ResumableDownloadResult;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.alibaba.sdk.android.oss.model.TriggerCallbackRequest;
import com.alibaba.sdk.android.oss.model.TriggerCallbackResult;
import com.blankj.utilcode.util.LogUtils;
import com.linrty.ctransaction.CTransactionApp;
import com.linrty.ctransaction.bean.Credit;
import com.linrty.ctransaction.util.CodeUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mOss on 2015/12/7 0007.
 * 支持普通上传，普通下载
 */
public class OssService {

    public OSS mOss;
    /**
     * 存储库名称
     */
    private String mBucket;
    // private UIDisplayer mDisplayer;
    private String mCallbackAddress;
    private static String mResumableObjectKey = "resumableObject";

    /*public OssService(OSS oss, String bucket, UIDisplayer uiDisplayer) {
        this.mOss = oss;
        this.mBucket = bucket;
        this.mDisplayer = uiDisplayer;
    }*/

    public void setBucketName(String bucket) {
        this.mBucket = bucket;
    }

    public OssService initOss(Credit credit) {
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
        mOss = new OSSClient(CTransactionApp.context, CodeUtil.OSS_ENDPOINT, provider, conf);
        return this;
    }

    public void setCallbackAddress(String callbackAddress) {
        this.mCallbackAddress = callbackAddress;
    }


    /**
     * 简单方式的同步上传文件
     */
    public void simplePutFile(Uri fileUri, String object){
        this.simplePutFile(this.mBucket,fileUri,object);
    }

    /**
     * android10可以使用Uri上传文件
     * @param bucket
     * @param fileUri
     * @param object
     */
    public void simplePutFile(String bucket, Uri fileUri, String object){
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucket,object,fileUri);
        // 设置文件元信息。
        // ObjectMetadata metadata = new ObjectMetadata();
        // 设置Content-Type。
        // metadata.setContentType("text/plain");
        // 校验MD5。
        // metadata.setContentMD5(BinaryUtil.calculateBase64Md5(uploadFilePath));
        // put.setMetadata(metadata);
        try {
            PutObjectResult putResult = mOss.putObject(put);

            LogUtils.d("PutObject", "UploadSuccess");
            LogUtils.d("ETag", putResult.getETag());
            LogUtils.d("RequestId", putResult.getRequestId());
        } catch (ClientException e) {
            // 客户端异常，例如网络异常等。
            e.printStackTrace();
        } catch (ServiceException e) {
            // 服务端异常。
            LogUtils.e("RequestId", e.getRequestId());
            LogUtils.e("ErrorCode", e.getErrorCode());
            LogUtils.e("HostId", e.getHostId());
            LogUtils.e("RawMessage", e.getRawMessage());
        }

    }

    public void simplePutFile(String filePath,String object){
        this.simplePutFile(this.mBucket,filePath,object);
    }

    public void simplePutFile(String bucket, String filePath, String object){
        // 构造上传请求
        // 依次填写Bucket名称（例如examplebucket）、Object完整路径（例如exampledir/exampleobject.txt）和本地文件完整路径（例如/storage/emulated/0/oss/examplefile.txt）。
        // Object完整路径中不能包含Bucket名称。
        PutObjectRequest put = new PutObjectRequest(bucket,object,filePath);

        // 设置文件元信息。
        // ObjectMetadata metadata = new ObjectMetadata();
        // 设置Content-Type。
        // metadata.setContentType("application/octet-stream");
        // 校验MD5。
        // metadata.setContentMD5(BinaryUtil.calculateBase64Md5(uploadFilePath));
        // 设置Object访问权限。默认值为private，表示私有权限。
        // metadata.setHeader("x-oss-object-acl", "private");
        // 设置Object的存储类型，默认与Bucket存储类型一致。此处设置为Standard，表示标准存储类型。
        // metadata.setHeader("x-oss-storage-class", "Standard");
        // put.setMetadata(metadata);
        try {
            PutObjectResult putResult = mOss.putObject(put);

            LogUtils.d("PutObject", "UploadSuccess");
            LogUtils.d("ETag", putResult.getETag());
            LogUtils.d("RequestId", putResult.getRequestId());
            // 需要做相应的处理通知用户上传成功
        } catch (ClientException e) {
            // 客户端异常，例如网络异常等。
            e.printStackTrace();
            // 做相应的错误处理
        } catch (ServiceException e) {
            // 服务端异常。
            LogUtils.e("RequestId", e.getRequestId());
            LogUtils.e("ErrorCode", e.getErrorCode());
            LogUtils.e("HostId", e.getHostId());
            LogUtils.e("RawMessage", e.getRawMessage());

            // 做相应的错误处理
        }
    }

    /**
     * 简单方式异步上传文件
     */
    public void simpleAsyncUploadFile(String bucket,String object,String filePath){
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucket,object,filePath);
        this.simpleAsyncUploadFile(put,null,null);
    }

    public void simpleAsyncUploadFile(String bucket,String object,String filePath,OSSProgressCallback progressCallback, OSSCompletedCallback completedCallback){
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucket,object,filePath);
        this.simpleAsyncUploadFile(put,progressCallback,completedCallback);
    }

    public void simpleAsyncUploadFile(String object,String filePath,OSSProgressCallback progressCallback, OSSCompletedCallback completedCallback){
        this.simpleAsyncUploadFile(this.mBucket,object,filePath,progressCallback,completedCallback);
    }

    public void simpleAsyncUploadFile(String object,String filePath){
        this.simpleAsyncUploadFile(this.mBucket,object,filePath);
    }

    public void simpleAsyncUploadFile(String bucket,String object,Uri fileUri){
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucket,object,fileUri);
        this.simpleAsyncUploadFile(put,null,null);
    }

    public void simpleAsyncUploadFile(String bucket,String object,Uri fileUri,OSSProgressCallback progressCallback, OSSCompletedCallback completedCallback){
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucket,object,fileUri);
        this.simpleAsyncUploadFile(put,progressCallback,completedCallback);
    }

    public void simpleAsyncUploadFile(String object,Uri fileUri,OSSProgressCallback progressCallback, OSSCompletedCallback completedCallback){
        this.simpleAsyncUploadFile(this.mBucket,object,fileUri,progressCallback,completedCallback);
    }

    public void simpleAsyncUploadFile(String object,Uri fileUri){
        this.simpleAsyncUploadFile(this.mBucket,object,fileUri);
    }

    public void simpleAsyncUploadFile(PutObjectRequest put,OSSProgressCallback progressCallback, OSSCompletedCallback completedCallback){
        put.setProgressCallback(progressCallback);
        OSSAsyncTask task = mOss.asyncPutObject(put,completedCallback);
    }

    /**
     * 追加异步上传文件，指通过AppendObject方法在已上传的追加类型文件（Appendable Object）末尾直接追加内容
     */

    /**
     * 通过filePath路径来进行上上传
     * @param object
     * @param filePath
     * @param position
     * @param contentType
     * @param progressCallback
     * @param completedCallback
     */
    public void appendAsyncUploadFile(String object,String filePath,long position,String contentType,OSSProgressCallback progressCallback,OSSCompletedCallback completedCallback){
        // 构造上传请求
        AppendObjectRequest request = new AppendObjectRequest(CodeUtil.BUCKET_NAME,object,filePath);
        // 设置文件元数据
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        request.setMetadata(metadata);
        this.appendAsyncUploadFile(request,progressCallback,completedCallback);

    }

    /**
     * 通过Uri来进行上传，Android 10
     * @param object
     * @param fileUri
     * @param position
     * @param contentType
     * @param progressCallback
     * @param completedCallback
     */
    public void appendAsyncUploadFile(String object,Uri fileUri, long position, String contentType,OSSProgressCallback progressCallback,OSSCompletedCallback completedCallback){
        // 构造上传请求
        AppendObjectRequest request = new AppendObjectRequest(CodeUtil.BUCKET_NAME,object,fileUri);
        // 设置文件元数据
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        request.setMetadata(metadata);
        this.appendAsyncUploadFile(request,progressCallback,completedCallback);
    }

    public void appendAsyncUploadFile(AppendObjectRequest request,OSSProgressCallback progressCallback, OSSCompletedCallback completedCallback){
        request.setProgressCallback(progressCallback);
        OSSAsyncTask task = mOss.asyncAppendObject(request,completedCallback);
    }



    public void multipartAsyncUploadFile(){

    }
























    /**
     * 异步下载图片文件
     * @param object 图片文件在存储库的位置
     */
    public void asyncGetImage(String object) {
        final long get_start = System.currentTimeMillis();
        OSSLog.logDebug("get start");
        if ((object == null) || object.equals("")) {
            LogUtils.w("AsyncGetImage", "ObjectNull");
            return;
        }

        OSSLog.logDebug("create GetObjectRequest");
        GetObjectRequest get = new GetObjectRequest(mBucket, object);
        get.setCRC64(OSSRequest.CRC64Config.YES);
        get.setProgressListener(new OSSProgressCallback<GetObjectRequest>() {
            @Override
            public void onProgress(GetObjectRequest request, long currentSize, long totalSize) {
                LogUtils.d("GetObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                int progress = (int) (100 * currentSize / totalSize);
                // mDisplayer.updateProgress(progress);
                // mDisplayer.displayInfo("下载进度: " + String.valueOf(progress) + "%");
            }
        });
        OSSLog.logDebug("asyncGetObject");
        OSSAsyncTask task = mOss.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
            @Override
            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                // 请求成功
                InputStream inputStream = result.getObjectContent();
                //Bitmap bm = BitmapFactory.decodeStream(inputStream);
                try {
                    //需要根据对应的View大小来自适应缩放
                    // Bitmap bm = mDisplayer.autoResizeFromStream(inputStream);
                    long get_end = System.currentTimeMillis();
                    OSSLog.logDebug("get cost: " + (get_end - get_start) / 1000f);
                    //mDisplayer.downloadComplete(bm);
                    //mDisplayer.displayInfo("Bucket: " + mBucket + "\nObject: " + request.getObjectKey() + "\nRequestId: " + result.getRequestId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(GetObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                String info = "";
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                    info = clientExcepion.toString();
                }
                if (serviceException != null) {
                    // 服务异常
                    LogUtils.e("ErrorCode", serviceException.getErrorCode());
                    LogUtils.e("RequestId", serviceException.getRequestId());
                    LogUtils.e("HostId", serviceException.getHostId());
                    LogUtils.e("RawMessage", serviceException.getRawMessage());
                    info = serviceException.toString();
                }
                // mDisplayer.downloadFail(info);
                // mDisplayer.displayInfo(info);
            }
        });
    }


    /**
     * 异步上传图片文件
     * @param object  图片文件在存储库的位置
     * @param localFile 本地图片位置
     */
    public void asyncPutImage(String object, String localFile) {
        final long upload_start = System.currentTimeMillis();
        OSSLog.logDebug("upload start");

        if (object.equals("")) {
            LogUtils.w("AsyncPutImage", "ObjectNull");
            return;
        }

        File file = new File(localFile);
        if (!file.exists()) {
            LogUtils.w("AsyncPutImage", "FileNotExist");
            LogUtils.w("LocalFile", localFile);
            return;
        }

        // 构造上传请求
        OSSLog.logDebug("create PutObjectRequest ");
        PutObjectRequest put = new PutObjectRequest(mBucket, object, localFile);
        put.setCRC64(OSSRequest.CRC64Config.YES);
        if (mCallbackAddress != null) {
            // 传入对应的上传回调参数，这里默认使用OSS提供的公共测试回调服务器地址
            put.setCallbackParam(new HashMap<String, String>() {
                {
                    put("callbackUrl", mCallbackAddress);
                    //callbackBody可以自定义传入的信息
                    put("callbackBody", "filename=${object}");
                }
            });
        }

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                LogUtils.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                int progress = (int) (100 * currentSize / totalSize);
                //mDisplayer.updateProgress(progress);
                //mDisplayer.displayInfo("上传进度: " + String.valueOf(progress) + "%");
            }
        });

        OSSLog.logDebug(" asyncPutObject ");
        OSSAsyncTask task = mOss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                LogUtils.d("PutObject", "UploadSuccess");

                LogUtils.d("ETag", result.getETag());
                LogUtils.d("RequestId", result.getRequestId());

                long upload_end = System.currentTimeMillis();
                OSSLog.logDebug("upload cost: " + (upload_end - upload_start) / 1000f);
                //mDisplayer.uploadComplete();
                /*mDisplayer.displayInfo("Bucket: " + mBucket
                        + "\nObject: " + request.getObjectKey()
                        + "\nETag: " + result.getETag()
                        + "\nRequestId: " + result.getRequestId()
                        + "\nCallback: " + result.getServerCallbackReturnBody());*/
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                String info = "";
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                    info = clientExcepion.toString();
                }
                if (serviceException != null) {
                    // 服务异常
                    LogUtils.e("ErrorCode", serviceException.getErrorCode());
                    LogUtils.e("RequestId", serviceException.getRequestId());
                    LogUtils.e("HostId", serviceException.getHostId());
                    LogUtils.e("RawMessage", serviceException.getRawMessage());
                    info = serviceException.toString();
                }
                //mDisplayer.uploadFail(info);
                //mDisplayer.displayInfo(info);
            }
        });
    }

    /**
     * 异步下载指定前缀的所有文件
     */
    public void asyncListObjectsWithBucketName() {
        ListObjectsRequest listObjects = new ListObjectsRequest(mBucket);
        // Sets the prefix
        listObjects.setPrefix("android");
        listObjects.setDelimiter("/");
        // Sets the success and failure callback. calls the Async API
        OSSAsyncTask task = mOss.asyncListObjects(listObjects, new OSSCompletedCallback<ListObjectsRequest, ListObjectsResult>() {
            @Override
            public void onSuccess(ListObjectsRequest request, ListObjectsResult result) {
                String info = "";
                OSSLog.logDebug("AyncListObjects", "Success!");
                for (int i = 0; i < result.getObjectSummaries().size(); i++) {
                    info += "\n" + String.format("object: %s %s %s", result.getObjectSummaries().get(i).getKey(), result.getObjectSummaries().get(i).getETag(), result.getObjectSummaries().get(i).getLastModified().toString());
                    OSSLog.logDebug("AyncListObjects", info);
                }
                //mDisplayer.displayInfo(info);
            }

            @Override
            public void onFailure(ListObjectsRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // request exception
                if (clientExcepion != null) {
                    // client side exception such as network exception
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // service side exception.
                    OSSLog.logError("ErrorCode", serviceException.getErrorCode());
                    OSSLog.logError("RequestId", serviceException.getRequestId());
                    OSSLog.logError("HostId", serviceException.getHostId());
                    OSSLog.logError("RawMessage", serviceException.getRawMessage());
                }
                //mDisplayer.downloadFail("Failed!");
                //mDisplayer.displayInfo(serviceException.toString());
            }
        });
    }

    // Gets file's metadata
    public void headObject(String objectKey) {
        // Creates a request to get the file's metadata
        HeadObjectRequest head = new HeadObjectRequest(mBucket, objectKey);

        OSSAsyncTask task = mOss.asyncHeadObject(head, new OSSCompletedCallback<HeadObjectRequest, HeadObjectResult>() {
            @Override
            public void onSuccess(HeadObjectRequest request, HeadObjectResult result) {
                OSSLog.logDebug("headObject", "object Size: " + result.getMetadata().getContentLength());
                OSSLog.logDebug("headObject", "object Content Type: " + result.getMetadata().getContentType());

                //mDisplayer.displayInfo(result.toString());
            }

            @Override
            public void onFailure(HeadObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // request exception
                if (clientExcepion != null) {
                    // client side exception,  such as network exception
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // service side exception
                    OSSLog.logError("ErrorCode", serviceException.getErrorCode());
                    OSSLog.logError("RequestId", serviceException.getRequestId());
                    OSSLog.logError("HostId", serviceException.getHostId());
                    OSSLog.logError("RawMessage", serviceException.getRawMessage());
                }
                //mDisplayer.downloadFail("Failed!");
                //mDisplayer.displayInfo(serviceException.toString());
            }
        });
    }

    /**
     * 异步方式分片上传文件
     * @param uploadKey  存储位置
     * @param uploadFilePath  本地文件路径
     */
    public void asyncMultipartUpload(String uploadKey, Uri uploadFilePath) {

        MultipartUploadRequest request = new MultipartUploadRequest(mBucket, uploadKey,
                uploadFilePath);
        request.setCRC64(OSSRequest.CRC64Config.YES);
        request.setProgressCallback(new OSSProgressCallback<MultipartUploadRequest>() {

            @Override
            public void onProgress(MultipartUploadRequest request, long currentSize, long totalSize) {
                OSSLog.logDebug("[testMultipartUpload] - " + currentSize + " " + totalSize, false);
                int progress = (int) (100 * currentSize / totalSize);
                //mDisplayer.updateProgress(progress);
                //mDisplayer.displayInfo("上传进度: " + String.valueOf(progress) + "%");
            }
        });
        mOss.asyncMultipartUpload(request, new OSSCompletedCallback<MultipartUploadRequest, CompleteMultipartUploadResult>() {
            @Override
            public void onSuccess(MultipartUploadRequest request, CompleteMultipartUploadResult result) {
                //mDisplayer.uploadComplete();
                //mDisplayer.displayInfo(request.toString());
            }

            @Override
            public void onFailure(MultipartUploadRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    //mDisplayer.displayInfo(clientException.toString());
                } else if (serviceException != null) {
                    //mDisplayer.displayInfo(serviceException.toString());
                }

            }
        });
    }

    /**
     * 异步断点续传的方式上传文件
     * @param resumableFilePath
     */
    public void asyncResumableUpload(String resumableFilePath) {
        ResumableUploadRequest request = new ResumableUploadRequest(mBucket, mResumableObjectKey, resumableFilePath);
        request.setProgressCallback(new OSSProgressCallback<ResumableUploadRequest>() {
            @Override
            public void onProgress(ResumableUploadRequest request, long currentSize, long totalSize) {
                LogUtils.d("GetObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                int progress = (int) (100 * currentSize / totalSize);
                //mDisplayer.updateProgress(progress);
                //mDisplayer.displayInfo("上传进度: " + String.valueOf(progress) + "%");
            }
        });
        OSSAsyncTask task = mOss.asyncResumableUpload(request, new OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult>() {
            @Override
            public void onSuccess(ResumableUploadRequest request, ResumableUploadResult result) {
                //mDisplayer.uploadComplete();
                //mDisplayer.displayInfo(request.toString());
            }

            @Override
            public void onFailure(ResumableUploadRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    //mDisplayer.displayInfo(clientException.toString());
                } else if (serviceException != null) {
                    //mDisplayer.displayInfo(serviceException.toString());
                }
            }
        });
    }

    /**
     * 断电续传的方式下载文件
     * @param downloadPath  下载文件在存储库的位置
     */
    public void asyncResumableDownload(String downloadPath) {
        Map<String, String> header = new HashMap<>();
        ResumableDownloadRequest request = new ResumableDownloadRequest(CodeUtil.BUCKET_NAME, "landscape-painting1.jpeg", downloadPath + "/landscape-painting.jpeg");
        request.setEnableCheckPoint(true);
//        request.setRange(new Range(0, 1024 * 1024));
        request.setPartSize(100 * 1024);
        request.setRequestHeader(header);
        request.setCheckPointFilePath(downloadPath);
        request.setCRC64(OSSRequest.CRC64Config.YES);
        request.setProgressListener(new OSSProgressCallback() {
            @Override
            public void onProgress(Object request, long currentSize, long totalSize) {
                LogUtils.i("MultipartDownload", "currentSize: " + currentSize + ", totalSize: " + totalSize);
                if (totalSize == 0) {
                    return;
                }
                int progress = (int) (100 * currentSize / totalSize);
                //mDisplayer.updateProgress(progress);
                //displayInfo("上传进度: " + String.valueOf(progress) + "%");
            }
        });
        final long start = System.currentTimeMillis();
        final OSSAsyncTask task = mOss.asyncResumableDownload(request, new OSSCompletedCallback<ResumableDownloadRequest, ResumableDownloadResult>() {
            @Override
            public void onSuccess(ResumableDownloadRequest request, ResumableDownloadResult result) {
                LogUtils.i("MultipartDownload", result.getMetadata().toString());
                long time = System.currentTimeMillis() - start;
                LogUtils.i("time", time + "");
            }

            @Override
            public void onFailure(ResumableDownloadRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    clientException.printStackTrace();
                    LogUtils.i("clientException", "clientException");
                } else if (serviceException != null) {
                    LogUtils.i("serviceException", "serviceException");
                    serviceException.printStackTrace();
                }
            }
        });
    }


    /**
     * 如果存储库是私有的，则访问需要签名URL。过期时间在签名URL中指定.
     * @param objectKey
     */
    public void presignURLWithBucketAndKey(final String objectKey) {
        if (objectKey == null || objectKey == "") {
            //mDisplayer.displayInfo("Please input objectKey!");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Gets the signed url, the expiration time is 5 minute
                    String url = mOss.presignConstrainedObjectURL(mBucket, objectKey, 5 * 60);
                    OSSLog.logDebug("signContrainedURL", "get url: " + url);
                    // 访问该url
                    Request request = new Request.Builder().url(url).build();
                    Response resp = null;

                    resp = new OkHttpClient().newCall(request).execute();

                    if (resp.code() == 200) {
                        OSSLog.logDebug("signContrainedURL", "object size: " + resp.body().contentLength());
                        //mDisplayer.displayInfo(resp.toString());
                    } else {
                        OSSLog.logDebug("signContrainedURL", "get object failed, error code: " + resp.code()
                                + "error message: " + resp.message());
                        //mDisplayer.displayInfo(resp.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    //mDisplayer.displayInfo(e.toString());
                } catch (ClientException e) {
                    e.printStackTrace();
                    //mDisplayer.displayInfo(e.toString());
                }
            }
        }).start();
    }

    /**
     * 删除一个非空的存储库
     * 创建一个存储库并向里面添加文件
     * 试图去删除这个存储库，结果会是失败的
     * 然后清空里面的文件后再次尝试
     */
    public void deleteNotEmptyBucket(final String bucket, final String filePath) {
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
        // 创建bucket
        try {
            mOss.createBucket(createBucketRequest);
        } catch (ClientException clientException) {
            clientException.printStackTrace();
            //mDisplayer.displayInfo(clientException.toString());
        } catch (ServiceException serviceException) {
            serviceException.printStackTrace();
            //mDisplayer.displayInfo(serviceException.toString());
        }

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, "test-file", filePath);
        try {
            mOss.putObject(putObjectRequest);
        } catch (ClientException clientException) {
            clientException.printStackTrace();
        } catch (ServiceException serviceException) {
            serviceException.printStackTrace();
        }
        final DeleteBucketRequest deleteBucketRequest = new DeleteBucketRequest(bucket);
        OSSAsyncTask deleteBucketTask = mOss.asyncDeleteBucket(deleteBucketRequest, new OSSCompletedCallback<DeleteBucketRequest, DeleteBucketResult>() {
            @Override
            public void onSuccess(DeleteBucketRequest request, DeleteBucketResult result) {
                OSSLog.logDebug("DeleteBucket", "Success!");
                //mDisplayer.displayInfo(result.toString());
            }

            @Override
            public void onFailure(DeleteBucketRequest request, ClientException clientException, ServiceException serviceException) {
                // request exception
                if (clientException != null) {
                    // client side exception,  such as network exception
                    clientException.printStackTrace();
                    //mDisplayer.displayInfo(clientException.toString());
                }
                if (serviceException != null) {
                    // The bucket to delete is not empty.
                    if (serviceException.getStatusCode() == 409) {
                        // Delete files
                        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, "test-file");
                        try {
                            mOss.deleteObject(deleteObjectRequest);
                        } catch (ClientException clientexception) {
                            clientexception.printStackTrace();
                        } catch (ServiceException serviceexception) {
                            serviceexception.printStackTrace();
                        }
                        // Delete bucket again
                        DeleteBucketRequest deleteBucketRequest1 = new DeleteBucketRequest(bucket);
                        try {
                            mOss.deleteBucket(deleteBucketRequest1);
                        } catch (ClientException clientexception) {
                            clientexception.printStackTrace();
                            //mDisplayer.displayInfo(clientexception.toString());
                            return;
                        } catch (ServiceException serviceexception) {
                            serviceexception.printStackTrace();
                            //mDisplayer.displayInfo(serviceexception.toString());
                            return;
                        }
                        OSSLog.logDebug("DeleteBucket", "Success!");
                        //mDisplayer.displayInfo("The Operation of Deleting Bucket is successed!");
                    }
                }
            }
        });
    }

    public void customSign(Context ctx, String objectKey) {
        OSSCustomSignerCredentialProvider provider = new OSSCustomSignerCredentialProvider() {
            @Override
            public String signContent(String content) {

                // 此处本应该是客户端将contentString发送到自己的业务服务器,然后由业务服务器返回签名后的content。关于在业务服务器实现签名算法
                // 详情请查看http://help.aliyun.com/document_detail/oss/api-reference/access-control/signature-header.html。客户端
                // 的签名算法实现请参考OSSUtils.sign(accessKey,screctKey,content)

                String signedString = OSSUtils.sign(CodeUtil.OSS_ACCESS_KEY_ID, CodeUtil.OSS_ACCESS_KEY_SECRET, content);
                return signedString;
            }
        };

        GetObjectRequest get = new GetObjectRequest(mBucket, objectKey);
        get.setCRC64(OSSRequest.CRC64Config.YES);
        get.setProgressListener(new OSSProgressCallback<GetObjectRequest>() {
            @Override
            public void onProgress(GetObjectRequest request, long currentSize, long totalSize) {
                LogUtils.d("GetObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                int progress = (int) (100 * currentSize / totalSize);
                //mDisplayer.updateProgress(progress);
                //mDisplayer.displayInfo("下载进度: " + String.valueOf(progress) + "%");
            }
        });
        OSSAsyncTask task = mOss.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
            @Override
            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                //mDisplayer.displayInfo("使用自签名获取网络对象成功！");
            }

            @Override
            public void onFailure(GetObjectRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    //mDisplayer.displayInfo(clientException.toString());
                } else if (serviceException != null) {
                    //mDisplayer.displayInfo(serviceException.toString());
                }
            }
        });
    }

    public void triggerCallback(Context ctx, String endpoint) {
        OSSPlainTextAKSKCredentialProvider provider = new OSSPlainTextAKSKCredentialProvider("AK", "SK");
        OSSClient tClient = new OSSClient(ctx, endpoint, provider);

        Map<String, String> callbackParams = new HashMap<String, String>();
        callbackParams.put("callbackUrl", "callbackURL");
        callbackParams.put("callbackBody", "callbackBody");

        Map<String, String> callbackVars = new HashMap<String, String>();
        callbackVars.put("key1", "value1");
        callbackVars.put("key2", "value2");

        TriggerCallbackRequest request = new TriggerCallbackRequest("bucketName", "objectKey", callbackParams, callbackVars);

        OSSAsyncTask task = tClient.asyncTriggerCallback(request, new OSSCompletedCallback<TriggerCallbackRequest, TriggerCallbackResult>() {
            @Override
            public void onSuccess(TriggerCallbackRequest request, TriggerCallbackResult result) {
                //mDisplayer.displayInfo(result.getServerCallbackReturnBody());
            }

            @Override
            public void onFailure(TriggerCallbackRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    //mDisplayer.displayInfo(clientException.toString());
                } else if (serviceException != null) {
                    //mDisplayer.displayInfo(serviceException.toString());
                }
            }
        });

    }

    public void imagePersist(String fromBucket, String fromObjectKey, String toBucket, String toObjectkey, String action){

        ImagePersistRequest request = new ImagePersistRequest(fromBucket,fromObjectKey,toBucket,toObjectkey,action);

        OSSAsyncTask task = mOss.asyncImagePersist(request, new OSSCompletedCallback<ImagePersistRequest, ImagePersistResult>() {
            @Override
            public void onSuccess(ImagePersistRequest request, ImagePersistResult result) {
//                mDisplayer.displayInfo(result.getServerCallbackReturnBody());
            }

            @Override
            public void onFailure(ImagePersistRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    //mDisplayer.displayInfo(clientException.toString());
                } else if (serviceException != null) {
                    //mDisplayer.displayInfo(serviceException.toString());
                }
            }
        });
    }
}