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
import java.util.Map;

/**
 * Created by lee on 15-9-13.
 */

@Controller
public class TopicController extends AbstractController{
    private static Logger logger = LogManager.getLogger(TopicController.class);
    private static Integer TOPIC_PAGE_SIZE = 10;
    private static Integer POST_PAGE_SIZE = 10;

    @RequestMapping("/index")
    public String index(@RequestParam(value="api", required=false, defaultValue="false") boolean api, Model model) throws Exception{
        Integer page = 1;
        int totalCount = TopicDao.getInstance().getTotalCount();
        int totalPage = (int)Math.ceil((double)totalCount / TOPIC_PAGE_SIZE);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("hasPre",page>1?true:false);
        model.addAttribute("hasNext",page<totalPage?true:false);
        model.addAttribute("currentPage",page);

        //获取第一页主题数据
        List topicList = TopicDao.getInstance().getTopics(1,TOPIC_PAGE_SIZE);
        model.addAttribute("topicList",topicList);
        return api?debugAPI(model):"index";
    }

    @RequestMapping("/index-{page}")
    public String indexByPage(@PathVariable Integer page,@RequestParam(value="api", required=false, defaultValue="false") boolean api, Model model) throws Exception{
        int totalCount = TopicDao.getInstance().getTotalCount();
        int totalPage = (int)Math.ceil((double)totalCount / TOPIC_PAGE_SIZE);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("hasPre",page>1?true:false);
        model.addAttribute("hasNext",page<totalPage?true:false);
        model.addAttribute("currentPage",page);

        //获取第一页主题数据
        List topicList = TopicDao.getInstance().getTopics(page, TOPIC_PAGE_SIZE);
        model.addAttribute("topicList",topicList);
        return api?debugAPI(model):"index";
    }

    @RequestMapping("/topic-{tid}-{page}")
    public String topic(@PathVariable Integer tid, @PathVariable Integer page, @RequestParam(value="api", required=false, defaultValue="false") boolean api,Model model) throws Exception{
        Map topic = TopicDao.getInstance().getTopicByTid(tid);
        model.addAttribute("topic",topic);

        PostDao postDao = PostDao.getInstance();
        List list = postDao.getPostsByTid(tid, page,POST_PAGE_SIZE);
        model.addAttribute("postList",list);
        return api?debugAPI(model):"topic";
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
    public void savePost(ForumPost f,@RequestParam(value="api", required=false, defaultValue="false") boolean api,@RequestParam(value="redirect", required=false, defaultValue="false") String redirect,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        Long time = new Date().getTime();

        PostDao postDao = PostDao.getInstance();
        f.setDateLine(time);
        f.setAuthorId(0);
        f.setAuthorName("lishg");
        f.setSubject("");

        f = postDao.add(f);


        String jsonStr = toJson(f,AjaxCode.SUC, response);
        logger.debug(jsonStr);
    }

    @RequestMapping("/topic/new")
    public String newTopic(Model model) throws Exception{
        return "new";
    }




}