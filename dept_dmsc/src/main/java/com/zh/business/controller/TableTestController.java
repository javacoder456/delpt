package com.zh.business.controller;

import com.github.pagehelper.PageInfo;
import com.zh.service.SysUserService;
import com.zh.entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TableTestController {

    @Resource
    private SysUserService userService;

    //登录页面
    @RequestMapping("/page.do")
    public String login(){
        return "/test/TestPage";
    }

    //分页测试
    @RequestMapping("/getData.do")
    @ResponseBody
    public Map testData (Integer pageNo, Integer pageSize){
        Map<String,Object> data = new HashMap();
        PageInfo<SysUser> page = userService.listForPage(pageNo,pageSize);
        data.put("recordsTotal", page.getTotal());
        data.put("recordsFiltered", page.getTotal());
        data.put("data", page.getList());
        return data;
    }


}
