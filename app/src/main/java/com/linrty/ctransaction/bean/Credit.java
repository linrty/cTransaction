package com.linrty.ctransaction.bean;

import com.google.gson.annotations.SerializedName;


/**
 * @PackageName: com.linrty.ctransaction.bean
 * @ClassName: Credit
 * @Description: 临时文件访问凭证
 * @author: Linrty
 * @date: 2022/3/25 17:22
 */
public class Credit {
    /**
     *
     */
    @SerializedName(value = "expiration")
    private String expiration;

    /**
     *
     */
    @SerializedName(value = "access_key_id")
    private String accessKeyId;

    /**
     *
     */
    @SerializedName(value = "access_key_secret")
    private String accessKeySecret;

    /**
     *
     */
    @SerializedName(value = "security_token")
    private String securityToken;

    /**
     *
     */
    @SerializedName(value = "request_id")
    private String requestId;

    public String getExpiration() {
        return expiration;
    }

    public Credit setExpiration(String expiration) {
        this.expiration = expiration;
        return this;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public Credit setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
        return this;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public Credit setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
        return this;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public Credit setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public Credit setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }
}
