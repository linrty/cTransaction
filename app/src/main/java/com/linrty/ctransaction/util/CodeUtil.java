package com.linrty.ctransaction.util;
 /**
  * @ClassName:      CodeUtil
  * @Description:    用于保存一些全局需要使用到的静态常量用于区分类型
  * @Author:         Linrty
  * @CreateDate:     2022/3/23
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/23
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public interface CodeUtil {


    /**
     * 全局Log的TAG
     */
    public static final String TAG_LOG = "Linrty";

    /**
     * 全局网络相关参数
     */
    public static final String BASE_URL = "http://linrty.com/";
    /**
     * 超时相关设置，单位为分钟
     */
    public static final long CONNECT_TIME_OUT = 2;
    public static final long READ_TIME_OUT = 2;
    public static final long WRITE_TIME_OUT = 2;

    /**
     * 指向对应的Fragment页面的code编码
     */

    /**
     * index
     */
    public static final int CODE_FRAGMENT_INDEX = 100;
    public static final int CODE_FRAGMENT_INDEX_HOME = 101;
    public static final int CODE_FRAGMENT_INDEX_WORK = 102;
    public static final int CODE_FRAGMENT_INDEX_MESSAGE = 103;
    public static final int CODE_FRAGMENT_INDEX_USER = 104;

    /**
     * swap
     */
    public static final int CODE_FRAGMENT_SWAP = 200;
    public static final int CODE_FRAGMENT_SWAP_UPLOAD = 201;
    public static final int CODE_FRAGMENT_SWAP_DOWNLOAD = 202;


    /**
     * 对于多类型的RecyclerView中type参数来指定对应的type
     */

    /**
     * IndexHome的item类型
     */
    public static final int CODE_INDEX_HOME_ITEM_SMALL = 1;
    public static final int CODE_INDEX_HOME_ITEM_BIG = 2;

     // To run the sample correctly, the following variables must have valid values.
     // The endpoint value below is just the example. Please use proper value according to your region

     /**
      * 访问的endpoint地址
      */
    public static final String OSS_ENDPOINT = "http://oss-cn-shanghai.aliyuncs.com";
     /**
      * callback 测试地址
      */
    public static final String OSS_CALLBACK_URL = "http://oss-demo.aliyuncs.com:23450";
    // STS 鉴权服务器地址，使用前请参照文档 https://help.aliyun.com/document_detail/31920.html 介绍配置STS 鉴权服务器地址。
    // 或者根据工程sts_local_server目录中本地鉴权服务脚本代码启动本地STS 鉴权服务器。详情参见sts_local_server 中的脚本内容。
     /**
      * STS 地址
      */
    public static final String STS_SERVER_URL = "http://*.*.*.*:*/sts/getsts";

    public static final String BUCKET_NAME = "请输入bucket名称";
    public static final String OSS_ACCESS_KEY_ID = "*************";
    public static final String OSS_ACCESS_KEY_SECRET = "*************";

    public static final int DOWNLOAD_SUCCESS = 1;
    public static final int DOWNLOAD_Fail = 2;
    public static final int UPLOAD_SUCCESS = 3;
    public static final int UPLOAD_Fail = 4;
    public static final int UPLOAD_PROGRESS = 5;
    public static final int LIST_SUCCESS = 6;
    public static final int HEAD_SUCCESS = 7;
    public static final int RESUMABLE_SUCCESS = 8;
    public static final int SIGN_SUCCESS = 9;
    public static final int BUCKET_SUCCESS = 10;
    public static final int GET_STS_SUCCESS = 11;
    public static final int MULTIPART_SUCCESS = 12;
    public static final int STS_TOKEN_SUCCESS = 13;
    public static final int FAIL = 9999;
    public static final int REQUEST_CODE_AUTH = 10111;
    public static final int REQUEST_CODE_LOCAL_PHOTOS = 10112;
    public static final int REQUEST_CODE_OPEN_DOCUMENT = 10113;


}
