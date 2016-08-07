package com.shaozilee.dada.pojo;

/**
 *
 * SELECT pid,tid,userName,uid,dateLine,message,postIp,invisible,anonymous,status,tags,ppid,puserName,puid,photo FROM forum_post
 *
 **/


public class ForumPost {
	public Integer pid;
	public Integer tid;
	public String userName;
	public Integer uid;
	public String dateLine;
	public String message;
	public String postIp;
	public Boolean invisible;
	public Boolean anonymous;
	public Boolean status;
	public String tags;
	public Integer ppid;
	public String puserName;
	public Integer puid;
	public String photo;
	public void setPid(Integer pid){
	this.pid=pid;
	}
	public Integer getPid(){
		return pid;
	}
	public void setTid(Integer tid){
	this.tid=tid;
	}
	public Integer getTid(){
		return tid;
	}
	public void setUserName(String userName){
	this.userName=userName;
	}
	public String getUserName(){
		return userName;
	}
	public void setUid(Integer uid){
	this.uid=uid;
	}
	public Integer getUid(){
		return uid;
	}
	public void setDateLine(String dateLine){
	this.dateLine=dateLine;
	}
	public String getDateLine(){
		return dateLine;
	}
	public void setMessage(String message){
	this.message=message;
	}
	public String getMessage(){
		return message;
	}
	public void setPostIp(String postIp){
	this.postIp=postIp;
	}
	public String getPostIp(){
		return postIp;
	}
	public void setInvisible(Boolean invisible){
	this.invisible=invisible;
	}
	public Boolean getInvisible(){
		return invisible;
	}
	public void setAnonymous(Boolean anonymous){
	this.anonymous=anonymous;
	}
	public Boolean getAnonymous(){
		return anonymous;
	}
	public void setStatus(Boolean status){
	this.status=status;
	}
	public Boolean getStatus(){
		return status;
	}
	public void setTags(String tags){
	this.tags=tags;
	}
	public String getTags(){
		return tags;
	}
	public void setPpid(Integer ppid){
	this.ppid=ppid;
	}
	public Integer getPpid(){
		return ppid;
	}
	public void setPuserName(String puserName){
	this.puserName=puserName;
	}
	public String getPuserName(){
		return puserName;
	}
	public void setPuid(Integer puid){
	this.puid=puid;
	}
	public Integer getPuid(){
		return puid;
	}
	public void setPhoto(String photo){
	this.photo=photo;
	}
	public String getPhoto(){
		return photo;
	}
}

