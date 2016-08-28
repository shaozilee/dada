package com.shaozilee.dada.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lee on 15-9-13.
 */

@Controller
public class ErrorController {

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping("/404")
    public String error404(Model model) {
        return "404";
    }

    /**
     * 禁止访问
     * @param model
     * @return
     */
    @RequestMapping("/403")
    public String error403(Model model) {
        return "403";
    }
}