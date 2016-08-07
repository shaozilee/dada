package com.shaozilee.dada.dao;

import com.shaozilee.dada.pojo.ForumPost;
import com.shaozilee.dada.utils.BeanUtil;
import com.shaozilee.dada.utils.DS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
        Integer ppid = post.getPpid();
        String sql = "INSERT INTO forum_post(tid,userName,uid,dateLine,message) VALUES (?,?,?,?,?)";
        if(ppid != null){
            sql = "INSERT INTO forum_post(tid,userName,uid,dateLine,message,"+ppid+") VALUES (?,?,?,?,?,?)";
        }

        PreparedStatement ps = con.prepareStatement(sql,new String[]{"pid"});
        ps.setInt(1, post.getTid());
        ps.setString(2, post.getUserName());
        ps.setInt(3, post.getUid());
        ps.setString(4, post.getDateLine());
        ps.setString(5, post.getMessage());
        if(ppid != null){
            ps.setInt(6,ppid);
        }

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs!=null && rs.next())
        {
            post.setPid(rs.getInt(1));
            logger.debug("post:"+rs.getInt(1));
        }
        con.close();
        return post;
    }

    public List getPostsByTid(Integer tid,Integer pageNum,Integer pageSize) throws SQLException{
        int start = (pageNum-1)*pageSize;
        List list = new ArrayList();
        Connection con = DS.getConnection();
        String sql = "SELECT * FROM forum_post  WHERE tid=? ORDER BY pid ASC LIMIT ?,?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,tid);
        ps.setInt(2,start);
        ps.setInt(3,pageSize);
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            list.add(BeanUtil.getBean(resultSet));
        }
        con.close();
        return list;
    }


}
