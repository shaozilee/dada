package com.shaozilee.dada.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by lee on 15-4-12.
 */
public class DataSourceListener implements ServletContextListener {
    private Logger logger = LogManager.getLogger(DataSourceListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.debug("###################init DBCP#####################");
        DS.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.debug("###################destory DBCP##################");
        DS.destory();
    }
}
