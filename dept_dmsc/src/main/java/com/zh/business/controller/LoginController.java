package com.zh.business.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zh.service.*;
import com.zh.entity.*;
import com.zh.common.CommonConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private SysAuthorityService authoritiesService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private NoticeService noticeService;

    @Resource
    private SysUserService sysUserService;

    //登录页面
    @RequestMapping("/login.do")
    public String login(){
        return "/common/login";
    }

    //登录页面
    @RequestMapping("/register.do")
    public String register(){
        return "/common/register";
    }

    //登录验证
    @RequestMapping(value = "/valid.do")
    public String validLogin(HttpSession httpSession,String username,String password,RedirectAttributes attr){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {//执行登录
            UsernamePasswordToken userToken = new UsernamePasswordToken(username,password);
            try {
                subject.login(userToken);
            }  catch (UnknownAccountException e) {
                attr.addFlashAttribute ("error","用户名或密码错误!");
                return "redirect:/login/login.do";
            } catch (IncorrectCredentialsException e) {
                attr.addFlashAttribute ("error","用户名或密码错误!");
                return "redirect:/login/login.do";
            } catch (ExcessiveAttemptsException e) {
                attr.addFlashAttribute ("error","登录失败多次，账户锁定!");
                return "redirect:/login/login.do";
            } catch (AuthenticationException e) {
                logger.error ("其他错误：" + e.getMessage());
            }catch (Exception e) {
                logger.error("登陆失败！错误原因：",e);
            }
        }
        String principal = (String)subject.getPrincipal();
        SysUser user = sysUserService.findUserByName(principal);
        httpSession.setAttribute("user",user);
        return "redirect:/login/index.do";
    }

    //验证成功页面
    @RequestMapping("/index.do")
    public String index(HttpServletRequest request,HttpSession session){
        SysUser user = (SysUser) session.getAttribute("user");
        //获取消息
        List<Notice> notices = noticeService.getNoticesById(user.getUserid().toString());
        request.setAttribute("count",notices.size());
        //获取系统当前时间
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm");
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        String now = df.format(date);
        String week = dateFm.format(date);
        request.setAttribute("user", user);
        request.setAttribute("nowtime", now);
        request.setAttribute("week", week);
        return "/common/index";
    }

    /**
     * 获取主菜单
     */
    @RequestMapping(value="/getMenu.do")
    @ResponseBody
    public Map getMainMenu(HttpServletRequest request,HttpSession session){
        SysUser user = (SysUser) session.getAttribute("user");
        Map<String,Object> data = new HashMap();
        data.put("user", user);
        List authIds = getAuthIds(session);
        List<SysAuthority> roots = new ArrayList<>();
        List<SysAuthority> crs = new ArrayList<>();
        List<SysAuthority> threeLevelMenu = new ArrayList<>();
        if(authIds == null){
            data.put("mainMenu", roots);
            data.put("submenu", crs);
            data.put("threeMenu", threeLevelMenu);
            return data;
        }
        for (Object id : authIds){
            String authid = id.toString();
            SysAuthority auth =  authoritiesService.getResourceById(authid);
            if(auth == null){
                continue;
            }
            if(CommonConstant.rootMenu.equals(auth.getChildren())){
                roots.add(auth);
            }
            if(CommonConstant.subMenu.equals(auth.getChildren())){
                crs.add(auth);
            }
            if(CommonConstant.threeMenu.equals(auth.getChildren())){
                threeLevelMenu.add(auth);
            }
        }
        data.put("success",true);
        data.put("mainMenu", roots);
        data.put("submenu", crs);
        data.put("threeMenu", threeLevelMenu);
        return data;
    }

    /**
     * 获取子菜单
     */
    @RequestMapping(value="/getSubMenu.do")
    @ResponseBody
    public Map getSubMenu(HttpServletRequest request,HttpSession session){
        Map<String,Object> data = new HashMap();
        String fatherid = request.getParameter("fatherid");
        List<SysAuthority> subMenus = authoritiesService.getSubMenuById(fatherid,CommonConstant.subMenu);
        List<SysAuthority> resultSub = new ArrayList<>();
        List<SysAuthority> resultthree = new ArrayList<>();
        List<Object> authIds = getAuthIds(session); //菜单
        //二级菜单不为空时，根据id获取三级菜单 2018012704
        for(SysAuthority subMenu : subMenus){
            String subMenuAuthorityid= subMenu.getAuthorityid().toString();
            resultSub.add(subMenu);
            if(authIds.contains(subMenuAuthorityid)){
                List<SysAuthority> threeMenus = authoritiesService.getSubMenuById(subMenuAuthorityid,CommonConstant.threeMenu);
                //菜单信息
                for (SysAuthority threeMenu : threeMenus){
                    if(authIds.contains(threeMenu.getAuthorityid().toString())){
                        resultthree.add(threeMenu);
                    }
                }
            }
        }
        data.put("submenu", resultSub);
        data.put("threeMenu", resultthree);
        data.put("success", true);
        return data;
    }

    private List getAuthIds(HttpSession session){
        SysUser user = (SysUser) session.getAttribute("user");
        List authIds = new ArrayList();
        if(user != null && CommonConstant.ADMIN.equals(user.getName())){
            List<SysAuthority> sysAuthorities = authoritiesService.getAllResource();
            for(SysAuthority sysAuthority : sysAuthorities){
                authIds.add(sysAuthority.getAuthorityid().toString());
            }
            return authIds;
        }
        SysRole sysRole = sysRoleService.getRoleById(user.getRoleid().toString());
        JSONArray jsonArray = JSONObject.parseArray(sysRole.getAuths());
        return jsonArray;
    }

}
