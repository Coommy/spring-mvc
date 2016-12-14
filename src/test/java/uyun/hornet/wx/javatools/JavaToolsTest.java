package uyun.hornet.wx.javatools;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import org.junit.Test;

/**
 * Created by xuht on 2016/12/12.
 */
public class JavaToolsTest {
    @Test
    public void testSendMessage() throws WxErrorException {
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId("wxd87f8411b2372d7b"); // 设置微信公众号的appid
        config.setSecret("c9cb4690a942f7c4e2c88377f87d8377"); // 设置微信公众号的app corpSecret
        config.setToken("wechat"); // 设置微信公众号的token
        config.setAesKey("63qg4f3n5CnhhUzh17kQMeGKh2l7ePVSFAOpzHseBlA"); // 设置微信公众号的EncodingAESKey

        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(config);

// 用户的openid在下面地址获得
// https://mp.weixin.qq.com/debug/cgi-bin/apiinfo?t=index&type=用户管理&form=获取关注者列表接口%20/user/get
        String openid = "oZ0vxw6YjF957JUA6w63IR0JVtCE";
        WxMpCustomMessage message = WxMpCustomMessage.TEXT().content("Hello World").toUser(openid).build();
        wxService.customMessageSend(message);
    }
}
