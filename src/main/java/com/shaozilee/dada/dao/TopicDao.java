package com.shaozilee.dada.dao;

import com.shaozilee.dada.pojo.ForumTopic;
import com.shaozilee.dada.utils.BeanUtil;
import com.shaozilee.dada.utils.DS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lee on 15-11-7.
 */
public class TopicDao {
    public static Logger logger = LogManager.getLogger(TopicDao.class);

    private static TopicDao topicDao = null;
    public static TopicDao getInstance(){
        if(topicDao==null)topicDao = new TopicDao();
        return topicDao;
    }

    public List getTopics(int pageNum,int pageSize)throws Exception{
        int start = (pageNum-1)*pageSize;
        List list = new ArrayList();
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_topic ORDER BY lastPostDate DESC LIMIT ?,?");
        ps.setInt(1,start);
        ps.setInt(2,pageSize);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(BeanUtil.getBean(rs));
        }
        con.close();
        return list;
    }


    public ForumTopic add(ForumTopic topic) throws SQLException {
        Connection con = DS.getConnection();
        String sql = "INSERT INTO forum_topic(userName,uid,subject,dateLine,lastPostDate,lastPoster,message) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql,new String[]{"tid"});
        ps.setString(1,topic.getUserName());
        ps.setInt(2, topic.getUid());
        ps.setString(3, topic.getSubject());
        ps.setString(4, topic.getDateLine());
        ps.setString(5,topic.getLastPostDate());
        ps.setString(6, topic.getLastPoster());
        ps.setString(7,topic.getMessage());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs!=null && rs.next())
        {
            topic.setTid(rs.getInt(1));
        }
        con.close();
        return topic;
    }

    public Integer getTotalCount()throws SQLException{
        Integer totalCount = 0;
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT COUNT(tid) FROM forum_topic");
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            totalCount = rs.getInt(1);
        }
        con.close();
        return totalCount;
    }

    public Integer getTodayCount()throws SQLException{
        Integer totalCount = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String startDate = simpleDateFormat.format(today)+" 00:00:00";
        String endDate = simpleDateFormat.format(new Date(today.getTime()+24*60*60*1000))+" 00:00:00";

        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT COUNT(tid) FROM forum_topic WHERE dateLine between ? and ?");
        ps.setString(1,startDate);
        ps.setString(2,endDate);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            totalCount = rs.getInt(1);
        }
        con.close();
        return totalCount;
    }


    public Map getTopicByTid(Integer tid)throws  SQLException{
        Map topic = null;
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_topic WHERE tid = ?");
        ps.setInt(1,tid);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            topic = BeanUtil.getBean(rs);
        }
        con.close();
        return topic;
    }


    public Integer getPostCountByTid(Integer tid)throws SQLException{
        Integer totalCount = 0;
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT COUNT(pid) FROM forum_post WHERE tid = ?");
        ps.setInt(1,tid);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            totalCount = rs.getInt(1);
        }
        con.close();
        return totalCount;
    }

    public Boolean update(ForumTopic topic) throws SQLException {
        Connection con = DS.getConnection();
        String sql = "UPDATE forum_topic SET subject=?,message=?,lastPostDate=?,lastPoster=? WHERE tid=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, topic.getSubject());
        ps.setString(2,topic.getMessage());
        ps.setString(3,topic.getLastPostDate());
        ps.setString(4, topic.getLastPoster());
        ps.setInt(5, topic.getTid());
        int rows = ps.executeUpdate();
        con.close();
        return rows>0;
    }




}
