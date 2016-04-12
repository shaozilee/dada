package com.shaozilee.dada.dao;

import com.shaozilee.dada.pojo.Section;
import com.shaozilee.dada.utils.BeanUtil;
import com.shaozilee.dada.utils.DS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 15-11-7.
 */
public class SectionDao {
    private static SectionDao sectionDao = null;
    public static SectionDao getInstance(){
        if(sectionDao==null)sectionDao = new SectionDao();
        return sectionDao;
    }

    /**
     * 获取顶级板块
     * @return
     * @throws Exception
     */
    public List getTopSections()throws Exception{
        List list = new ArrayList();
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT sid,name,status,display_order,notice,title,img FROM section WHERE parent_sid IS NULL ORDER BY display_order DESC");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(BeanUtil.getBean(rs));
        }
        con.close();
        return list;
    }

    public List getSectionsByParentId(int sid)throws Exception{
        List list = new ArrayList();
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT sid,name,status,display_order,notice,title,img FROM section WHERE parent_sid=? ORDER BY display_order DESC");
        ps.setInt(1,sid);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            list.add(BeanUtil.getBean(rs));
        }
        con.close();
        return list;
    }

    public Section getSectionBySid(int sid)throws Exception{
        Section section = null;
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT sid,name,status,display_order,notice,title,img FROM section WHERE sid=?");
        ps.setInt(1,sid);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            section = (Section)BeanUtil.getBean(Section.class,rs);
        }
        con.close();
        return section;
    }

}
