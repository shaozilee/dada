package com.shaozilee.dada.pojo;

/**
 *
 * SELECT uid,user_name,password,email,status,email_status,reg_date,credits,last_login_ip,last_login_time FROM common_member
 *
 **/


public class CommonMember {
	public Integer uid;
	public String user_name;
	public String password;
	public String email;
	public Boolean status;
	public Boolean email_status;
	public Long reg_date;
	public Integer credits;
	public Integer last_login_ip;
	public Long last_login_time;
	public void setUid(Integer uid){
	this.uid=uid;
	}
	public Integer getUid(){
		return uid;
	}
	public void setUserName(String user_name){
	this.user_name=user_name;
	}
	public String getUserName(){
		return user_name;
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
	public void setEmailStatus(Boolean email_status){
	this.email_status=email_status;
	}
	public Boolean getEmailStatus(){
		return email_status;
	}
	public void setRegDate(Long reg_date){
	this.reg_date=reg_date;
	}
	public Long getRegDate(){
		return reg_date;
	}
	public void setCredits(Integer credits){
	this.credits=credits;
	}
	public Integer getCredits(){
		return credits;
	}
	public void setLastLoginIp(Integer last_login_ip){
	this.last_login_ip=last_login_ip;
	}
	public Integer getLastLoginIp(){
		return last_login_ip;
	}
	public void setLastLoginTime(Long last_login_time){
	this.last_login_time=last_login_time;
	}
	public Long getLastLoginTime(){
		return last_login_time;
	}
}

