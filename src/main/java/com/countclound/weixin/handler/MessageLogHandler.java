package com.countclound.weixin.handler;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by dff on 2016/1/7.
 * 记录日志
 */
@Service("MessageLogHandler")
public class MessageLogHandler implements WxMpMessageHandler{

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        System.out.println(wxMpXmlMessage.toString());
        return null;
    }
}
