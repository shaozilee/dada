package com.shaozilee.dada.controller;

import com.shaozilee.dada.utils.AjaxCode;
import com.shaozilee.dada.utils.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lee on 15-11-7.
 */

public abstract class AbstractController {
    private static Logger logger = LogManager.getLogger(AbstractController.class);
    private static Map CODE_CN = new HashMap();

    static {
        CODE_CN.put("0000","请求成功!");
    }



    protected String debugAPI(Model model){
        String data = new JSONObject(model.asMap()).toString();
        model.addAttribute("api",data);
        return "api";
    }

    /**
     * only data on success
     * @param data
     * @param response
     * @throws IOException
     */
    protected String toJson(Object data,HttpServletResponse response){
        return toJson(data, AjaxCode.SUC, response);
    }

    /**
     * only return code no data
     * @param code
     * @param response
     * @throws IOException
     */
    protected String toJson(String code,HttpServletResponse response){
        return toJson(null,code,response);
    }

    protected String toJson(Object data,String code,HttpServletResponse response){
        HashMap map = new HashMap();
        map.put("code",code);
        map.put("data",data);
        String msg = Config.get("code.CODE_" + code);
        if(msg==null)msg = "";
        map.put("msg",msg);


        JSONObject resp = new JSONObject(map);
        String jsonStr = resp.toString();
        try{
            response.getWriter().write(jsonStr);
            response.getWriter().close();
        }catch (IOException e){
            logger.error(e.getMessage());
        }
        return jsonStr;

    }


}
