package com.shaozilee.dada.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.ui.Model;

/**
 * Created by lee on 15-11-7.
 */

public abstract class AbstractController {
    private static Logger logger = LogManager.getLogger(AbstractController.class);

    protected String debugAPI(Model model){
        String data = new JSONObject(model.asMap()).toString();
        model.addAttribute("api",data);
        return "api";
    }
}
