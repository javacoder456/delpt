package com.zh.business.controller;

import com.zh.utils.DateUtil;
import com.zh.utils.ReadPropertiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("fileManger")
public class FileMangerController {

    //跳转到登录页面
    @RequestMapping("/manger.do")
    public String login(HttpServletRequest request){
        return "/test/FileManger";
    }

    @RequestMapping(value="/upload.do",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        Map<String,Object> data = new HashMap();
        String uploadPath = ReadPropertiesUtil.getValue("uploadPath");
        String uploadName = file.getOriginalFilename();
        String suffix = uploadName.substring(uploadName.lastIndexOf("."));
        String fileName = DateUtil.getDateFormat(new Date(),"yyyyMMddHHmmss") + suffix;
        File newFile = new File(uploadPath + File.separator + fileName);
        if(!newFile.exists()){
            newFile.mkdirs();
        }
        try {
            file.transferTo(newFile);
        }catch (IOException e){
            e.printStackTrace();
        }
        data.put("success", true);
        data.put("path", newFile.getPath());
        return data;
    }


}
