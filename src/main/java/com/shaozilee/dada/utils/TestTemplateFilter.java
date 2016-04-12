package com.shaozilee.dada.utils;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.servlet.ServletGroupTemplate;
import org.beetl.ext.web.SimpleCrossFilter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lee on 15-9-12.
 */
public class TestTemplateFilter extends SimpleCrossFilter{

    @Override
    protected GroupTemplate getGroupTemplate() {
        return ServletGroupTemplate.instance().getGroupTemplate();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    protected String getValueFile(String path, HttpServletRequest hq, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        return "/test/" + path + ".js";
    }

    @Override
    protected String getCommonValueFile(HttpServletRequest hq, HttpServletResponse response) {
        return "/test/common.js";
    }
}
