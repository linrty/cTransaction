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
     * 指向对应的Fragment页面的code编码
     */

    /**
     * index
      */
    int CODE_FRAGMENT_INDEX = 100;
    int CODE_FRAGMENT_INDEX_HOME = 101;
    int CODE_FRAGMENT_INDEX_WORK = 102;
    int CODE_FRAGMENT_INDEX_MESSAGE = 103;
    int CODE_FRAGMENT_INDEX_USER = 104;

    /**
     * swap
     */
    int CODE_FRAGMENT_SWAP = 200;
    int CODE_FRAGMENT_SWAP_UPLOAD = 201;
    int CODE_FRAGMENT_SWAP_DOWNLOAD = 202;


    /**
     * 对于多类型的RecyclerView中type参数来指定对应的type
     */

    /**
     * IndexHome的item类型
     */
    int CODE_INDEX_HOME_ITEM_SMALL = 1;
    int CODE_INDEX_HOME_ITEM_BIG = 2;


}
