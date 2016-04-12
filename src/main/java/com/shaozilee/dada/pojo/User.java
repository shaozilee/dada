package com.shaozilee.dada.pojo;

/**
 *
 * SELECT uid,rid,name,email,tel,status,password FROM user
 *
 **/


public class User{
	public Integer uid;
	public Integer rid;
	public String name;
	public String email;
	public String tel;
	public Integer status;
	public String password;
	public void setUid(Integer uid){
	this.uid=uid;
	}
	public Integer getUid(){
		return uid;
	}
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
	public void setEmail(String email){
	this.email=email;
	}
	public String getEmail(){
		return email;
	}
	public void setTel(String tel){
	this.tel=tel;
	}
	public String getTel(){
		return tel;
	}
	public void setStatus(Integer status){
	this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setPassword(String password){
	this.password=password;
	}
	public String getPassword(){
		return password;
	}
}

