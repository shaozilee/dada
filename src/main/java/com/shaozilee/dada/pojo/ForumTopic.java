package com.shaozilee.dada.pojo;

/**
 *
 * SELECT tid,read_perm,author_name,author_id,subject,date_line,last_post,last_poster,views,replies,display_order,high_light,digest,closed,stick_reply,status,fav_times,share_times,stamp,icon FROM forum_topic
 *
 **/


public class ForumTopic {
	public Integer tid;
	public Integer read_perm;
	public String author_name;
	public Integer author_id;
	public String subject;
	public Long date_line;
	public Long last_post;
	public String last_poster;
	public Integer views;
	public Integer replies;
	public Integer display_order;
	public Boolean high_light;
	public Boolean digest;
	public Integer closed;
	public Boolean stick_reply;
	public Integer status;
	public Integer fav_times;
	public Integer share_times;
	public Integer stamp;
	public Integer icon;
	public void setTid(Integer tid){
	this.tid=tid;
	}
	public Integer getTid(){
		return tid;
	}
	public void setReadPerm(Integer read_perm){
	this.read_perm=read_perm;
	}
	public Integer getReadPerm(){
		return read_perm;
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
	public void setLastPost(Long last_post){
	this.last_post=last_post;
	}
	public Long getLastPost(){
		return last_post;
	}
	public void setLastPoster(String last_poster){
	this.last_poster=last_poster;
	}
	public String getLastPoster(){
		return last_poster;
	}
	public void setViews(Integer views){
	this.views=views;
	}
	public Integer getViews(){
		return views;
	}
	public void setReplies(Integer replies){
	this.replies=replies;
	}
	public Integer getReplies(){
		return replies;
	}
	public void setDisplayOrder(Integer display_order){
	this.display_order=display_order;
	}
	public Integer getDisplayOrder(){
		return display_order;
	}
	public void setHighLight(Boolean high_light){
	this.high_light=high_light;
	}
	public Boolean getHighLight(){
		return high_light;
	}
	public void setDigest(Boolean digest){
	this.digest=digest;
	}
	public Boolean getDigest(){
		return digest;
	}
	public void setClosed(Integer closed){
	this.closed=closed;
	}
	public Integer getClosed(){
		return closed;
	}
	public void setStickReply(Boolean stick_reply){
	this.stick_reply=stick_reply;
	}
	public Boolean getStickReply(){
		return stick_reply;
	}
	public void setStatus(Integer status){
	this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setFavTimes(Integer fav_times){
	this.fav_times=fav_times;
	}
	public Integer getFavTimes(){
		return fav_times;
	}
	public void setShareTimes(Integer share_times){
	this.share_times=share_times;
	}
	public Integer getShareTimes(){
		return share_times;
	}
	public void setStamp(Integer stamp){
	this.stamp=stamp;
	}
	public Integer getStamp(){
		return stamp;
	}
	public void setIcon(Integer icon){
	this.icon=icon;
	}
	public Integer getIcon(){
		return icon;
	}
}

