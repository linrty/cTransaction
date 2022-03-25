package com.linrty.ctransaction.bean;

import com.google.gson.annotations.SerializedName;


/**
 * @PackageName: com.linrty.ctransaction.bean
 * @ClassName: User
 * @Description: TODO
 * @author: Linrty
 * @date: 2022/3/24 17:22
 */
public class User {

    /**
     * 用户主键，用户ID
     */
    @SerializedName(value = "user_id")
    private Long userId;

    /**
     * 用户手机号码，唯一
     */
    @SerializedName(value = "phone")
    private String phone;

    /**
     * 用户名
     */
    @SerializedName(value = "user_name")
    private String userName;

    /**
     * 用户登录密码
     */
    @SerializedName(value = "password")
    private String password;

    public Long getUserId() {
        return userId;
    }

    public User setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}
