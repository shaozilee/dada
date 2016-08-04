package com.shaozilee.dada.controller;

import com.shaozilee.dada.dao.MemberDao;
import com.shaozilee.dada.pojo.CommonMember;
import com.shaozilee.dada.utils.AjaxCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Member;
import java.util.Date;

/**
 * Created by lee on 15-9-13.
 */

@Controller
public class MemberController extends AbstractController{
    public static Logger logger = LogManager.getLogger(MemberController.class);

    @RequestMapping("/login")
    public String login(@RequestParam(value="redirect", required=false, defaultValue="") String redirect,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        model.addAttribute("redirect",redirect);
        return "login";
    }

    @RequestMapping("/doLogin")
    public void doLogin(CommonMember user,@RequestParam(value="api", required=false, defaultValue="false") boolean api,@RequestParam(value="redirect", required=false, defaultValue="false") String redirect,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        request.getSession().setAttribute("user",user);

        String jsonStr = toJson(AjaxCode.SUC, response);
        logger.debug(jsonStr);
    }

    @RequestMapping("/regist")
    public String regist() throws Exception{
        return "regist";
    }

    @RequestMapping("/doRegist")
    public void doRegist(CommonMember mem,@RequestParam(value="api", required=false, defaultValue="false") boolean api,@RequestParam(value="redirect", required=false, defaultValue="false") String redirect,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        mem.setRegDate(new Date().getTime());
        try{
            mem = MemberDao.getInstance().add(mem);
            request.getSession().setAttribute("user",mem);
            toJson(AjaxCode.SUC, response);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            toJson(AjaxCode.ERR, response);
        }

    }


    @RequestMapping("/doConfig")
    public void doConfig(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        String jsonStr = toJson(AjaxCode.SUC, response);
        logger.debug(jsonStr);
    }




}