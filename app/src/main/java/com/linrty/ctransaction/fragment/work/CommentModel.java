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
     * 评论者ID
     */
    private Long userId;
    /**
     * 评论ID
     */
    private Long commentId;
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

}
