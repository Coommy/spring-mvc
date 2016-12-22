package uyun.hornet.wx.config;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by dff on 2016/1/8.
 */
@Component("weixinConfigStorage")
public class WeixinConfigStorage extends WxMpInMemoryConfigStorage {

    @Value("${appId}")
    private String weixin_appid;

    @Value("${secret}")
    private String weixin_secretkey;

    @Value("${token}")
    private String weixin_token;

    @Value("${aesKey}")
    private String weixin_aeskey;

    @PostConstruct
    private void init(){
        this.appId = weixin_appid;
        this.secret = weixin_secretkey;
        this.token = weixin_token;
        this.aesKey = weixin_aeskey;
    }

    public String getWeixin_appid() {
        return weixin_appid;
    }

    public void setWeixin_appid(String weixin_appid) {
        this.weixin_appid = weixin_appid;
    }

    public String getWeixin_secretkey() {
        return weixin_secretkey;
    }

    public void setWeixin_secretkey(String weixin_secretkey) {
        this.weixin_secretkey = weixin_secretkey;
    }

    public String getWeixin_token() {
        return weixin_token;
    }

    public void setWeixin_token(String weixin_token) {
        this.weixin_token = weixin_token;
    }

    public String getWeixin_aeskey() {
        return weixin_aeskey;
    }

    public void setWeixin_aeskey(String weixin_aeskey) {
        this.weixin_aeskey = weixin_aeskey;
    }
}
