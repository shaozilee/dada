package com.shaozilee.dada.pojo;

/**
 *
 * SELECT pid,sid,uid,subject,message,dateline,status,view_count,comment_count FROM post
 *
 **/


public class Post{
	public Integer pid;
	public Integer sid;
	public Integer uid;
	public String subject;
	public String message;
	public String dateline;
	public Integer status;
	public Integer view_count;
	public Integer comment_count;
	public void setPid(Integer pid){
	this.pid=pid;
	}
	public Integer getPid(){
		return pid;
	}
	public void setSid(Integer sid){
	this.sid=sid;
	}
	public Integer getSid(){
		return sid;
	}
	public void setUid(Integer uid){
	this.uid=uid;
	}
	public Integer getUid(){
		return uid;
	}
	public void setSubject(String subject){
	this.subject=subject;
	}
	public String getSubject(){
		return subject;
	}
	public void setMessage(String message){
	this.message=message;
	}
	public String getMessage(){
		return message;
	}
	public void setDateline(String dateline){
	this.dateline=dateline;
	}
	public String getDateline(){
		return dateline;
	}
	public void setStatus(Integer status){
	this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setViewCount(Integer view_count){
	this.view_count=view_count;
	}
	public Integer getViewCount(){
		return view_count;
	}
	public void setCommentCount(Integer comment_count){
	this.comment_count=comment_count;
	}
	public Integer getCommentCount(){
		return comment_count;
	}
}

