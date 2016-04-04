package org.wxh.user.model.dto;

import org.wxh.basic.model.Dto;

public class LoginDto extends Dto{
	
	/**用户名*/
	public String username;
	public String password;
	public String checkcode;
	public String remember;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public String getRemember() {
		return remember;
	}
	public void setRemember(String remember) {
		this.remember = remember;
	}

}
