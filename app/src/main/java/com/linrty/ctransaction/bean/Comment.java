package com.linrty.ctransaction.bean;

import static com.drake.spannable.SpanUtilsKt.setSpan;

import android.graphics.Color;

import com.linrty.ctransaction.plugin.SpannableUtil;

/**
  * @ClassName:      Comment
  * @Description:    java类作用描述
  * @Author:         Linrty
  * @CreateDate:     2022/3/28
  * @UpdateUser:     updater
  * @UpdateDate:     2022/3/28
  * @UpdateRemark:   更新内容
  * @Version:        1.0
  */

public class Comment {
     private String name;
     private String content;
     /**
      * 因为需要换行所以使用Spannable来填充一个textView
      * 使用依赖库快速渲染评论内容 https://github.com/liangjingkanji/spannable
      */

     public CharSequence getSpannable(){
         return SpannableUtil.INSTANCE.getNestedCommentSpannable(name,content);
     }

    public String getName() {
        return name;
    }

    public Comment setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }
}
