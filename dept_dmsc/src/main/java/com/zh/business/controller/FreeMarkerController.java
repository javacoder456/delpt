package com.zh.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("freeMarker")
public class FreeMarkerController{

    //跳转到登录页面
    @RequestMapping("/manger.do")
    public String login(HttpServletRequest request){
        return "/freemarker/FileManger";
    }
}
