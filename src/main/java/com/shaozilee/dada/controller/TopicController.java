package com.shaozilee.dada.controller;

import com.shaozilee.dada.dao.PostDao;
import com.shaozilee.dada.dao.TopicDao;
import com.shaozilee.dada.pojo.ForumPost;
import com.shaozilee.dada.pojo.ForumTopic;
import com.shaozilee.dada.pojo.ForumUser;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
    public void index(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        request.getRequestDispatcher("index-1.html").forward(request,response);
    }

    @RequestMapping("/index-{page}")
    public String indexByPage(@PathVariable Integer page,@RequestParam(value="api", required=false, defaultValue="false") boolean api, Model model) throws Exception{
        int totalCount = TopicDao.getInstance().getTotalCount();
        int todayCount = TopicDao.getInstance().getTodayCount();

        int totalPage = (int)Math.ceil((double)totalCount / TOPIC_PAGE_SIZE);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("hasPre",page>1?true:false);
        model.addAttribute("hasNext",page<totalPage?true:false);
        model.addAttribute("currentPage",page);
        model.addAttribute("todayCount",todayCount);


        //获取第一页主题数据
        List topicList = TopicDao.getInstance().getTopics(page, TOPIC_PAGE_SIZE);
        model.addAttribute("topicList",topicList);
        return api?debugAPI(model):"index";
    }

    @RequestMapping("/topic-{tid}-{page}")
    public String topic(@PathVariable Integer tid, @PathVariable Integer page, @RequestParam(value="api", required=false, defaultValue="false") boolean api,Model model) throws Exception{
        Map topic = TopicDao.getInstance().getTopicByTid(tid);
        TopicDao.getInstance().increaseViews(tid);

        model.addAttribute("topic",topic);
        model.addAttribute("tid",tid);
        model.addAttribute("page",page);

        PostDao postDao = PostDao.getInstance();
        //获取一级回复的计数
        int totalCount = postDao.getPostTotalCount(tid,false);
        int totalCount1 = postDao.getPostTotalCount(tid, true);

        int totalPage = (int)Math.ceil((double)totalCount1 / POST_PAGE_SIZE);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("hasPre",page>1?true:false);
        model.addAttribute("hasNext",page<totalPage?true:false);
        model.addAttribute("currentPage",page);
        //获取一级回复
        List list = postDao.getPostsByTid(tid, page,POST_PAGE_SIZE);
        //组装子级回复的ppid
        ArrayList<Integer> ppids = new ArrayList<Integer>();
        Iterator<Map> iterator = list.iterator();
        while(iterator.hasNext()){
            ppids.add((Integer)iterator.next().get("pid"));
        }
        Map<String,ArrayList> ppMap = new HashMap<String,ArrayList>();
        //如果没有一级回复则不处理
        if(ppids.size()>0){
            //获取所有的子级Post
            List ppList = postDao.getPPostList(ppids);
            Iterator<Map> iterator1 = ppList.iterator();
            while(iterator1.hasNext()){
                Map tempPPost = iterator1.next();
                String tempPPid = tempPPost.get("ppid").toString();
                ArrayList tempPPostList = null;
                if(ppMap.containsKey(tempPPid)){
                    tempPPostList = ppMap.get(tempPPid);
                }else{
                    tempPPostList = new ArrayList();
                }
                tempPPostList.add(tempPPost);
                ppMap.put(tempPPid,tempPPostList);
            }
        }

        model.addAttribute("postList",list);
        model.addAttribute("ppMap",ppMap);
        return api?debugAPI(model):"topic";
    }


    @RequestMapping("/topic/new")
    public String newTopic(Model model) throws Exception{
        return "new";
    }

    @RequestMapping("/topic/save")
    public void saveTopic(ForumTopic topic,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        if(topic.getSubject() == null || "".equals(topic.getSubject())){
            toJson(AjaxCode.ERR_NULL, response);
            return;
        }
        if(topic.getMessage() == null || "".equals(topic.getMessage())){
            toJson(AjaxCode.ERR_NULL, response);
            return;
        }

        ForumUser user = (ForumUser)request.getSession().getAttribute("user");
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        TopicDao topicDao = TopicDao.getInstance();
        topic.setUid(user.getUid());
        topic.setUserName(user.getUserName());
        topic.setDateLine(date);
        topic.setLastPostDate(date);
        topic.setLastPoster(user.getUserName());
        topicDao.add(topic);

        String jsonStr = toJson(AjaxCode.SUC, response);
        logger.debug(jsonStr);
    }

    @RequestMapping("/post/save")
    public void savePost(ForumPost f,@RequestParam(value="api", required=false, defaultValue="false") boolean api,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        ForumUser user = (ForumUser)request.getSession().getAttribute("user");
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        PostDao postDao = PostDao.getInstance();
        f.setDateLine(date);
        f.setUid(user.getUid());
        f.setUserName(user.getUserName());

        f = postDao.add(f);
        if(f != null){
            TopicDao.getInstance().increaseReplies(f.getTid());
        }

        String jsonStr = toJson(f,AjaxCode.SUC, response);
        logger.debug(jsonStr);
    }


    @RequestMapping("/edit-topic-{tid}")
    public String topic(@PathVariable Integer tid, @RequestParam(value="api", required=false, defaultValue="false") boolean api,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
        Map topic = TopicDao.getInstance().getTopicByTid(tid);
        ForumUser user = (ForumUser)request.getSession().getAttribute("user");
        String url = "edit-topic";
        if(user != null && topic != null && user.getUid() == (Integer)topic.get("uid")){
            model.addAttribute("topic", topic);
        }else{
            url = "403";
        }

        //获取一级回复
        return api?debugAPI(model):url;
    }


    @RequestMapping("/topic/edit")
    public void editTopic(ForumTopic topic,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        if(topic.getSubject() == null || "".equals(topic.getSubject())){
            toJson(AjaxCode.ERR_NULL, response);
            return;
        }
        if(topic.getMessage() == null || "".equals(topic.getMessage())){
            toJson(AjaxCode.ERR_NULL, response);
            return;
        }

        ForumUser user = (ForumUser)request.getSession().getAttribute("user");
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        TopicDao topicDao = TopicDao.getInstance();
        topic.setUid(user.getUid());
        topic.setLastPostDate(date);
        topic.setLastPoster(user.getUserName());
        topicDao.update(topic);

        String jsonStr = toJson(AjaxCode.SUC, response);
        logger.debug(jsonStr);
    }

}