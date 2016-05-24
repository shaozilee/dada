package com.shaozilee.dada.controller;

import com.shaozilee.dada.pojo.Member;
import com.shaozilee.dada.utils.AjaxCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lee on 15-9-13.
 */

@Controller
public class MemberController extends AbstractController{
    public static Logger logger = LogManager.getLogger(MemberController.class);

    @RequestMapping("/doLogin")
    public void doLogin(@RequestParam(value="api", required=false, defaultValue="false") boolean api,@RequestParam(value="redirect", required=false, defaultValue="false") String redirect,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        String jsonStr = toJson(AjaxCode.SUC, response);
        logger.debug(jsonStr);
    }

    @RequestMapping("/regist")
    public String regist() throws Exception{
        return "regist";
    }

    @RequestMapping("/doRegist")
    public void doRegist(Member mem,@RequestParam(value="api", required=false, defaultValue="false") boolean api,@RequestParam(value="redirect", required=false, defaultValue="false") String redirect,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        String jsonStr = toJson(AjaxCode.SUC, response);
        logger.debug(jsonStr);
    }


    @RequestMapping("/doConfig")
    public void doConfig(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        String jsonStr = toJson(AjaxCode.SUC, response);
        logger.debug(jsonStr);
    }




}