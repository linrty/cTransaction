package com.linrty.ctransaction.fragment.work;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
  * @ClassName:      WorkInfoViewModel
  * @Description:    Work Info页面的数据类
  * @Author:         Linrty
  * @CreateDate:     2022/4/4
  * @UpdateUser:     updater
  * @UpdateDate:     2022/4/4
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */
public class WorkInfoViewModel extends ViewModel {

    private Long workId;



    private List<Object> commentList = new ArrayList<>();

    public List<Object> getCommentList() {
        return commentList;
    }

    public WorkInfoViewModel setCommentList(List<Object> commentList) {
        this.commentList = commentList;
        return this;
    }

    public Long getWorkId() {
        return workId;
    }

    public WorkInfoViewModel setWorkId(Long workId) {
        this.workId = workId;
        return this;
    }
}
