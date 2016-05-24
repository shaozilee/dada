package com.shaozilee.dada.dao;

import com.shaozilee.dada.utils.DS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 15-11-7.
 */
public class AttachmentDao {
    public static Logger logger = LogManager.getLogger(AttachmentDao.class);

    private static AttachmentDao attachmentDao = null;
    public static AttachmentDao getInstance(){
        if(attachmentDao==null)attachmentDao = new AttachmentDao();
        return attachmentDao;
    }

    public void addAttachment()throws Exception{
        Connection con = DS.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_topic ORDER BY tid DESC LIMIT ?,?");
//        ps.setInt(1,start);
//        ps.setInt(2,pageSize);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            //list.add(BeanUtil.getBean(rs));
            logger.debug("has data");
        }
        con.close();
    }



}
