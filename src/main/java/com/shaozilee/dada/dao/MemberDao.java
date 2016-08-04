package com.shaozilee.dada.dao;

import com.shaozilee.dada.pojo.CommonMember;
import com.shaozilee.dada.pojo.ForumTopic;
import com.shaozilee.dada.utils.BeanUtil;
import com.shaozilee.dada.utils.DS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Member;
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
public class MemberDao {
    public static Logger logger = LogManager.getLogger(MemberDao.class);

    private static MemberDao topicDao = null;
    public static MemberDao getInstance(){
        if(topicDao==null)topicDao = new MemberDao();
        return topicDao;
    }


    public CommonMember add(CommonMember member) throws SQLException {
        Connection con = DS.getConnection();
        String sql = "INSERT INTO common_member(userName,password,email,regDate) VALUES (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql,new String[]{"uid"});
        ps.setString(1,member.getUserName());
        ps.setString(2, member.getPassword());
        ps.setString(3, member.getEmail());
        ps.setLong(4, member.getRegDate());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs!=null && rs.next())
        {
            member.setUid(rs.getInt(1));
        }
        con.close();
        return member;
    }

    public CommonMember getUserByEmail(String email) throws SQLException {
        CommonMember member = null;
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT uid,userName,password,email,status,emailStatus,regDate,credits,lastLoginIp,lastLoginTime FROM common_member WHERE email = ?");
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            member = (CommonMember)BeanUtil.getBean(CommonMember.class,rs);
        }
        con.close();
        return member;
    }



}
