package org.wxh.topic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.wxh.basic.common.Constant;
import org.wxh.user.model.User;

@Entity
@Table(name="t_video")
public class Video {
	
	@Id
	@GeneratedValue
	@Column(name="pv_id")
	private int id;
	/**
	 * 视频标题
	 */
	@Column(name="pv_title")
	private String title;
	/**
	 * 视频的大小
	 */
	@Column(name="pv_size")
	private String size;
	/**
	 * 视频上传之后的名称
	 */
	@Column(name="pv_name")
	private String videoName;
	/**
	 * 视频缩略图名称
	 */
	@Column(name="pv_picName")
	private String picName;
	/**
	 * 发布时间，用来进行排序的
	 */
	@Column(name="pv_publish_date")
	private Date publishDate;
	/**
	 * 创建时间
	 */
	@Column(name="pv_create_date")
	private Date createDate;
	/**
	 * 作者名称，用来显示用户的昵称
	 */
	@Column(name="pv_author")
	private String author;
	/**
	 * 栏目名称的冗余字段
	 */
	@Column(name="pv_cname")
	private String cname;
	/**
	 * 视频所在的频道，多对一
	 */
	@ManyToOne
	@JoinColumn(name="pv_channel")
	private Channel channel;
	/**
	 * 视频的创建者
	 */
	@ManyToOne
	@JoinColumn(name="pv_user")
	private User user;
	/**
	 * 视频审核人
	 */
	@Column(name="pv_auditor")
	private String auditor;
	/**
	 * 视频的状态，默认为0表示未发表，1表示已发布
	 */
	@Column(name="pv_status")
	private int status;
	/**
	 * 浏览次数
	 */
	@Column(name="pv_view_count")
	private int viewCount;
	
	public Video() {
		super();
	}
	public Video(int id, String title, String size,Date publishDate, Date createDate, String author, String cname,String auditor, int status,int viewCount) {
		super();
		this.id = id;
		this.title = title;
		this.size = size;
		this.publishDate = publishDate;
		this.createDate = createDate;
		this.author = author;
		this.cname = cname;
		this.auditor = auditor;
		this.status = status;
		this.viewCount = viewCount;
	}
	
	//对新数据进行封装
	public void getVideo(Video vOld,User u) {
		this.setPublishDate(vOld.getPublishDate());
		this.setCreateDate(vOld.getCreateDate());
		this.setAuditor(vOld.getAuditor());
		this.setViewCount(vOld.getViewCount());
		if(this.getStatus() != vOld.getStatus()) {//如果状态有变动
			if(this.getStatus() == Constant.YES) {//发布
				this.setPublishDate(new Date());
				this.setAuditor(u.getNickname());
			}
			if(this.getStatus() == 0){ //取消发布
				this.setPublishDate(null);
				this.setAuditor(null);
			}
		}
		if(this.getAuthor() == null || this.getAuthor() == ""){ //如果没有指定作者名称，则默认为创建者的名称
			this.setAuthor(u.getNickname());
		}
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
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
}
