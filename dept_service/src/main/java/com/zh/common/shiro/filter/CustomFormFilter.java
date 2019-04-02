package com.zh.common.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.slf4j.LoggerFactory.getLogger;

public class CustomFormFilter extends FormAuthenticationFilter {

    private static final Logger logger = getLogger(CustomFormFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Boolean flag = super.isAccessAllowed(request, response, mappedValue);
        logger.debug("Permitted=>{},url:{}",flag,httpRequest.getRequestURL());
        return flag;
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (null == subject.getPrincipal()) {//表示没有登录，重定向到登录页面
            WebUtils.issueRedirect(request, response, "/login/login.do");
        } else {
            if (StringUtils.hasText("/login/unAuth.do")) {//如果有未授权页面跳转过去
                WebUtils.issueRedirect(request, response, "/login/unAuth.do");
            } else {//否则返回401未授权状态码
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return Boolean.FALSE;
        //return super.onAccessDenied(request, response);
    }



}
