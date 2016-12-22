package uyun.hornet.wx.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uyun.hornet.wx.entity.WxUser;
import uyun.whale.common.mybatis.type.UUIDTypeHandler;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xuht on 2016/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:applicationContext.xml")
public class WxUserDaoTest {

    @Resource
    private WxUserDao wxUserDao;//误报错误

    private String userId;

    private String tenant_id;

    @Before
    public void init(){

        userId = UUIDTypeHandler.createUUID();
        tenant_id = UUIDTypeHandler.createUUID();
    }



    @Test
    public void testDelete(){
        String userId = "6D0703FEB8C54EB28DB4111FF1766FA1";
        boolean result = wxUserDao.delete(userId);
        System.out.println(result);
    }

    @Test
    public void testQueryByOpenId(){
        String openId = "8b92d57d8eb84228bb414e9577dbeb26";
        WxUser wxUser = wxUserDao.queryByOpenId(openId);
        System.out.println(wxUser.getUserId());
    }

    @Test
    public void testQueryByUserId(){
        String userId = "82EE039000DB47B8BC1CB72CBC3B4D42";
        String tenantId = "A551F4167C3E422E8CAB4E03F7EE3790";
        WxUser wxUser = wxUserDao.queryByUserId(userId,tenantId);
        System.out.println(wxUser.getCreateTime());
    }

    @Test
    public void testSave(){
        WxUser user = new WxUser();
        user.setUserId(userId);
        user.setOpenId(UUIDTypeHandler.createUUID());
        user.setTenantId(tenant_id);
        Date now = new Date();
        user.setBindingTime(now);
        user.setCreateTime(now);
        //设置超时时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR,20);
        user.setExpireTime(calendar.getTime());
        wxUserDao.save(user);
    }

    @Test
    public void testUpdateExpire(){
        String userId = "82EE039000DB47B8BC1CB72CBC3B4D42";
        Date expireTime = new Date();
        wxUserDao.updateExpire(expireTime,userId);
    }



}
