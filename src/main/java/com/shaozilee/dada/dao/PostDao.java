package com.shaozilee.dada.dao;

import com.shaozilee.dada.pojo.Post;
import com.shaozilee.dada.utils.BeanUtil;
import com.shaozilee.dada.utils.DS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 15-11-7.
 */
public class PostDao {
    private static PostDao postDao = null;
    public static PostDao getInstance(){
        if(postDao==null)postDao = new PostDao();
        return postDao;
    }

    public List getPostsBySid(int sid,int page,int len)throws Exception{
        List list = new ArrayList();
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM post WHERE sid=? ORDER BY dateline DESC limit ?,?");
        ps.setInt(1,sid);
        ps.setInt(2,(page-1)*len);
        ps.setInt(3,len);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(BeanUtil.getBean(rs));
        }
        con.close();
        return list;
    }

    public Integer save(Post post)throws Exception{
        Integer pid = null;

        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO post(sid,uid,subject,message,dateline) VALUES (?,?,?,?,?)",
                                                    Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, post.getSid());
        ps.setInt(2, post.getUid());
        ps.setString(3, post.getSubject());
        ps.setString(4, post.getMessage());
        ps.setString(5, post.getDateline());
        ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
        if(resultSet.next()){
            pid = resultSet.getInt(1);
        }

        con.close();
        return pid;
    }

    public Post getPostByPid(int pid)throws Exception{
        Post post = null;
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM post WHERE pid=?");
        ps.setInt(1,pid);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            post = (Post)BeanUtil.getBean(Post.class,rs);
        }
        con.close();
        return post;
    }

    public int getTotalBySid(int sid)throws Exception{
        int total = 0;
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT count(pid) FROM post WHERE sid=?");
        ps.setInt(1,sid);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            total = rs.getInt(1);
        }
        con.close();
        return total;
    }

}
