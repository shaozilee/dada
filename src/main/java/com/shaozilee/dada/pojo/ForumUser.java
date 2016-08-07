package com.shaozilee.dada.pojo;

/**
 *
 * SELECT uid,userName,password,email,status,emailStatus,regDate,credits,lastLoginTime,lastLoginIp,photo FROM forum_user
 *
 **/


public class ForumUser {
	public Integer uid;
	public String userName;
	public String password;
	public String email;
	public Boolean status;
	public Boolean emailStatus;
	public String regDate;
	public Integer credits;
	public String lastLoginTime;
	public String lastLoginIp;
	public String photo;
	public void setUid(Integer uid){
	this.uid=uid;
	}
	public Integer getUid(){
		return uid;
	}
	public void setUserName(String userName){
	this.userName=userName;
	}
	public String getUserName(){
		return userName;
	}
	public void setPassword(String password){
	this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public void setEmail(String email){
	this.email=email;
	}
	public String getEmail(){
		return email;
	}
	public void setStatus(Boolean status){
	this.status=status;
	}
	public Boolean getStatus(){
		return status;
	}
	public void setEmailStatus(Boolean emailStatus){
	this.emailStatus=emailStatus;
	}
	public Boolean getEmailStatus(){
		return emailStatus;
	}
	public void setRegDate(String regDate){
	this.regDate=regDate;
	}
	public String getRegDate(){
		return regDate;
	}
	public void setCredits(Integer credits){
	this.credits=credits;
	}
	public Integer getCredits(){
		return credits;
	}
	public void setLastLoginTime(String lastLoginTime){
	this.lastLoginTime=lastLoginTime;
	}
	public String getLastLoginTime(){
		return lastLoginTime;
	}
	public void setLastLoginIp(String lastLoginIp){
	this.lastLoginIp=lastLoginIp;
	}
	public String getLastLoginIp(){
		return lastLoginIp;
	}
	public void setPhoto(String photo){
	this.photo=photo;
	}
	public String getPhoto(){
		return photo;
	}
}

