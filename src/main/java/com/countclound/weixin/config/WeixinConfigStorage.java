package com.countclound.weixin.config;

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

}
