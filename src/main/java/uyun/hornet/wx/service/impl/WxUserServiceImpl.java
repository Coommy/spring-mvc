package uyun.hornet.wx.service.impl;

import org.springframework.stereotype.Service;
import uyun.hornet.wx.entity.WxUser;
import uyun.hornet.wx.service.WxUserService;

import java.util.Date;

/**
 * Created by xuht on 2016/12/14.
 */
@Service
public class WxUserServiceImpl implements WxUserService {
    /**
     * 根据优云用户ID删除记录
     *
     * @param userId
     */
    @Override
    public boolean delete(String userId) {
        return false;
    }

    /**
     * 根据微信用户ID查询绑定信息
     *
     * @param openId
     */
    @Override
    public WxUser queryByOpenId(String openId) {
        return null;
    }

    /**
     * 根据优云用户ID和租户ID查询绑定信息
     *
     * @param tenantId
     * @param userId
     */
    @Override
    public WxUser queryByUserId(String tenantId, String userId) {
        return null;
    }

    /**
     * 保存绑定记录
     *
     * @param wxUser
     */
    @Override
    public boolean save(WxUser wxUser) {
        return false;
    }

    /**
     * 更新过期时间
     *
     * @param expireTime
     * @param userId
     */
    @Override
    public boolean updateExpire(Date expireTime, String userId) {
        return false;
    }
}
