package com.shaozilee.dada.pojo;

/**
 *
 * SELECT pid,name,description FROM permit
 *
 **/


public class Permit{
	public Integer pid;
	public String name;
	public String description;
	public void setPid(Integer pid){
	this.pid=pid;
	}
	public Integer getPid(){
		return pid;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setDescription(String description){
	this.description=description;
	}
	public String getDescription(){
		return description;
	}
}

