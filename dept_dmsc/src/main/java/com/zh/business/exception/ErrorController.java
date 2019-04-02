package com.zh.business.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    //跳转到登录页面
    @RequestMapping("/error.do")
    public String error(){
        return "/common/Error";
    }



}
