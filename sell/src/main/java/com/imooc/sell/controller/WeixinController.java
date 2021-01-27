package com.imooc.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
@RequestMapping("weixin")
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam(value = "code")String code){
        log.info("进入微信授权.......");
        log.info("code={}",code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxe3a8b0b67a8ae948&secret=f2e4520f9bc570bde1d5526678256317" +
                "&code="+code+"&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        String responce = restTemplate.getForObject(url, String.class);
        log.info("responce={}",responce);
    }
}
