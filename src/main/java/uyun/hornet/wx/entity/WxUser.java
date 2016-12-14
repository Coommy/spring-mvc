package uyun.hornet.wx.entity;


import java.util.Date;

/**
 * 微信用户、优云账号绑定信息实体类
 * Created by xuht on 2016/12/13.
 */
public class WxUser {

    //微信用户ID
    private String openId;
    //优云用户ID
    private String userId;
    //优云租户ID
    private String tenantId;
    //微信用户昵称
    private String nickname;
    //绑定时间
    private Date bindingTime;
    //cookie失效时间
    private Date expireTime;
    //记录创建时间
    private Date createTime;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBindingTime() {
        return bindingTime;
    }

    public void setBindingTime(Date bindingTime) {
        this.bindingTime = bindingTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
