package org.wxh.topic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 图片实体
 * @author wxh
 *
 */
@Entity
@Table(name="t_picture")
public class Picture {
	
	@Id
	@GeneratedValue
	@Column(name="p_id")
	private int id;
	/**
	 * 图片上传之后的名称
	 */
	@Column(name="p_pic_name")
	private String picName;
	/**
	 * 图片原始名称
	 */
	@Column(name="p_pic_name_old")
	private String picNameOld;
	/**
	 * 图片的大小
	 */
	@Column(name="p_size")
	private long size;
	/**
	 * 图片的后缀名
	 */
	@Column(name="p_suffix")
	private String suffix;
	/**
	 * 图片所属新闻
	 */
	@ManyToOne
	@JoinColumn(name="p_picture_topic")
	private PictureTopic pictureTopic;
	/**
	 * 图片的序号
	 */
	@Column(name="p_orders")
	private int orders;
	
	public Picture() {
		super();
	}
	public Picture(int id, String picName, String picNameOld,
			long size, String suffix, int orders,int pid,String picTopicTitle,Date publishDate,String author) {
		super();
		this.id = id;
		this.picName = picName;
		this.picNameOld = picNameOld;
		this.size = size;
		this.suffix = suffix;
		this.orders = orders;
		this.pictureTopic = new PictureTopic();
		this.pictureTopic.setId(pid);
		this.pictureTopic.setTitle(picTopicTitle);
		this.pictureTopic.setPublishDate(publishDate);
		this.pictureTopic.setAuthor(author);
	}
	public Picture(int id, String picName, String picNameOld,
			long size, String suffix, int orders) {
		super();
		this.id = id;
		this.picName = picName;
		this.picNameOld = picNameOld;
		this.size = size;
		this.suffix = suffix;
		this.orders = orders;
		this.pictureTopic = new PictureTopic();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getPicNameOld() {
		return picNameOld;
	}
	public void setPicNameOld(String picNameOld) {
		this.picNameOld = picNameOld;
	}

	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public PictureTopic getPictureTopic() {
		return pictureTopic;
	}
	public void setPictureTopic(PictureTopic pictureTopic) {
		this.pictureTopic = pictureTopic;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	
}
