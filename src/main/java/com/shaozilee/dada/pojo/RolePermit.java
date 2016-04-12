package com.shaozilee.dada.pojo;

/**
 *
 * SELECT rid,pid,rpid FROM role_permit
 *
 **/


public class RolePermit{
	public Integer rid;
	public Integer pid;
	public Integer rpid;
	public void setRid(Integer rid){
	this.rid=rid;
	}
	public Integer getRid(){
		return rid;
	}
	public void setPid(Integer pid){
	this.pid=pid;
	}
	public Integer getPid(){
		return pid;
	}
	public void setRpid(Integer rpid){
	this.rpid=rpid;
	}
	public Integer getRpid(){
		return rpid;
	}
}

