package com.shaozilee.dada.dao;

import com.shaozilee.dada.pojo.User;
import com.shaozilee.dada.utils.BeanUtil;
import com.shaozilee.dada.utils.DS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by lee on 15-11-7.
 */
public class UserDao {
    private static UserDao userDao = null;
    public static UserDao getInstance(){
        if(userDao==null)userDao = new UserDao();
        return userDao;
    }

    public Integer addUser(User user)throws Exception{
        Integer uid = null;

        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO user(name,password,email,tel) VALUES (?,?,?,?)",
                                                    Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,user.getName());
        ps.setString(2,user.getPassword());
        ps.setString(3,user.getEmail());
        ps.setString(4,user.getTel());
        ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
        if(resultSet.next()){
            uid = resultSet.getInt(1);
        }

        con.close();
        return uid;
    }

    public User getUserByPassword(String name,String password)throws Exception{
        User user = null;
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE name=? AND password=?");
        ps.setString(1,name);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            user = (User)BeanUtil.getBean(User.class,rs);
        }
        con.close();
        return user;
    }

    public User getUserByUid(Integer uid)throws Exception{
        User user = null;
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE uid=?");
        ps.setInt(1,uid);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            user = (User)BeanUtil.getBean(User.class,rs);
        }
        con.close();
        return user;
    }



}
