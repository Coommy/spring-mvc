package com.countclound.weixin.interceptor;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录验证
 * Created by xuht on 2016/12/12.
 */
public class LoginInterceptor implements HandlerInterceptor{
    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    private WxMpService wxMpService;

    @Autowired
    @Qualifier("weixinConfigStorage")
    private WxMpConfigStorage wxMpConfigStorage;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.info("拦截器进行登录验证");
        //判断是否登录
        Cookie[] cookies = request.getCookies();
        int flag = 0;//主要是为了判断cookie是不是同时存在userId,tenantId,token
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if("userId".equals(cookie.getName()) && StringUtils.isNotBlank(cookie.getValue())){
                    flag++;
                    continue;
                }
                if("tenantId".equals(cookie.getName()) && StringUtils.isNotBlank(cookie.getValue())){
                    flag++;
                    continue;
                }
                if("token".equals(cookie.getName()) && StringUtils.isNotBlank(cookie.getValue())){
                    flag++;
                    continue;
                }
            }

        }
        if(flag != 3){
            //当前用户未登录
            //两种情况：1、用户已经绑定过，只是没有登录
            //         2、用户未绑定,需要绑定
            wxMpService = new WxMpServiceImpl();
            wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
            //获取域名地址
            StringBuffer url = request.getRequestURL();
            String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();

            String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(tempContextUrl+"server/getUserInfo", WxConsts.OAUTH2_SCOPE_BASE,"");
            response.sendRedirect(redirectUrl);
            /*String info="{\"state\":\"0\",\"result\":\"未绑定，需要绑定才能继续访问！\"}";
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(info);*/
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
    }
}
