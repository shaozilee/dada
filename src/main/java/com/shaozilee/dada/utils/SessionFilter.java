package com.shaozilee.dada.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by lee on 15-12-6.
 */
public class SessionFilter implements Filter {
    public static Logger logger = LogManager.getLogger(SessionFilter.class);
    private Pattern[] include = null;
    private Pattern[] exclude = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        String includeStr = filterConfig.getInitParameter("include");
        String excludeStr = filterConfig.getInitParameter("exclude");
        if(includeStr != null){
            String[] tempInclude = includeStr.split(";");
            include = new Pattern[tempInclude.length];
            for(int i=0;i<tempInclude.length;i++){
                include[i] = Pattern.compile(tempInclude[i]);
            }
        }
        if(excludeStr != null){
            String[] tempExclude = excludeStr.split(";");
            exclude = new Pattern[tempExclude.length];
            for(int i=0;i<tempExclude.length;i++){
                exclude[i] = Pattern.compile(tempExclude[i]);
            }
        }

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String ctxPath = request.getContextPath();
        String url = request.getServletPath();
        logger.debug("url:"+url);

        if(exclude != null){
            for(int i=0;i<exclude.length;i++){
                if(exclude[i].matcher(url).find()){
                    logger.debug("find exclude:"+url);
                    filterChain.doFilter(request,response);
                    return;
                }
            }
        }

        if(include != null){
            for(int i=0;i<include.length;i++){
                if(include[i].matcher(url).find()){
                    if(request.getSession().getAttribute("user") == null){
                        logger.debug("find include:"+url);
                        //判断是否是ajax请求
                        Pattern pattern = Pattern.compile(".*\\.do(\\?)?.*");
                        if(pattern.matcher(url).find()){
                            Map result = new HashMap();
                            result.put("code",AjaxCode.ERR_NO_PERMISSION);
                            result.put("msg","您还没有登录，不能访问此请求！");
                            String jsonString = new JSONObject(result).toString();
                            response.getWriter().write(jsonString);
                            response.getWriter().close();
                        }else{
                            response.sendRedirect(ctxPath+"/login.html?redirect="+ctxPath+url);
                        }


                        return;
                    }
                }
            }
        }

        filterChain.doFilter(request,response);

    }

    public void destroy() {

    }
}
