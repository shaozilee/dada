package com.shaozilee.dada.controller;

import com.shaozilee.dada.dao.CommentDao;
import com.shaozilee.dada.dao.PostDao;
import com.shaozilee.dada.dao.SectionDao;
import com.shaozilee.dada.pojo.Post;
import com.shaozilee.dada.pojo.Section;
import com.shaozilee.dada.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lee on 15-11-20.
 */

@Controller
public class PostController extends AbstractController {
    private static Logger logger = LogManager.getLogger(PostController.class);

    public static int COMMENT_PAGE_SIZE = 20;
    /**
     * 帖子内容
     * @param pid
     * @param api
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/post-{pid}-{page}")
    public String post(@PathVariable Integer pid,@PathVariable Integer page,@RequestParam(value = "api",required = false) boolean api,Model model) throws Exception{
        Post post = PostDao.getInstance().getPostByPid(pid);

        int start = (page-1)*COMMENT_PAGE_SIZE-1;
        List comments = CommentDao.getInstance().getCommentsByPid(pid,start<0?0:start,start<0?COMMENT_PAGE_SIZE-1:COMMENT_PAGE_SIZE);

        model.addAttribute("comments",comments);
        model.addAttribute("post",post);

        return api?debugAPI(model):"post";
    }

    /**
     * 帖子发布页面
     * @param api
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/post/new")
    public String newPost(@RequestParam(value = "sid",required = false) Integer sid,@RequestParam(value = "pid",required = false) Integer pid,@RequestParam(value = "api",required = false) boolean api,Model model) throws Exception{

        if(pid != null){
            Post post = PostDao.getInstance().getPostByPid(pid);
            model.addAttribute("post",post);
        }

        if(sid != null){
            Section section = SectionDao.getInstance().getSectionBySid(sid);
            model.addAttribute("section",section);
        }else{
            List list = SectionDao.getInstance().getTopSections();
            model.addAttribute("sections",list);
        }

        return api?debugAPI(model):"post_new";
    }

    /**
     * 帖子发布请求
     * @param post
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/post/add")
    public void addPost(Post post,HttpServletRequest request,HttpServletResponse response) throws Exception{
        Integer pid = post.pid;
        //不存在保存
        if(pid==null){
            User user = (User)request.getSession().getAttribute("user");
            post.setUid(user.getUid());
            post.setDateline(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            pid = PostDao.getInstance().save(post);
        }else{//存在修改
//            Integer pid = PostDao.getInstance().save(post);
        }

        response.sendRedirect(request.getContextPath()+"/post-"+pid.toString()+".html");

//        Map result = new HashMap();
//        result.put("status",1);
//        result.put("msg","发布帖子成功！");
//        String jsonString = new JSONObject(result).toString();
//
//        response.getWriter().write(jsonString);
//        response.getWriter().close();
    }


}
