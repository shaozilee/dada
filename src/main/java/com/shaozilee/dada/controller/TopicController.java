package com.shaozilee.dada.controller;

import com.shaozilee.dada.dao.TopicDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by lee on 15-9-13.
 */

@Controller
public class TopicController extends AbstractController{

    @RequestMapping("/index")
    public String index(@RequestParam(value="api", required=false, defaultValue="false") boolean api, Model model) throws Exception{
        //TODO 获取主题统计数据

        //获取第一页主题数据
        List topicList = TopicDao.getInstance().getTopics(1,10);
        model.addAttribute("topicList",topicList);
        return api?debugAPI(model):"index";
    }
}