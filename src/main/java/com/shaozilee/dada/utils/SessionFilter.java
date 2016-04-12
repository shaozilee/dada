package com.shaozilee.dada.utils;

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
    private Pattern[] include = null;
    private Pattern[] exclude = null;

    @Override
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

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String ctxPath = request.getContextPath();
        String url = request.getServletPath();
        System.out.println("url:"+url);

        if(exclude != null){
            for(int i=0;i<exclude.length;i++){
                if(exclude[i].matcher(url).find()){
                    System.out.println("find exclude:"+url);
                    filterChain.doFilter(request,response);
                    return;
                }
            }
        }

        if(include != null){
            for(int i=0;i<include.length;i++){
                if(include[i].matcher(url).find()){
                    if(request.getSession().getAttribute("user") == null){
                        System.out.println("find include:"+url);
                        Pattern pattern = Pattern.compile(".*\\.do(\\?)?.*");
                        if(pattern.matcher(url).find()){
                            Map result = new HashMap();
                            result.put("status",-1);
                            result.put("msg","没有权限访问此请求！");
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

    @Override
    public void destroy() {

    }
}
