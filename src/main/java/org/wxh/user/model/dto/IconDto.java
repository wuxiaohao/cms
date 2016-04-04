package org.wxh.user.model.dto;

import org.wxh.basic.model.Dto;

public class IconDto extends Dto{
	/**
	 * 新文件名
	 */
	private String newName;
	/**
	 * 源文件名
	 */
	private String oldName;
	/**
	 * 上传之后的临时图片宽度
	 */
	private int imgWidth;
	/**
	 * 上传之后的临时图片高度
	 */
	private int imgHeight;
	/**
	 * 头像宽度
	 */
	private int iconWidth;
	/**
	 * 头像高度
	 */
	private int iconHeight;
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public int getImgWidth() {
		return imgWidth;
	}
	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}
	public int getImgHeight() {
		return imgHeight;
	}
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}
	public int getIconWidth() {
		return iconWidth;
	}
	public void setIconWidth(int iconWidth) {
		this.iconWidth = iconWidth;
	}
	public int getIconHeight() {
		return iconHeight;
	}
	public void setIconHeight(int iconHeight) {
		this.iconHeight = iconHeight;
	}
	
	
	
}
