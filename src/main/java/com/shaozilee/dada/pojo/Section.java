package com.shaozilee.dada.pojo;

/**
 *
 * SELECT sid,name,status,display_order,notice,title,parent_sid,img FROM section
 *
 **/


public class Section{
	public Integer sid;
	public String name;
	public Integer status;
	public Integer display_order;
	public String notice;
	public String title;
	public Integer parent_sid;
	public String img;
	public void setSid(Integer sid){
	this.sid=sid;
	}
	public Integer getSid(){
		return sid;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setStatus(Integer status){
	this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setDisplayOrder(Integer display_order){
	this.display_order=display_order;
	}
	public Integer getDisplayOrder(){
		return display_order;
	}
	public void setNotice(String notice){
	this.notice=notice;
	}
	public String getNotice(){
		return notice;
	}
	public void setTitle(String title){
	this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setParentSid(Integer parent_sid){
	this.parent_sid=parent_sid;
	}
	public Integer getParentSid(){
		return parent_sid;
	}
	public void setImg(String img){
	this.img=img;
	}
	public String getImg(){
		return img;
	}
}

