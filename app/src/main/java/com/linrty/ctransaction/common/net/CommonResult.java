package com.linrty.ctransaction.common.net;


import com.google.gson.annotations.SerializedName;

/**
 * @description: 网络请求响应统一包装类
 * @author Linrty
 * @date 2022/3/24 14:45
 * @version 1.0
 */

public class CommonResult<T> {

    /**
     * http返回码
     */
    @SerializedName(value = "code")
    private Integer code;
    /**
     * 消息
     */
    @SerializedName(value = "msg")
    private String msg;
    /**
     * 返回的具体数据
     */
    @SerializedName(value = "data")
    private T data;

    public Integer getCode() {
        return code;
    }

    public CommonResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public CommonResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public CommonResult<T> setData(T data) {
        this.data = data;
        return this;
    }

}
