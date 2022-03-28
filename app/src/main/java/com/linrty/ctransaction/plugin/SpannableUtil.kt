package com.linrty.ctransaction.plugin

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.drake.spannable.addSpan
import com.drake.spannable.replaceSpan
import com.drake.spannable.setSpan

object SpannableUtil {
    /**
     * 因为需要换行所以使用Spannable来填充一个textView
     * 使用依赖库快速渲染评论内容 https://github.com/liangjingkanji/spannable
     */
    fun getNestedCommentSpannable(name: String,content: String): CharSequence {
        return name.setSpan(ForegroundColorSpan(Color.parseColor("#3b87bf"))).addSpan(": $content").replaceSpan("@[^@]+?(?=\\s|\$)".toRegex()) {
            ForegroundColorSpan(Color.parseColor("#3b87bf"))
        }
    }
}