package uyun.hornet.wx.service;

import uyun.hornet.wx.entity.WxUser;

import java.util.Date;

/**
 * 微信用户服务类
 * Created by xuht on 2016/12/14.
 */
public interface WxUserService {
    /**
     * 根据优云用户ID删除记录
     *
     * @param userId
     */
    boolean delete(String userId);

    /**
     * 根据微信用户ID查询绑定信息
     *
     * @param openId
     */
    WxUser queryByOpenId(String openId);

    /**
     * 根据优云用户ID和租户ID查询绑定信息
     *
     * @param tenantId
     * @param userId
     */
    WxUser queryByUserId(String tenantId, String userId);

    /**
     * 保存绑定记录
     *
     * @param wxUser
     */
    boolean save(WxUser wxUser);

    /**
     * 更新过期时间
     *
     * @param expireTime
     * @param userId
     */
    boolean updateExpire(Date expireTime, String userId);
}
