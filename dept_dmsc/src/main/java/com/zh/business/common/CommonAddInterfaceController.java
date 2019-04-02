package com.zh.business.common;

import com.zh.service.InterfaceconfigService;
import com.zh.entity.Interfaceconfig;
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
import java.util.Map;
@Controller
@RequestMapping("/commonQuery")
public class CommonAddInterfaceController {

    private static Logger logger = LoggerFactory.getLogger(CommonAddInterfaceController.class);

    @Resource
    private InterfaceconfigService interfaceService;

    /**
     * 初始化
     * @return
     */
    @RequestMapping(value="/initInterface.do")
    public String initInterface(){
        return "/business/interface/InterfaceList";
    }

    @RequestMapping(value="/addInterface.do")
    public String add(){
        return "/business/interface/InterfaceAdd";
    }

    @RequestMapping(value="/editInterface.do")
    public String edit(HttpServletRequest request){
        Interfaceconfig config = interfaceService.getById(request.getParameter("tradeid"));
        request.setAttribute("config",config);
        return "/business/interface/InterfaceEdit";
    }

    @RequestMapping(value ="/delete.do",method=RequestMethod.POST)
    @ResponseBody
    public Map delete(HttpServletRequest request) {
        Map<String,Object> data = new HashMap();
        String tradeid = request.getParameter("tradeid");
        interfaceService.deleteById(tradeid);
        data.put("success", true);
        data.put("message", "更新成功！");
        return data;
    }

    @RequestMapping(value ="/save.do",method=RequestMethod.POST)
    @ResponseBody
    public Map add(HttpServletRequest request) {
        Map<String,Object> data = new HashMap();
        Interfaceconfig interfaceConfig = (Interfaceconfig) DTOUtil.getDTO(request, Interfaceconfig.class);
        interfaceService.save(interfaceConfig);
        data.put("success", true);
        data.put("message", "保存成功！");
        return data;
    }

}
