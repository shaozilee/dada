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
import java.util.ArrayList;
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
        logger.debug("start:"+start);
        List list = new ArrayList();
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_topic ORDER BY tid DESC LIMIT ?,?");
        ps.setInt(1,start);
        ps.setInt(2,pageSize);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(BeanUtil.getBean(rs));
            logger.debug("has data");
        }
        con.close();
        return list;
    }


    public ForumTopic add(ForumTopic topic) throws SQLException {
        Connection con = DS.getConnection();
        String sql = "INSERT INTO forum_topic(author_name,author_id,subject,date_line,last_post,last_poster) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql,new String[]{"tid"});
        ps.setString(1,topic.getAuthorName());
        ps.setInt(2,topic.getAuthorId());
        ps.setString(3,topic.getSubject());
        ps.setLong(4,topic.getDateLine());
        ps.setLong(5,topic.getLastPost());
        ps.setString(6,topic.getLastPoster());
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

    public Map getTopicByTid(Integer tid)throws  SQLException{
        Map topic = null;
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT tid,read_perm,author_name,author_id,subject,date_line,last_post,last_poster,views,replies,display_order,high_light,digest,closed,stick_reply,status,fav_times,share_times,stamp,icon FROM forum_topic WHERE tid = ?");
        ps.setInt(1,tid);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            topic = BeanUtil.getBean(rs);
        }
        con.close();
        return topic;
    }



}
