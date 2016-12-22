package uyun.hornet.wx.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uyun.hornet.wx.entity.WxUser;
import uyun.hornet.wx.error.WxUserException;
import uyun.hornet.wx.logic.WxUserLogic;
import uyun.hornet.wx.service.WxUserService;

import java.util.Date;

/**
 * Created by xuht on 2016/12/14.
 */
@Service
public class WxUserServiceImpl implements WxUserService {
    @Autowired
    private WxUserLogic wxUserLogic;

    /**
     * 根据优云用户ID删除记录
     *
     * @param userId
     */
    @Override
    public boolean delete(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new WxUserException("删除失败，优云用户ID不能为空");
        }
        
        return wxUserLogic.delete(userId);
    }

    /**
     * 根据微信用户ID查询绑定信息
     *
     * @param openId
     */
    @Override
    public WxUser queryByOpenId(String openId) {
        return wxUserLogic.queryByOpenId(openId);
    }

    /**
     * 根据优云用户ID和租户ID查询绑定信息
     *
     * @param tenantId
     * @param userId
     */
    @Override
    public WxUser queryByUserId(String tenantId, String userId) {
        return wxUserLogic.queryByUserId(tenantId, userId);
    }

    /**
     * 保存绑定记录
     *
     * @param wxUser
     */
    @Override
    public boolean save(WxUser wxUser) {
        return wxUserLogic.save(wxUser);
    }

    /**
     * 更新过期时间
     *
     * @param expireTime
     * @param userId
     */
    @Override
    public boolean updateExpire(Date expireTime, String userId) {
        return wxUserLogic.updateExpire(expireTime, userId);
    }
}
