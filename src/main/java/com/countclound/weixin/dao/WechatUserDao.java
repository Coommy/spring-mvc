package com.countclound.weixin.dao;

import com.countclound.weixin.bean.WechatUser;

/**
 * Created by xuht on 2016/12/13.
 */
public interface WechatUserDao {
    /**
     * 插入微信用户和优云用户的绑定信息
     * @param wechatUser
     */
    boolean insert(WechatUser wechatUser);

    /**
     * 更新微信用户和优云用户的绑定信息
     * @param wechatUser
     * @return
     */
    boolean update(WechatUser wechatUser);

    /**
     * 根据微信的openId获取微信用户和优云用户的绑定信息
     * @param openId
     * @return
     */
    WechatUser getUserByOpenId(String openId);

    /**
     * 根据优云用户ID获取微信用户和优云用户的绑定信息
     * @param userId
     * @return
     */
    WechatUser getUserByUserId(String userId);


}