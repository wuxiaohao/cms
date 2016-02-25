package org.wxh.topic.model.dto;

import java.util.Date;

public class PictureDto {
	/**
	 * 组图新闻id
	 */
	private int id;
	/**
	 * 组图新闻标题
	 */
	private String title;
	/**
	 * 浏览量
	 */
	private int viewCount;
	/**
	 * 发布日期
	 */
	private String publishDate;
	/**
	 * 封面图片的名称
	 */
	private String picName;
	
	public PictureDto() {
		super();
	}
	public PictureDto(int id, String title, int viewCount, String publishDate,
			String picName) {
		super();
		this.id = id;
		this.title = title;
		this.viewCount = viewCount;
		this.publishDate = publishDate;
		this.picName = picName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	
	
	
}
