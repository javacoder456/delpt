package com.zh.business.exception;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * spring MVC 统一异常处理
 */
public class BusinessException implements HandlerExceptionResolver {

        private static Logger logger = LoggerFactory.getLogger(BusinessException.class);


        @Override
        public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
            ModelAndView mv = new ModelAndView();
            logger.error("请求处理失败，请求url=[{}], 失败原因 : {}", request.getRequestURI(),ex);
            if (mv.isEmpty() || null == mv) {
                String message = "系统异常，请联系管理员";
                if(ex instanceof BaseException){
                    message = ex.getMessage();
                }
                mv = errorResult(message, "/error/error.do", request);
            }
            return mv;
        }


        /**
         * 判断是否ajax请求
         *
         * @param request 请求对象
         * @return true:ajax请求  false:非ajax请求
         */
        private boolean isAjax(HttpServletRequest request) {
            return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
        }

        /**
         * 返回错误信息
         *
         * @param message 错误信息
         * @param url     错误页url
         * @param request 请求对象
         * @return 模型视图对象
         */
        private ModelAndView errorResult(String message, String url, HttpServletRequest request) {
            if (isAjax(request)) {
                return jsonResult(500, message);
            } else {
                return normalResult(message, url);
            }
        }

        /**
         * 返回错误页
         *
         * @param message 错误信息
         * @param url     错误页url
         * @return 模型视图对象
         */
        private ModelAndView normalResult(String message, String url) {
            Map<String, String> model = new HashMap<>();
            model.put("errorMessage", message);
            return new ModelAndView(url, model);
        }

        /**
         * 返回错误数据
         *
         * @param message 错误信息
         * @return 模型视图对象
         */
        private ModelAndView jsonResult(int code, String message) {
            ModelAndView mv = new ModelAndView();
            FastJsonJsonView view = new FastJsonJsonView();
            view.setAttributesMap(JSONObject.parseObject(code + message));
            mv.setView(view);
            return mv;
        }


}
