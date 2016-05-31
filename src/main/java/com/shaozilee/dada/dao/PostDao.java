package com.shaozilee.dada.dao;

import com.shaozilee.dada.pojo.ForumPost;
import com.shaozilee.dada.utils.DS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 15-11-7.
 */
public class PostDao {
    public static Logger logger = LogManager.getLogger(PostDao.class);

    private static PostDao postDao = null;
    public static PostDao getInstance(){
        if(postDao==null)postDao = new PostDao();
        return postDao;
    }

    public ForumPost add(ForumPost post) throws SQLException {
        Connection con = DS.getConnection();
        String sql = "INSERT INTO forum_post(tid,author_name,author_id,subject,date_line,message) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql,new String[]{"pid"});
        ps.setInt(1, post.getTid());
        ps.setString(2,post.getAuthorName());
        ps.setInt(3, post.getAuthorId());
        ps.setString(4, post.getSubject());
        ps.setLong(5, post.getDateLine());
        ps.setString(6,post.getMessage());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs!=null && rs.next())
        {
            post.setPid(rs.getInt(1));
        }
        con.close();
        return post;
    }



}
