package com.zh.business.controller;

import com.zh.service.SysAuthorityService;
import com.zh.service.SysRoleService;
import com.zh.entity.SysAuthority;
import com.zh.entity.SysRole;
import com.zh.utils.DTOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/roleManger")
public class SysRoleController {

    private static Logger log=LoggerFactory.getLogger(SysRoleController.class);

    @Resource
    private SysAuthorityService authoritiesService;

    @Resource
    private SysRoleService roleService;

    @RequestMapping(value="/list.do")
    public String list(HttpServletRequest request){
        List<SysRole> roles = roleService.getAllRoles();
        request.setAttribute("data",roles);
        return "/business/role/RoleSetup";
    }

    /**
     * 初始化新增页面
     * @param request
     * @return
     */
    @RequestMapping(value="/addRole.do")
    public String addRole(HttpServletRequest request){
        return "/business/role/RoleSetupAdd";
    }
    /**
     * 初始化修改页面
     * @param request
     * @return
     */
    @RequestMapping(value="/editRole.do")
    public String editRole(HttpServletRequest request){
        String roleid = request.getParameter("roleid");
        SysRole role = roleService.getRoleById(roleid);
        String auths = role.getAuths() == null? "{}": role.getAuths();
        request.setAttribute("role",role);
        request.setAttribute("auths",auths);
        return "/business/role/RoleSetupEdit";
    }

    @RequestMapping(value="/getMenus.do")
    @ResponseBody
    public Map<String, Object> getMenus(HttpServletRequest request){
        Map<String,Object> data = new HashMap();
        List<SysAuthority> resources = authoritiesService.getAllResource();
        data.put("menu",resources);
        data.put("success", true);
        data.put("message", "保存成功！");
        return data;
    }

    @RequestMapping(value="/add.do")
    @ResponseBody
    public Map add(HttpServletRequest request){
        Map<String,Object> data = new HashMap();
        SysRole role = (SysRole) DTOUtil.getDTO(request, SysRole.class);
        roleService.save(role);
        data.put("success", true);
        data.put("message", "保存成功！");
        return data;
    }

    @RequestMapping(value="/edit.do")
    @ResponseBody
    public Map edit(HttpServletRequest request){
        Map<String,Object> data = new HashMap();
        SysRole role = (SysRole) DTOUtil.getDTO(request, SysRole.class);
        roleService.update(role);
        data.put("success", true);
        data.put("message", "更新成功！");
        return data;
    }

    @RequestMapping(value ="/delete.do",method=RequestMethod.POST)
    @ResponseBody
    public Map delete(HttpServletRequest request) {
        Map<String,Object> data = new HashMap();
        String roleid = request.getParameter("roleid");
        roleService.delete(roleid);
        data.put("success", true);
        data.put("message", "删除成功！");
        return data;
    }
}