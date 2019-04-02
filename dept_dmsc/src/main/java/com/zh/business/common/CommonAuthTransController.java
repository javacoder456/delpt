package com.zh.business.common;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zh.entity.ComAuthConfig;
import com.zh.entity.Interfaceconfig;
import com.zh.service.CommonAuthTranService;
import com.zh.service.InterfaceconfigService;
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
@RequestMapping("/commonAuthTrans")
public class CommonAuthTransController {

    private static Logger logger = LoggerFactory.getLogger(CommonAddInterfaceController.class);

    @Resource
    private InterfaceconfigService interfaceService;

    @Resource
    private CommonAuthTranService commonAuthTranService;

    /**
     * 初始化新增页面
     * @return
     */
    @RequestMapping(value="/initAuthTrans.do")
    public String initInterface(){
        return "/business/interface/TransAuthAdd";
    }

    @RequestMapping(value ="/addAuthTrans.do",method=RequestMethod.POST)
    @ResponseBody
    public Map add(HttpServletRequest request) {
        Map<String,Object> data = new HashMap();
        ComAuthConfig config = (ComAuthConfig) DTOUtil.getDTO(request, ComAuthConfig.class);
        commonAuthTranService.save(config);
        data.put("success", true);
        data.put("message", "保存成功！");
        return data;
    }

    @RequestMapping(value ="/queryAuth.do",method=RequestMethod.POST)
    @ResponseBody
    public Map queryAuth(HttpServletRequest request) {
        Map<String,Object> data = new HashMap();
        EntityWrapper<Interfaceconfig> wrapper = new EntityWrapper<>();
        List<Interfaceconfig> configs = interfaceService.selectList(wrapper);
        data.put("configs",configs);
        data.put("success", true);
        data.put("message", "保存成功！");
        return data;
    }

}
