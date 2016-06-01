package com.shaozilee.dada.controller;

import com.shaozilee.dada.utils.AjaxCode;
import com.shaozilee.dada.utils.Config;
import com.shaozilee.dada.utils.Uploader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by lee on 15-9-13.
 */

@Controller
public class AttachmentController extends AbstractController{
    public static Logger logger = LogManager.getLogger(AttachmentController.class);

    @RequestMapping("/doUploadImage")
    public void doUploadImage(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
        String savePath = Config.get("attachment.path");
        Uploader up = new Uploader(request);
        up.setSavePath(savePath);
        up.setMaxSize(2000);
        up.upload();

        HashMap map = new HashMap();
        map.put("code",AjaxCode.SUC);
        map.put("name",up.getFileName());
        map.put("originalName",up.getOriginalName());
        map.put("size",up.getSize());
        map.put("state",up.getState());
        map.put("type",up.getType());
        map.put("url",up.getUrl());

        JSONObject resp = new JSONObject(map);
        String jsonStr = resp.toString();
        try{
            response.getWriter().write(jsonStr);
            response.getWriter().close();
        }catch (IOException e){
            logger.error(e.getMessage());
        }

        logger.debug(jsonStr);
    }

}
