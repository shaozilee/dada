package com.shaozilee.dada.pojo;

/**
 *
 * SELECT pid,tid,author_name,author_id,subject,date_line,message,useIp,invisible,anonymous,status,tags FROM forum_post
 *
 **/


public class ForumPost {
	public Integer pid;
	public Integer tid;
	public String author_name;
	public Integer author_id;
	public String subject;
	public Long date_line;
	public String message;
	public String useIp;
	public Boolean invisible;
	public Boolean anonymous;
	public Boolean status;
	public String tags;
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
	public void setAuthorName(String author_name){
	this.author_name=author_name;
	}
	public String getAuthorName(){
		return author_name;
	}
	public void setAuthorId(Integer author_id){
	this.author_id=author_id;
	}
	public Integer getAuthorId(){
		return author_id;
	}
	public void setSubject(String subject){
	this.subject=subject;
	}
	public String getSubject(){
		return subject;
	}
	public void setDateLine(Long date_line){
	this.date_line=date_line;
	}
	public Long getDateLine(){
		return date_line;
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
}

