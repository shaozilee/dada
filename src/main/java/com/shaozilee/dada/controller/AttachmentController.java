package com.shaozilee.dada.controller;

import com.shaozilee.dada.utils.AjaxCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lee on 15-9-13.
 */

@Controller
public class AttachmentController extends AbstractController{
    public static Logger logger = LogManager.getLogger(AttachmentController.class);

    @RequestMapping("/doUpload")
    public void doUpload(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        String jsonStr = toJson(AjaxCode.SUC, response);
        logger.debug(jsonStr);
    }

}