package com.shaozilee.dada.pojo;

/**
 *
 * SELECT tid,readPerm,authorName,authorId,subject,dateLine,lastPost,lastPoster,views,replies,displayOrder,highLight,digest,closed,stickReply,status,favTimes,shareTimes,stamp,icon FROM forum_topic
 *
 **/


public class ForumTopic {
	public Integer tid;
	public Integer readPerm;
	public String authorName;
	public Integer authorId;
	public String subject;
	public Long dateLine;
	public Long lastPost;
	public String lastPoster;
	public Integer views;
	public Integer replies;
	public Integer displayOrder;
	public Boolean highLight;
	public Boolean digest;
	public Integer closed;
	public Boolean stickReply;
	public Integer status;
	public Integer favTimes;
	public Integer shareTimes;
	public Integer stamp;
	public Integer icon;
	public void setTid(Integer tid){
	this.tid=tid;
	}
	public Integer getTid(){
		return tid;
	}
	public void setReadPerm(Integer readPerm){
	this.readPerm=readPerm;
	}
	public Integer getReadPerm(){
		return readPerm;
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
	public void setLastPost(Long lastPost){
	this.lastPost=lastPost;
	}
	public Long getLastPost(){
		return lastPost;
	}
	public void setLastPoster(String lastPoster){
	this.lastPoster=lastPoster;
	}
	public String getLastPoster(){
		return lastPoster;
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
	public void setDisplayOrder(Integer displayOrder){
	this.displayOrder=displayOrder;
	}
	public Integer getDisplayOrder(){
		return displayOrder;
	}
	public void setHighLight(Boolean highLight){
	this.highLight=highLight;
	}
	public Boolean getHighLight(){
		return highLight;
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
	public void setStickReply(Boolean stickReply){
	this.stickReply=stickReply;
	}
	public Boolean getStickReply(){
		return stickReply;
	}
	public void setStatus(Integer status){
	this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setFavTimes(Integer favTimes){
	this.favTimes=favTimes;
	}
	public Integer getFavTimes(){
		return favTimes;
	}
	public void setShareTimes(Integer shareTimes){
	this.shareTimes=shareTimes;
	}
	public Integer getShareTimes(){
		return shareTimes;
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

