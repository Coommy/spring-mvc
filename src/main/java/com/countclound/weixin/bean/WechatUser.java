package com.countclound.weixin.bean;

import java.sql.Date;

/**
 * 微信用户、优云账号绑定信息实体类
 * Created by xuht on 2016/12/13.
 */
public class WechatUser {
    private String id;
    //微信用户ID
    private String openId;
    //优云用户ID
    private String userId;
    //优云租户ID
    private String tenantId;
    //优云用户名称
    private String username;
    //cookie生成时间
    private Date cookieCreateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCookieCreateTime() {
        return cookieCreateTime;
    }

    public void setCookieCreateTime(Date cookieCreateTime) {
        this.cookieCreateTime = cookieCreateTime;
    }
}
