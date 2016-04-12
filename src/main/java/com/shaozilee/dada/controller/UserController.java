package com.shaozilee.dada.controller;

import com.shaozilee.dada.dao.UserDao;
import com.shaozilee.dada.pojo.User;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lee on 15-9-13.
 */

@Controller
public class UserController extends AbstractController{

    @RequestMapping("/login")
    public String login(@RequestParam(value="api", required=false, defaultValue="false") boolean api, HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        return api?debugAPI(model):"login";
    }

    @RequestMapping("/dologin")
    public void dologin(User user,@RequestParam(value="redirect", required=false, defaultValue="false") String redirect,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        User u = UserDao.getInstance().getUserByPassword(user.getName(),user.getPassword());
        Map result = new HashMap();
        if(u != null){
            u.setPassword(null);
            request.getSession().setAttribute("user",u);
            result.put("user",u);
            result.put("status",0);
            result.put("msg","登录成功！");
        }else{
            result.put("status", 1);
            result.put("msg", "用户不存在！");
        }

        String jsonString = new JSONObject(result).toString();

        response.getWriter().write(jsonString);
        response.getWriter().close();
    }

    @RequestMapping("/signup")
    public String signup(Model model) throws Exception{
        return "signup";
    }

    @RequestMapping("/dosignup")
    public void dosignup(User user,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        Map result = new HashMap();
        Integer uid = UserDao.getInstance().addUser(user);
        if(uid != null){
            User newUser = UserDao.getInstance().getUserByUid(uid);
            newUser.setPassword(null);
            request.getSession().setAttribute("user",newUser);
            result.put("user",newUser);
            result.put("status",0);
            result.put("msg","注册成功");
        }else{
            result.put("status",1);
            result.put("msg","注册失败");
        }
        String jsonString = new JSONObject(result).toString();

        response.getWriter().write(jsonString);
        response.getWriter().close();
    }

}