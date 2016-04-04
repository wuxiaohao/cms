package org.wxh.index.model.dto;

import org.wxh.basic.model.Dto;

public class CmsLinkDto extends Dto{
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
	 * 超链接图片设定的宽度
	 */
	private int linkPicWidth;
	/**
	 * 首页图片设定的高度
	 */
	private int linkPicHeight;
	
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
	public int getLinkPicWidth() {
		return linkPicWidth;
	}
	public void setLinkPicWidth(int linkPicWidth) {
		this.linkPicWidth = linkPicWidth;
	}
	public int getLinkPicHeight() {
		return linkPicHeight;
	}
	public void setLinkPicHeight(int linkPicHeight) {
		this.linkPicHeight = linkPicHeight;
	}
	
}
