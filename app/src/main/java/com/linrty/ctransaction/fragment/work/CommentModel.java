package com.linrty.ctransaction.fragment.work;

import androidx.lifecycle.ViewModel;

import com.linrty.ctransaction.bean.Comment;

import java.util.ArrayList;
import java.util.List;

/**
  * @ClassName:      CommentModel
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/28
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/28
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class CommentModel{
    /**
     * 评论发布者用户名
     */
    private String userName;
    /**
     * 评论发布者头像
     */
    private String userAvatar;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private String date;
    /**
     * 点赞数量
     */
    private Integer upCount;
    /**
     * 用户是否点赞
     */
    private Boolean up;
    /**
     * 用户是否踩
     */
    private Boolean down;
    /**
     * 作者是否
     */
    private Boolean good;
    /**
     * 有作者参与的子评论
     */
    private Boolean commentUp;
    /**
     * 子评论数量
     */
    private Long commentCount;
    /**
     * 子评论
     */
    private List<Comment> comments;

    public List<CharSequence> nestedComment(boolean isAuthor){
        List<CharSequence> data = new ArrayList<>();

        return null;
    }
}
