package com.shaozilee.dada.controller;

import com.shaozilee.dada.dao.PostDao;
import com.shaozilee.dada.dao.TopicDao;
import com.shaozilee.dada.pojo.ForumPost;
import com.shaozilee.dada.pojo.ForumTopic;
import com.shaozilee.dada.utils.AjaxCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by lee on 15-9-13.
 */

@Controller
public class TopicController extends AbstractController{
    private static Logger logger = LogManager.getLogger(TopicController.class);


    @RequestMapping("/index")
    public String index(@RequestParam(value="api", required=false, defaultValue="false") boolean api, Model model) throws Exception{
        //TODO 获取主题统计数据

        //获取第一页主题数据
//        List topicList = TopicDao.getInstance().getTopics(1,10);
//        model.addAttribute("topicList",topicList);
        return api?debugAPI(model):"index";
    }

    @RequestMapping("/topic/new")
    public String newTopic(Model model) throws Exception{
        return "new";
    }

    @RequestMapping("/topic/{tid}-{page}")
    public String topic(@PathVariable Integer tid, @PathVariable Integer page,Model model) throws Exception{
        PostDao postDao = PostDao.getInstance();
        List list = postDao.getPostsByTid(tid, page);

        return "topic";
    }

    @RequestMapping("/topic/save")
    public void saveTopic(ForumPost f,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        Long time = new Date().getTime();


        TopicDao topicDao = TopicDao.getInstance();
        ForumTopic topic = new ForumTopic();
        topic.setAuthorId(0);
        topic.setAuthorName("lishg");
        topic.setDateLine(time);
        topic.setLastPost(time);
        topic.setLastPoster("lishg");
        topic.setSubject(f.getSubject());
        topicDao.add(topic);


        PostDao postDao = PostDao.getInstance();
        f.setDateLine(time);
        f.setAuthorId(0);
        f.setAuthorName("lishg");
        f.setTid(topic.getTid());
        postDao.add(f);


        String jsonStr = toJson(AjaxCode.SUC, response);
        logger.debug(jsonStr);
    }

    @RequestMapping("/post/save")
    public void savePost(@RequestParam(value="api", required=false, defaultValue="false") boolean api,@RequestParam(value="redirect", required=false, defaultValue="false") String redirect,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        String jsonStr = toJson(AjaxCode.SUC, response);
        logger.debug(jsonStr);
    }




}