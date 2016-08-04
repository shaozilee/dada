package com.shaozilee.dada.pojo;

/**
 *
 * SELECT pid,tid,authorName,authorId,subject,dateLine,message,useIp,invisible,anonymous,status,tags,ppid FROM forum_post
 *
 **/


public class ForumPost {
	public Integer pid;
	public Integer tid;
	public String authorName;
	public Integer authorId;
	public String subject;
	public Long dateLine;
	public String message;
	public String useIp;
	public Boolean invisible;
	public Boolean anonymous;
	public Boolean status;
	public String tags;
	public Integer ppid;
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
	public void setAuthorName(String authorName){
	this.authorName=authorName;
	}
	public String getAuthorName(){
		return authorName;
	}
	public void setAuthorId(Integer authorId){
	this.authorId=authorId;
	}
	public Integer getAuthorId(){
		return authorId;
	}
	public void setSubject(String subject){
	this.subject=subject;
	}
	public String getSubject(){
		return subject;
	}
	public void setDateLine(Long dateLine){
	this.dateLine=dateLine;
	}
	public Long getDateLine(){
		return dateLine;
	}
	public void setMessage(String message){
	this.message=message;
	}
	public String getMessage(){
		return message;
	}
	public void setUseIp(String useIp){
	this.useIp=useIp;
	}
	public String getUseIp(){
		return useIp;
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
}

