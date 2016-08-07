package com.shaozilee.dada.controller;

import com.shaozilee.dada.dao.UserDao;
import com.shaozilee.dada.pojo.ForumUser;
import com.shaozilee.dada.utils.AjaxCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

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
    public void doLogin(ForumUser user,@RequestParam(value="api", required=false, defaultValue="false") boolean api,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        try{
            user = UserDao.getInstance().getUserByEmail(user.getEmail());
            if(user.getPassword().equals(user.getPassword())){
                request.getSession().setAttribute("user",user);
                toJson(AjaxCode.SUC, response);
            }else{
                toJson(AjaxCode.ERR_LOGIN_INCORRECT, response);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            toJson(AjaxCode.ERR, response);
        }


    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        request.getSession().removeAttribute("user");
        response.sendRedirect("index.html");
    }

    @RequestMapping("/doLogout")
    public void doLogout(@RequestParam(value="api", required=false, defaultValue="false") boolean api,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        request.getSession().removeAttribute("user");
        toJson(AjaxCode.SUC, response);
    }

    @RequestMapping("/regist")
    public String regist() throws Exception{
        return "regist";
    }

    @RequestMapping("/doRegist")
    public void doRegist(ForumUser user,@RequestParam(value="api", required=false, defaultValue="false") boolean api,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        user.setRegDate(date);
        try{
            user = UserDao.getInstance().add(user);
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