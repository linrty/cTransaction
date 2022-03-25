package com.linrty.ctransaction.plugin

import com.drake.net.BuildConfig
import com.drake.net.NetConfig
import com.drake.net.interceptor.RequestInterceptor
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setLog
import com.drake.net.okhttp.setRequestInterceptor
import com.drake.net.request.BaseRequest
import com.linrty.ctransaction.util.CodeUtil
import org.web3j.protocol.core.methods.response.EthCompileSolidity
import java.util.concurrent.TimeUnit

object NetUtil {
    public fun netConfig(){
        NetConfig.init(CodeUtil.BASE_URL) {
            connectTimeout(CodeUtil.CONNECT_TIME_OUT,TimeUnit.MINUTES)
            readTimeout(CodeUtil.READ_TIME_OUT,TimeUnit.MINUTES)
            writeTimeout(CodeUtil.WRITE_TIME_OUT,TimeUnit.MINUTES)

            setLog(BuildConfig.DEBUG)
            setConverter(GsonConvert())
            // 请求拦截器，用于给每个请求头加入token信息
            setRequestInterceptor(object : RequestInterceptor {
                override fun interceptor(request: BaseRequest) {
                    request.setHeader("token", "123456")
                }
            })
        }
    }
}