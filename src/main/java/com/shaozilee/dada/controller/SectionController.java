package com.shaozilee.dada.controller;

import com.shaozilee.dada.dao.PostDao;
import com.shaozilee.dada.dao.SectionDao;
import com.shaozilee.dada.pojo.Section;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by lee on 15-11-20.
 */

@Controller
public class SectionController extends AbstractController {
    public static int PAGE_SIZE = 2;

    @RequestMapping("/section-{sid}-{page}")
    public String section(@PathVariable Integer sid,@PathVariable Integer page,@RequestParam(value = "api",required = false) boolean api,Model model) throws Exception{
        if(page == null)page=1;
        Section section = SectionDao.getInstance().getSectionBySid(sid);
        List sectionList = SectionDao.getInstance().getSectionsByParentId(sid);
        List postList = PostDao.getInstance().getPostsBySid(sid, page, PAGE_SIZE);
        //计算分页
        int postTotal = PostDao.getInstance().getTotalBySid(sid);
        int pageTotal = (int)Math.ceil((double)postTotal/PAGE_SIZE);
        boolean pageHasNext = page<pageTotal;
        boolean pageHasPre = page>1;

        int start,end,max = 9;
        int half = max/2;

        if(pageTotal>max){//总页数大于页码个数
            if(page-half>0 && page+half<=pageTotal){
                start = page-half;
                end = page+half;
            }else if(page-half>0){
                //接近末尾
                start = pageTotal-2*half;
                end = pageTotal;
            }else{
                start = 1;
                end = max;
            }
        }else{
            start = 1;
            end = pageTotal;
        }

        model.addAttribute("sections",sectionList);
        model.addAttribute("posts",postList);
        model.addAttribute("section",section);
        model.addAttribute("page",page);
        model.addAttribute("postTotal",postTotal);
        model.addAttribute("pageTotal",pageTotal);
        model.addAttribute("pageHasNext",pageHasNext);
        model.addAttribute("pageHasPre",pageHasPre);
        model.addAttribute("pagePre",pageHasPre?page-1:1);
        model.addAttribute("pageNext",pageHasNext?page+1:pageTotal);
        model.addAttribute("start",start);
        model.addAttribute("end",end);


        return api?debugAPI(model):"section";
    }
}
