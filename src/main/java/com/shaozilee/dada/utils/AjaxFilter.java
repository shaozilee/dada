package com.shaozilee.dada.utils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 针对ajax请求和返回的数据串进行编解码设置
 * request的setCharacterEncoding用于处理请求的数据格式，解决中文乱码
 * response的contentType设置，否则浏览器不识别返回的数据格式，解析中文乱码
 *
 * Created by lee on 15-11-24.
 */
public class AjaxFilter implements Filter{

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        //针对浏览器解析页面数据设置contentType
        servletResponse.setContentType("text/html;charset=UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {

    }
}
