package com.shaozilee.dada.pojo;

/**
 *
 * SELECT rid,name,description FROM role
 *
 **/


public class Role{
	public Integer rid;
	public String name;
	public String description;
	public void setRid(Integer rid){
	this.rid=rid;
	}
	public Integer getRid(){
		return rid;
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

