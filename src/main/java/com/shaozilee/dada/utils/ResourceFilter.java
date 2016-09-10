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
public class ResourceFilter implements Filter {
    public static Logger logger = LogManager.getLogger(ResourceFilter.class);
    private Pattern[] include = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        String includeStr = filterConfig.getInitParameter("include");
        if(includeStr != null){
            String[] tempInclude = includeStr.split(";");
            include = new Pattern[tempInclude.length];
            for(int i=0;i<tempInclude.length;i++){
                include[i] = Pattern.compile(tempInclude[i]);
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

        if(include != null){
            for(int i=0;i<include.length;i++){
                if(include[i].matcher(url).find()){
                    response.sendRedirect(ctxPath+"/login.html?redirect="+ctxPath+url);
                    return;
                }
            }
        }

        filterChain.doFilter(request,response);

    }

    public void destroy() {

    }
}
