package uyun.hornet.wx.javatools;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uyun.hornet.wx.config.WeixinConfigStorage;
import uyun.hornet.wx.dao.WxUserDao;
import uyun.hornet.wx.entity.WxUser;
import uyun.whale.common.mybatis.type.UUIDTypeHandler;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by xuht on 2016/12/12.
 */
public class JavaToolsTest {
    @Test
    public void testSendMessage() throws WxErrorException {
        String s = "李四";
        byte[] bytes = s.getBytes();

    }
}
