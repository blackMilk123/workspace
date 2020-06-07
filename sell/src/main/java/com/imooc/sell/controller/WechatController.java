package com.imooc.sell.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Controller
@Slf4j
@RequestMapping("/wechat")
public class WechatController {
    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/auth")
    public String auth(@RequestParam(value = "returnUrl")String returnUrl){

        String url="http://blackmilk.free.idcfengye.com/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO,
                URLEncoder.encode(returnUrl));
        return "redirect:"+redirectUrl;
    }
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam(value = "code")String code,
                         @RequestParam(value = "state")String returnUrl){
        WxMpOAuth2AccessToken wxAccessToken = new WxMpOAuth2AccessToken();

        try {
            wxAccessToken = wxMpService.oauth2getAccessToken(code);

        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        String openId = wxAccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
