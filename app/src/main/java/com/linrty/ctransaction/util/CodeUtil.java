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



     String ACCOUNT_CHANGE = "account_change";
     String ACCOUNT_REMOVED = "account_removed";
     String ACCOUNT_CONFLICT = "conflict";
     String ACCOUNT_FORBIDDEN = "user_forbidden";
     String ACCOUNT_KICKED_BY_CHANGE_PASSWORD = "kicked_by_change_password";
     String ACCOUNT_KICKED_BY_OTHER_DEVICE = "kicked_by_another_device";

     String EXTRA_CONFERENCE_ID = "confId";
     String EXTRA_CONFERENCE_PASS = "password";
     String EXTRA_CONFERENCE_INVITER = "inviter";
     String EXTRA_CONFERENCE_IS_CREATOR = "is_creator";
     String EXTRA_CONFERENCE_GROUP_ID = "group_id";
     String EXTRA_CONFERENCE_GROUP_EXIST_MEMBERS = "exist_members";

     String OP_INVITE = "invite";
     String OP_REQUEST_TOBE_SPEAKER = "request_tobe_speaker";
     String OP_REQUEST_TOBE_AUDIENCE = "request_tobe_audience";

     String EM_CONFERENCE_OP = "em_conference_op";
     String EM_CONFERENCE_ID = "em_conference_id";
     String EM_CONFERENCE_PASSWORD = "em_conference_password";
     String EM_CONFERENCE_TYPE = "em_conference_type";
     String EM_MEMBER_NAME = "em_member_name";
     String EM_NOTIFICATION_TYPE = "em_notification_type";

     String MSG_ATTR_CONF_ID = "conferenceId";
     String MSG_ATTR_CONF_PASS = EXTRA_CONFERENCE_PASS;
     String MSG_ATTR_EXTENSION = "msg_extension";

     String NEW_FRIENDS_USERNAME = "item_new_friends";
     String GROUP_USERNAME = "item_groups";
     String CHAT_ROOM = "item_chatroom";
     String CHAT_ROBOT = "item_robots";

     String NOTIFY_GROUP_INVITE_RECEIVE = "invite_receive";
     String NOTIFY_GROUP_INVITE_ACCEPTED = "invite_accepted";
     String NOTIFY_GROUP_INVITE_DECLINED = "invite_declined";
     String NOTIFY_GROUP_JOIN_RECEIVE = "invite_join_receive";
     String NOTIFY_CHANGE = "notify_change";

     String MESSAGE_GROUP_JOIN_ACCEPTED = "message_join_accepted";
     String MESSAGE_GROUP_AUTO_ACCEPT = "message_auto_accept";

     String CONTACT_REMOVE = "contact_remove";
     String CONTACT_ACCEPT = "contact_accept";
     String CONTACT_DECLINE = "contact_decline";
     String CONTACT_BAN = "contact_ban";
     String CONTACT_ALLOW = "contact_allow";

     String CONTACT_CHANGE = "contact_change";
     String CONTACT_ADD = "contact_add";
     String CONTACT_DELETE = "contact_delete";
     String CONTACT_UPDATE = "contact_update";
     String NICK_NAME_CHANGE = "nick_name_change";
     String AVATAR_CHANGE = "avatar_change";
     String REMOVE_BLACK = "remove_black";

     String GROUP_CHANGE = "group_change";
     String GROUP_OWNER_TRANSFER = "group_owner_transfer";
     String GROUP_SHARE_FILE_CHANGE = "group_share_file_change";

     String CHAT_ROOM_CHANGE = "chat_room_change";
     String CHAT_ROOM_DESTROY = "chat_room_destroy";

     String REFRESH_NICKNAME = "refresh_nickname";

     String CONVERSATION_DELETE = "conversation_delete";
     String CONVERSATION_READ = "conversation_read";

     String MESSAGE_NOT_SEND = "message_not_send";

     String SYSTEM_MESSAGE_FROM = "from";
     String SYSTEM_MESSAGE_REASON = "reason";
     String SYSTEM_MESSAGE_STATUS = "status";
     String SYSTEM_MESSAGE_GROUP_ID = "groupId";
     String SYSTEM_MESSAGE_NAME = "name";
     String SYSTEM_MESSAGE_INVITER = "inviter";

     String USER_CARD_EVENT = "userCard";
     String USER_CARD_ID = "uid";
     String USER_CARD_NICK = "nickname";
     String USER_CARD_AVATAR = "avatar";


}
