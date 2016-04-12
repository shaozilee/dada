package com.shaozilee.dada.controller;

import com.shaozilee.dada.dao.CommentDao;
import com.shaozilee.dada.pojo.Comment;
import com.shaozilee.dada.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lee on 15-11-20.
 */

@Controller
public class CommentController extends AbstractController {
    private static Logger logger = LogManager.getLogger(CommentController.class);

    /**
     * 帖子回复发布请求
     * @param comment
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/comment/add")
    public void add(Comment comment,HttpServletRequest request,HttpServletResponse response) throws Exception{
        User user = (User)request.getSession().getAttribute("user");
        comment.setUid(user.getUid());
        comment.setDateline(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        Integer cid = CommentDao.getInstance().save(comment);
        comment =  CommentDao.getInstance().getCommentByCid(cid);

        Map result = new HashMap();
        result.put("comment",comment);
        result.put("status",1);
        result.put("msg","发布回复成功！");
        String jsonString = new JSONObject(result).toString();

        response.getWriter().write(jsonString);
        response.getWriter().close();
    }




}
