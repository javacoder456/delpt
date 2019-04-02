package com.zh.business.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.service.SysRoleService;
import com.zh.service.SysUserService;
import com.zh.entity.SysRole;
import com.zh.entity.SysUser;
import com.zh.utils.DTOUtil;
import com.zh.utils.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller  
@RequestMapping("/userManger")
public class SysUserController {

    @Resource
    private SysUserService userService;

    @Resource
    private SysRoleService roleService;

    @RequestMapping(value="/init.do")
    public String list(){
        return "/business/user/UserSetup";
    }

    @RequestMapping(value ="/list.do",method=RequestMethod.POST)
    @ResponseBody
    public Map list(Integer pageNo, Integer pageSize) {
        Map<String,Object> data = new HashMap();
        List<SysUser> list = userService.getAllUsers();
        PageHelper.startPage(pageNo,pageSize);
        PageInfo page = new PageInfo(list);
        data.put("recordsTotal", page.getTotal());
        data.put("recordsFiltered", page.getTotal());
        data.put("data", page.getList());
        return data;
    }

    @RequestMapping(value="/addUser.do")
    public String addUser(HttpServletRequest request){
        List<SysRole> roles = roleService.getAllRoles();
        request.setAttribute("roles",roles);
        return "/business/user/UserSetupAdd";
    }


    @RequestMapping(value="/add.do")
    @ResponseBody
    public Map<String, Object> add(HttpServletRequest request){
        Map<String,Object> data = new HashMap();
        SysUser user = (SysUser) DTOUtil.getDTO(request, SysUser.class);
        MD5Util.encryptPassword(user);
        SysRole role = roleService.getRoleById(user.getRoleid().toString());
        user.setFaultrole(role.getRolename());
        userService.save(user);
        data.put("success", true);
        data.put("message", "保存成功！");
        return data;
    }

    @RequestMapping(value="/editUser.do")
    public String editUser(HttpServletRequest request){
        String userid = request.getParameter("userid");
        SysUser user = userService.findUserById(userid);
        List<SysRole> roles = roleService.getAllRoles();
        request.setAttribute("roles",roles);
        request.setAttribute("user",user);
        return "/business/user/UserSetupEdit";
    }



    @RequestMapping(value = "/edit.do",method=RequestMethod.POST)
    @ResponseBody
    public Map edit(HttpServletRequest request) {
        Map<String,Object> data = new HashMap();
        SysUser user = (SysUser) DTOUtil.getDTO(request, SysUser.class);
        SysRole role = roleService.getRoleById(user.getRoleid().toString());
        user.setFaultrole(role.getRolename());
        userService.update(user);
        data.put("success", true);
        data.put("message", "更新成功！");
        return data;
    }

    @RequestMapping(value ="/delete.do",method=RequestMethod.POST)
    @ResponseBody
    public Map delete(HttpServletRequest request) {
        Map<String,Object> data = new HashMap();
        String userid = request.getParameter("userid");
        userService.delete(userid);
        data.put("success", true);
        data.put("message", "更新成功！");
        return data;
    }

}