package com.linrty.ctransaction.fragment.index;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

/**
  * @ClassName:      IndexViewModel
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/9
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/9
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class IndexViewModel extends AndroidViewModel {


    /**
     * 会话Fragment具体参数
     */

    /**
     * 会话ID
     */
    private String conversationId;

    /**
     * 会话类型
     */
    private int chatType;

    /**
     * (可选参数)消息id，用于查询历史记录时的定位消息id
     */
    private String historyMsgId;
    /**
     * (可选参数)是否开启漫游，布尔类型，用于标记是否优先从服务器拉取消息，默认不开启
     */
    private boolean isRoam;

    public IndexViewModel(@NonNull Application application) {
        super(application);
    }

    public String getConversationId() {
        return conversationId;
    }

    public IndexViewModel setConversationId(String conversationId) {
        this.conversationId = conversationId;
        return this;
    }

    public int getChatType() {
        return chatType;
    }

    public IndexViewModel setChatType(int chatType) {
        this.chatType = chatType;
        return this;
    }

    public String getHistoryMsgId() {
        return historyMsgId;
    }

    public IndexViewModel setHistoryMsgId(String historyMsgId) {
        this.historyMsgId = historyMsgId;
        return this;
    }

    public boolean isRoam() {
        return isRoam;
    }

    public IndexViewModel setRoam(boolean roam) {
        isRoam = roam;
        return this;
    }
}
