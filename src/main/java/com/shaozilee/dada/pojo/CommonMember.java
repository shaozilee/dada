package com.shaozilee.dada.pojo;

/**
 *
 * SELECT uid,userName,password,email,status,emailStatus,regDate,credits,lastLoginIp,lastLoginTime FROM common_member
 *
 **/


public class CommonMember {
	public Integer uid;
	public String userName;
	public String password;
	public String email;
	public Boolean status;
	public Boolean emailStatus;
	public Long regDate;
	public Integer credits;
	public Integer lastLoginIp;
	public Long lastLoginTime;
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
	public void setRegDate(Long regDate){
	this.regDate=regDate;
	}
	public Long getRegDate(){
		return regDate;
	}
	public void setCredits(Integer credits){
	this.credits=credits;
	}
	public Integer getCredits(){
		return credits;
	}
	public void setLastLoginIp(Integer lastLoginIp){
	this.lastLoginIp=lastLoginIp;
	}
	public Integer getLastLoginIp(){
		return lastLoginIp;
	}
	public void setLastLoginTime(Long lastLoginTime){
	this.lastLoginTime=lastLoginTime;
	}
	public Long getLastLoginTime(){
		return lastLoginTime;
	}
}

