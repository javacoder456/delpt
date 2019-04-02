package com.zh.business.controller;

import com.zh.service.NoticeService;
import com.zh.entity.Notice;
import com.zh.entity.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/msgManger")
public class MsgMangerController {

    @Resource
    private NoticeService noticeService;

    /**
     * 消息弹框设置
     * @param request
     * @return
     */
    @RequestMapping(value="/list.do")
    public String list(HttpServletRequest request,HttpSession session){
        SysUser user = (SysUser) session.getAttribute("user");
        List<Notice> notices = noticeService.getNoticesById(user.getUserid().toString());
        request.setAttribute("notices",notices);
        return "/common/Notice";
    }

    @RequestMapping(value="/getNotice.do")
    @ResponseBody
    public Map getMsg(HttpServletRequest request){
        Map<String,Object> data = new HashMap();
        data.put("success", true);
        data.put("message", "更新成功！");
        return data;
    }



}
