package com.shaozilee.dada.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by lee on 15-4-12.
 */
public class Config {
    private static Logger logger = LogManager.getLogger(Config.class);
    private static Properties properties = null;

    public static String get(String key){
        if(properties == null){
            properties = new Properties();
            try{
                BufferedReader bf = new BufferedReader(new InputStreamReader(Config.class.getResourceAsStream("/config.properties")));
                properties.load(bf);
            }catch (IOException e){
                logger.error(e.getStackTrace());
            }
        }
        return properties.getProperty(key);
    }
}
