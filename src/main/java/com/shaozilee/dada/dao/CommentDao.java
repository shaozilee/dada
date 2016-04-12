package com.shaozilee.dada.dao;

import com.shaozilee.dada.pojo.Comment;
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
public class CommentDao {
    private static CommentDao commentDao = null;
    public static CommentDao getInstance(){
        if(commentDao==null)commentDao = new CommentDao();
        return commentDao;
    }

    public Integer save(Comment comment)throws Exception{
        Integer pid = null;

        Connection con = DS.getConnection();
        String sql = null;
        Integer rcid = comment.getRcid();
        if(rcid != null){
            sql = "INSERT INTO comment(pid,uid,message,dateline,rcid) VALUES (?,?,?,?,?)";
        }else{
            sql  = "INSERT INTO comment(pid,uid,message,dateline) VALUES (?,?,?,?)";
        }


        PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, comment.getPid());
        ps.setInt(2, comment.getUid());
        ps.setString(3, comment.getMessage());
        ps.setString(4, comment.getDateline());
        if(rcid != null){
            ps.setInt(5, rcid);
        }
        ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
        if(resultSet.next()){
            pid = resultSet.getInt(1);
        }

        con.close();
        return pid;
    }

    public List getCommentsByPid(int pid,int start,int len)throws Exception{
        List list = new ArrayList();
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM comment WHERE pid=? ORDER BY dateline DESC limit ?,?");
        ps.setInt(1,pid);
        ps.setInt(2,start);
        ps.setInt(3,len);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(BeanUtil.getBean(rs));
        }
        con.close();
        return list;
    }

    public Comment getCommentByCid(int cid)throws Exception{
        Comment comment = null;
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM comment WHERE cid=?");
        ps.setInt(1,cid);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            comment = (Comment)BeanUtil.getBean(Comment.class,rs);
        }
        con.close();
        return comment;
    }

}
