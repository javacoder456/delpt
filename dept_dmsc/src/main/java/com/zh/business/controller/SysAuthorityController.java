package com.zh.business.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.service.SysAuthorityService;
import com.zh.entity.SysAuthority;
import com.zh.utils.DTOUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysSetUp")
public class SysAuthorityController {

    @Resource
    private SysAuthorityService authoritiesService;

    //获取首页
    @RequestMapping("/home.do")
    public String getHomePage() {
        return "/common/HomePage";
    }

    //获取资源页
    @RequestMapping("/init.do")
    public String init(Model model) {
        // List<Authorities> data = authoritiesService.getAllResource();
        //model.addAttribute("data", data);
        return "/business/auth/AuthSetup";
    }

    @RequestMapping("/setup.do")
    public String setup(HttpServletRequest request, Model model) {
        String authorityid = request.getParameter("authorityid");
        SysAuthority authorities = authoritiesService.getResourceById(authorityid);
        model.addAttribute("auth", authorities);
        return "/business/auth/AuthSetupEdit";
    }

    @RequestMapping("/addAuth.do")
    public String addAuth(HttpServletRequest request, Model model) {
        String fatherid = request.getParameter("fatherid");
        SysAuthority auth = authoritiesService.getResourceById(fatherid);
        model.addAttribute("auth", auth);
        return "/business/auth/AuthSetupAdd";
    }
    @RequestMapping(value ="/list.do",method=RequestMethod.POST)
    @ResponseBody
    public Map list(Integer pageNo, Integer pageSize) {
        Map<String,Object> data = new HashMap();
        List<SysAuthority> list = authoritiesService.getAllResource();
        PageHelper.startPage(pageNo,pageSize);
        PageInfo page = new PageInfo(list);
        data.put("recordsTotal", page.getTotal());
        data.put("recordsFiltered", page.getTotal());
        data.put("data", page.getList());
        return data;
    }
    @RequestMapping(value ="/add.do",method=RequestMethod.POST)
    @ResponseBody
    public Map add(HttpServletRequest request) {
        Map<String,Object> data = new HashMap();
        SysAuthority auth = (SysAuthority) DTOUtil.getDTO(request, SysAuthority.class);
        authoritiesService.addAuth(auth);
        data.put("success", true);
        data.put("message", "更新成功！");
        return data;
    }

    @RequestMapping(value = "/edit.do",method=RequestMethod.POST)
    @ResponseBody
    public Map edit(HttpServletRequest request) {
        Map<String,Object> data = new HashMap();
        SysAuthority auth = (SysAuthority) DTOUtil.getDTO(request, SysAuthority.class);
        authoritiesService.editAuth(auth);
        data.put("success", true);
        data.put("message", "更新成功！");
        return data;
    }

    @RequestMapping(value ="/delete.do",method=RequestMethod.POST)
    @ResponseBody
    public Map delete(HttpServletRequest request) {
        Map<String,Object> data = new HashMap();
        String authorityid = request.getParameter("authorityid");
        authoritiesService.deleteAuth(authorityid);
        data.put("success", true);
        data.put("message", "更新成功！");
        return data;
    }


}
