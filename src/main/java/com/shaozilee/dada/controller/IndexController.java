package com.shaozilee.dada.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by lee on 15-9-13.
 */

@Controller
public class IndexController extends AbstractController{

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(@RequestParam(value="api", required=false, defaultValue="false") boolean api, Model model) throws Exception{
//        List list = SectionDao.getInstance().getTopSections();
//        model.addAttribute("sections",list);
        return api?debugAPI(model):"index";
    }
}