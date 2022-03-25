package com.linrty.ctransaction.common.net.api

import com.drake.net.Get
import com.drake.net.Post
import com.drake.net.request.BodyRequest
import com.drake.net.request.MediaConst
import com.drake.net.utils.scopeNet
import com.google.gson.Gson
import com.linrty.ctransaction.bean.Credit
import com.linrty.ctransaction.bean.User
import com.linrty.ctransaction.common.net.CommonResult
import okhttp3.RequestBody.Companion.toRequestBody
import java.lang.reflect.Type

object UserApi {

    /**
     * 适合多个实体类作为参数时，也可以自定义一个相应的Request类
     */
    fun BodyRequest.gson(vararg body: Pair<String, Any?>) {
        this.body = Gson().toJson(body.toMap()).toRequestBody(MediaConst.JSON)
    }

    /**
     * 单个实体类作为请求参数时
     */
    fun BodyRequest.gson(body: Any){
        this.body = Gson().toJson(body).toRequestBody(MediaConst.JSON)
    }

    public fun getCredit():Credit?{
        var data: Credit? = null;
        scopeNet {
            data = Get<CommonResult<Credit>>("/user/oss_credit").await().data
        }
        return data;
    }

    public fun loginByPassword(body: Any):Boolean{
        var data:Boolean = false;
        scopeNet {
            data = Post<CommonResult<Boolean>>("/user/login_by_password"){
                gson(body)
            }.await().data
        }
        return data;
    }
}