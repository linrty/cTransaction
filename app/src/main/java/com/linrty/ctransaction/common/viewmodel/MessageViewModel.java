package com.linrty.ctransaction.common.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.hyphenate.easeui.model.EaseEvent;
import com.linrty.ctransaction.common.livedatas.LiveDataBus;

/**
  * @ClassName:      MessageViewModel
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/23
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/23
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */


public class MessageViewModel extends AndroidViewModel {
    private LiveDataBus messageObservable;

    public MessageViewModel(@NonNull Application application) {
        super(application);
        messageObservable = LiveDataBus.get();
    }

    public void setMessageChange(EaseEvent change) {
        messageObservable.with(change.event).postValue(change);
    }

    public LiveDataBus getMessageChange() {
        return messageObservable;
    }

}
