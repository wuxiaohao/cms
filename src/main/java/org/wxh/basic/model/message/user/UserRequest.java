package org.wxh.basic.model.message.user;

import org.wxh.basic.model.message.Request;

public class UserRequest extends Request{
	
	/**用户id*/
	private int id;
	/**密码*/
	private String password;
	/**确认密码*/
	private String confirmPwd;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
}
