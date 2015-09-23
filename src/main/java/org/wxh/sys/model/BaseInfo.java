package org.wxh.sys.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 网站基本信息实体
 * @author wxh
 *
 */
public class BaseInfo {
	private String name; //网站名称
	private String address;//网站所在地址
	private String zipCode;//邮政编码
	private String recordCode;//网站备案号
	private String phone;//联系电话
	private String email;//网站联系邮箱
	private String domainName;//网站访问域名
	private int indexPicWidth;//首页图片宽度
	private int indexPicHeight;//首页图片高度
	private int indexPicNumber;
	
	
	
	public int getIndexPicNumber() {
		return indexPicNumber;
	}
	public void setIndexPicNumber(int indexPicNumber) {
		this.indexPicNumber = indexPicNumber;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	@NotEmpty(message="网站名称不能为空")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getRecordCode() {
		return recordCode;
	}
	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIndexPicWidth() {
		return indexPicWidth;
	}
	public void setIndexPicWidth(int indexPicWidth) {
		this.indexPicWidth = indexPicWidth;
	}
	public int getIndexPicHeight() {
		return indexPicHeight;
	}
	public void setIndexPicHeight(int indexPicHeight) {
		this.indexPicHeight = indexPicHeight;
	}
	@Override
	public String toString() {
		return "BaseInfo [name=" + name + ", address=" + address + ", zipCode="
				+ zipCode + ", recordCode=" + recordCode + ", phone=" + phone
				+ ", email=" + email + ", indexPicWidth=" + indexPicWidth
				+ ", indexPicHeight=" + indexPicHeight + "]";
	}
}
