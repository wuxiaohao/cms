package org.wxh.basic.model.message;

import java.io.Serializable;

/**
 * 消息体
 * 
 * @author wuxiaohao
 *
 */
public class Message implements Serializable{

	private String serialNo;
	
	private static final long serialVersionUID = 2404418245845285054L;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	

}
