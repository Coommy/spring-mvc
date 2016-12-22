package uyun.hornet.wx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import uyun.hornet.wx.entity.WxUser;
import uyun.hornet.wx.dao.WxUserDao;
import uyun.hornet.wx.handler.MessageCustomHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.*;
import me.chanjar.weixin.mp.bean.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uyun.hornet.wx.logic.WxUserLogic;
import uyun.hornet.wx.service.WxUserService;
import uyun.whale.common.mybatis.type.UUIDTypeHandler;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by dff on 2016/1/7.
 * 微信接口调用入口：可在微信公众号端配置地址为  {url}/server/center
 * 说明：
 * 项目是基于weixin-java-tools的开源包开发而来，仅供参考和学习，有任何开发中的问题和修改可以联系我修改
 * 为什么写这个项目：
 * 最近在看weixin-java-tools @link https://github.com/chanjarster/weixin-java-tools/ 项目的时候，
 * 觉得挺好，虽然demo的例子挺多，但是没有针对使用web框架的例子，于是乎就动手写了个
 */
@RestController
@RequestMapping("/server")
public class MessageController {

    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    @Qualifier("weixinConfigStorage")
    private WxMpConfigStorage wxMpConfigStorage;

    @Autowired
    @Qualifier("MessageLogHandler")
    private WxMpMessageHandler logHandler;

    @Autowired
    @Qualifier("MessageTextHandler")
    private WxMpMessageHandler textHandler;

    @Autowired
    @Qualifier("MessageEventHandler")
    private WxMpMessageHandler eventHandler;

    @Autowired
    @Qualifier("MessageCustomHandler")
    private MessageCustomHandler customHandler;

    private WxMpService wxMpService;

    private WxMpMessageRouter wxMpMessageRouter;

    @Autowired
    private WxUserLogic wxUserLogic;

    @Autowired
    private WxUserService wxUserService;

    //  初始化
    @PostConstruct
    private void init() {
        wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
        wxMpMessageRouter = new WxMpMessageRouter(wxMpService);
        wxMpMessageRouter.rule().handler(logHandler).next()
                .rule().async(false).msgType(WxConsts.XML_MSG_TEXT).matcher(customHandler).handler(customHandler).end()
                .rule().msgType(WxConsts.XML_MSG_TEXT).handler(textHandler).end()
                .rule().msgType(WxConsts.XML_MSG_EVENT).handler(eventHandler).end();
    }

    @RequestMapping(value = "/center", produces = "text/html;charset=UTF-8")
    public String center(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            return "非法请求";
        }
        String echostr = request.getParameter("echostr");
        if (StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            return echostr;
        }
        String encryptType = request.getParameter("encrypt_type");
        encryptType = StringUtils.isBlank(encryptType) ? "raw" : encryptType;
        if ("raw".equals(encryptType)) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            if (outMessage != null) {
                return outMessage.toXml();
            }
        } else if ("aes".equals(encryptType)) {
            // 是aes加密的消息
            String msgSignature = request.getParameter("msg_signature");
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpConfigStorage, timestamp, nonce, msgSignature);
            WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
            return outMessage.toEncryptedXml(wxMpConfigStorage);
        }
        return "success";
    }

    @RequestMapping("/test")
    public String test() throws WxErrorException {
        //发送模板信息 测试
        WxMpTemplateMessage message = new WxMpTemplateMessage();
        List<WxMpTemplateData> datas = new ArrayList<WxMpTemplateData>() {{
            add(new WxMpTemplateData("first", "董先生", "#173177"));
            add(new WxMpTemplateData("keyword2", "2016-01-08 16:55:00", "#173177"));
        }};
        message.setDatas(datas);
        message.setTemplateId("j8lKfar6HERd1ird3i3Vt_erFVxFviGo17cmDPkkeCE");
        message.setToUser("oZ0vxw6YjF957JUA6w63IR0JVtCE");
        message.setUrl("http://www.baidu.com");
        message.setTopColor("#173177");
        return wxMpService.templateSend(message);
    }

    //@RequestMapping(value="/login",produces="application/json;charset=UTF-8")
    @RequestMapping(value = "/login", produces = "text/json;charset=UTF-8")
    public String testString(HttpServletRequest request, HttpServletResponse response) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aa", "bb");
        jsonObject.put("cc", "dd");
        String s = jsonObject.toJSONString();
        Cookie userIdCookie = new Cookie("userId", "12344");
        Cookie tenantIdCookie = new Cookie("tenantId", "23455");
        Cookie tokenCookie = new Cookie("token", "8921");
        userIdCookie.setDomain("free.natapp.cc");
        tenantIdCookie.setDomain("free.natapp.cc");
        tokenCookie.setDomain("free.natapp.cc");
        userIdCookie.setPath("/");
        tenantIdCookie.setPath("/");
        tokenCookie.setPath("/");

        response.addCookie(userIdCookie);
        response.addCookie(tenantIdCookie);
        response.addCookie(tokenCookie);
        return s;
    }

    @RequestMapping(value = "test2", produces = "text/html;charset=utf-8")
    public String test2() {

        return "成功访问到数据";
    }

    @RequestMapping(value = "/getUserInfo", produces = "text/json;charset=UTF-8")
    public String getUserInfo(HttpServletRequest request, HttpServletResponse response) throws WxErrorException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        logger.debug("code:" + code + ",state:" + state);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        logger.debug("user的openId为：" + wxMpUser.getOpenId());

        //TODO 根据openId去对照表中查询是否已经绑定。如果已经绑定，则设置cookie


        //如果没有绑定，则返回需要绑定的信息给前端。
        String result = "{\"state\":\"0\",\"result\":\"未绑定，需要绑定才能继续访问！\"}";
        return result;
    }

    @RequestMapping(value = "/getJSSignature", produces = "text/json;charset=UTF-8")
    public String getJSSignature() throws WxErrorException {
        String ticket = wxMpService.getJsapiTicket();
        logger.info("ticket:" + ticket);
        WxJsapiSignature wxJsapiSignature = wxMpService.createJsapiSignature("http://wx.free.natapp.cc:8080/");
        String str = JSON.toJSONString(wxJsapiSignature);

        return str;
    }

    @RequestMapping(value = "/test3")
    public String test3() throws WxErrorException {
        WxUser user = new WxUser();
        user.setUserId("121321");
        user.setOpenId("oZ0vxw6YjF957JUA6w63IR0JVtCE");
        user.setTenantId("321312");
        Date now = new Date();
        user.setBindingTime(now);
        user.setCreateTime(now);
        //设置超时时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR,20);
        user.setExpireTime(calendar.getTime());
        wxUserService.save(user);
        return "success";
    }

}
