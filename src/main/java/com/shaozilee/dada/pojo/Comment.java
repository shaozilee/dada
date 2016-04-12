package com.shaozilee.dada.pojo;

/**
 *
 * SELECT cid,pid,uid,message,dateline,rcid,status FROM comment
 *
 **/


public class Comment{
	public Integer cid;
	public Integer pid;
	public Integer uid;
	public String message;
	public String dateline;
	public Integer rcid;
	public Integer status;
	public void setCid(Integer cid){
	this.cid=cid;
	}
	public Integer getCid(){
		return cid;
	}
	public void setPid(Integer pid){
	this.pid=pid;
	}
	public Integer getPid(){
		return pid;
	}
	public void setUid(Integer uid){
	this.uid=uid;
	}
	public Integer getUid(){
		return uid;
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
	public void setRcid(Integer rcid){
	this.rcid=rcid;
	}
	public Integer getRcid(){
		return rcid;
	}
	public void setStatus(Integer status){
	this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
}

