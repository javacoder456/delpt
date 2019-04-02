package com.zh.business.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zh.common.dbmanger.tran.impl.CommonQueryService;
import com.zh.entity.Interfaceconfig;
import com.zh.utils.DTOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/commonQuery")
public class CommonQueryController {

    private static Logger logger = LoggerFactory.getLogger(CommonQueryController.class);

    @RequestMapping(value = "/initQuery.action")
    public String initQuery(){
        return "/business/interface/Query";
    }

    @Resource
    private CommonQueryService commonQueryService;

    @RequestMapping(value = "/query.action")
    public void commonQuery(HttpServletRequest request,HttpServletResponse response)
            throws IOException{
        //获取请求参数
        Map<String,String> reqMap = DTOUtil.getReqMap(request);

        commonQueryService.changeCharSet(reqMap);
        //校验参数
        commonQueryService.checkData(reqMap);
        //获取对应接口参数 方法调用
        Interfaceconfig config = commonQueryService.initConfig(reqMap);
        //权限校验
        commonQueryService.checkAuth(reqMap,config);
        //返回结果
        JSONObject result = commonQueryService.dealResult(reqMap,config);
        responseQueryResult(result.toJSONString(),response);
    }

    @RequestMapping(value = "/queryForPage.action")
    @ResponseBody
    public Map<String,Object> commonQueryForPage(HttpServletRequest request) throws IOException{
        Map<String,Object> data = new HashMap();
        //获取请求参数
        Map<String,String> reqMap = DTOUtil.getReqMap(request);
        commonQueryService.changeCharSet(reqMap);
        //校验参数
        commonQueryService.checkData(reqMap);
        //获取对应接口参数 方法调用
        Interfaceconfig config = commonQueryService.initConfigForPage(reqMap);
        //权限校验
        commonQueryService.checkAuth(reqMap,config);
        //返回结果
        JSONObject result = commonQueryService.dealResult(reqMap,config);
        Integer total = commonQueryService.getTotal(reqMap);
        JSONArray jsonArray = (JSONArray)result.get("result");
        List jsonList = JSONArray.parseArray(jsonArray.toJSONString());
        data.put("recordsTotal", total);
        data.put("recordsFiltered", total);
        data.put("data", jsonList);
        return data;
    }

    /**
     * 响应商户退款查询请求
     *
     */
    public static void responseQueryResult(String paramMapText, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html;charset=utf8" );
        resp.getWriter().append(paramMapText);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}
