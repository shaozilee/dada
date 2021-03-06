package com.shaozilee.dada.dao;

import com.shaozilee.dada.pojo.ForumUser;
import com.shaozilee.dada.pojo.ForumUser;
import com.shaozilee.dada.utils.BeanUtil;
import com.shaozilee.dada.utils.DS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lee on 15-11-7.
 */
public class UserDao {
    public static Logger logger = LogManager.getLogger(UserDao.class);

    private static UserDao topicDao = null;
    public static UserDao getInstance(){
        if(topicDao==null)topicDao = new UserDao();
        return topicDao;
    }


    public ForumUser add(ForumUser user) throws SQLException {
        Connection con = DS.getConnection();
        String sql = "INSERT INTO forum_user(userName,password,email,regDate) VALUES (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql,new String[]{"uid"});
        ps.setString(1,user.getUserName());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getRegDate());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs!=null && rs.next())
        {
            user.setUid(rs.getInt(1));
        }
        con.close();
        return user;
    }

    public ForumUser getUserByEmail(String email) throws SQLException {
        ForumUser user = null;
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_user WHERE email = ?");
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            user = (ForumUser)BeanUtil.getBean(ForumUser.class,rs);
        }
        con.close();
        return user;
    }

    public ForumUser getUserByUserName(String userName) throws SQLException {
        ForumUser user = null;
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_user WHERE userName = ?");
        ps.setString(1,userName);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            user = (ForumUser)BeanUtil.getBean(ForumUser.class,rs);
        }
        con.close();
        return user;
    }

    public boolean addPoints(Integer uid,Integer points) throws SQLException{
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE forum_user SET points=points+? WHERE uid=?");
        ps.setInt(1,points);
        ps.setInt(2,uid);
        int rows = ps.executeUpdate();
        con.close();
        return rows>0;
    }

    public boolean addCredits(Integer uid,Integer credits) throws SQLException{
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE forum_user SET credits=credits+? WHERE uid=?");
        ps.setInt(1,credits);
        ps.setInt(2,uid);
        int rows = ps.executeUpdate();
        con.close();
        return rows>0;
    }


    public ForumUser getNewRegistUser() throws SQLException {
        ForumUser user = null;
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_user ORDER BY regDate DESC LIMIT 1");
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            user = (ForumUser)BeanUtil.getBean(ForumUser.class,rs);
        }
        con.close();
        return user;
    }

    public boolean setLastLoginTime(Integer uid,String date) throws SQLException{
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE forum_user SET lastLoginTime=? WHERE uid=?");
        ps.setString(1, date);
        ps.setInt(2,uid);
        int rows = ps.executeUpdate();
        con.close();
        return rows>0;
    }



}
