package uyun.hornet.wx.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uyun.hornet.wx.dao.WxUserDao;
import uyun.hornet.wx.entity.WxUser;

import java.util.Date;

/**
 * Created by xuht on 2016/12/14.
 */
@Service
public class WxUserLogic {

    @Autowired
    private WxUserDao wxUserDao;

    /**
     * 根据优云用户ID删除记录
     *
     * @param userId
     */
    public boolean delete(String userId) {

        return wxUserDao.delete(userId);
    }

    /**
     * 根据微信用户ID查询绑定信息
     *
     * @param openId
     */
    public WxUser queryByOpenId(String openId) {
        WxUser user = wxUserDao.queryByOpenId(openId);
        return user;
    }

    /**
     * 根据优云用户ID和租户ID查询绑定信息
     *
     * @param tenantId
     * @param userId
     */
    public WxUser queryByUserId(String tenantId, String userId) {
        WxUser wxUser =  wxUserDao.queryByUserId(tenantId,userId);
        return wxUser;
    }

    /**
     * 保存绑定记录
     *
     * @param wxUser
     */
    public boolean save(WxUser wxUser) {
        return wxUserDao.save(wxUser);
    }

    /**
     * 更新过期时间
     *
     * @param expireTime
     * @param userId
     */
    public boolean updateExpire(Date expireTime, String userId) {

        return wxUserDao.updateExpire(expireTime,userId);
    }
}
