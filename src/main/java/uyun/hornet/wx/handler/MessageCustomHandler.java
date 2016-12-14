package uyun.hornet.wx.handler;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageMatcher;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by dff on 2016/1/8.
 * 客服服务
 */
@Service("MessageCustomHandler")
public class MessageCustomHandler implements WxMpMessageHandler,WxMpMessageMatcher {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        WxSession session = wxSessionManager.getSession(wxMpXmlMessage.getFromUserName());
        WxMpXmlOutTextMessage message = null;
        if (session.getAttribute("talking") == null){
            message = WxMpXmlOutMessage.TEXT().content("Hi,请问有什么能帮您的？")
                    .fromUser(wxMpXmlMessage.getToUserName())
                    .toUser(wxMpXmlMessage.getFromUserName()).build();
        }else {

        }
        return message;
    }

    @Override
    public boolean match(WxMpXmlMessage wxMpXmlMessage) {
        return "呼叫客服".equals(wxMpXmlMessage.getContent());
    }
}
