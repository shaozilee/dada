package com.shaozilee.dada.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by lee on 15-4-12.
 */
public class DS {
    private static BasicDataSource dbcp = null;

    public static void init(){
        dbcp = new BasicDataSource();
        dbcp.setDriverClassName(Config.get("dbcp.driverClassName"));
        dbcp.setUrl(Config.get("dbcp.url"));
        dbcp.setUsername(Config.get("dbcp.userName"));
        dbcp.setPassword(Config.get("dbcp.password"));
        dbcp.setInitialSize(Integer.parseInt(Config.get("dbcp.initialSize")));
        dbcp.setDefaultAutoCommit(Boolean.parseBoolean(Config.get("dbcp.defaultAutoCommit")));
    }

    public static Connection getConnection()throws SQLException{
        return dbcp.getConnection();
    }

    public static void destory(){
        try{
            dbcp.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}
