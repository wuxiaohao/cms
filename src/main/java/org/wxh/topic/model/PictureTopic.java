package org.wxh.topic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.wxh.user.model.User;

/**
 * 图片新闻实体
 * @author wxh
 *
 */
@Entity
@Table(name="t_picture_topic")
public class PictureTopic {

	@Id
	@GeneratedValue
	@Column(name="pt_id")
	private int id;
	/**
	 * 图片新闻标题
	 */
	@Column(name="pt_title")
	private String title;
	/**
	 * 关键字:通过|来分割不同的关键字
	 */
	@Column(name="pt_keyword")
	private String keyword;
	/**
	 * 图片新闻说明
	 */
	@Lob     
	@Type(type="text")  
	@Column(name="pt_explain", nullable=true)
	private String explain;
	/**
	 * 图片新闻的发布时间，用来进行排序的
	 */
	@Column(name="pt_publish_date")
	private Date publishDate;
	/**
	 * 图片新闻的创建时间
	 */
	@Column(name="pt_create_date")
	private Date createDate;
	/**
	 * 图片新闻的作者名称，用来显示用户的昵称
	 */
	@Column(name="pt_author")
	private String author;
	/**
	 * 栏目名称的冗余字段
	 */
	@Column(name="pt_cname")
	private String cname;
	/**
	 * 图片新闻所在的频道，多对一
	 */
	@ManyToOne
	@JoinColumn(name="pt_channel")
	private Channel channel;
	/**
	 * 图片新闻的创建者
	 */
	@ManyToOne
	@JoinColumn(name="pt_user")
	private User user;
	/**
	 * 图片新闻审核人
	 */
	@Column(name="pt_auditor")
	private String auditor;
	/**
	 * 图片新闻的状态，默认为0表示未发表，1表示已发布
	 */
	@Column(name="pt_status")
	private int status;
	/**
	 * 浏览次数
	 */
	@Column(name="pt_view_count")
	private int viewCount;
	/**
	 * 是否是推荐文章,0表示不推荐，1表示推荐
	 */
	@Column(name="pt_recommend")
	private int recommend;
	
	public PictureTopic() {}
	
	public PictureTopic(int id, String title,String keyword,String explain,
			Date publishDate,Date createDate,String author, int status,int recommend,String cname,String auditor) {
		super();
		this.id = id;
		this.title = title;
		this.keyword = keyword;
		this.explain = explain;
		this.createDate = createDate;
		this.publishDate = publishDate;
		this.author = author;
		this.status = status;
		this.recommend = recommend;
		this.cname = cname;
		this.auditor = auditor;
	}
	public PictureTopic(int id, String title,String keyword,int status,int recommend,String author) {
		super();
		this.id = id;
		this.title = title;
		this.keyword = keyword;
		this.status = status;
		this.recommend = recommend;
		this.author = author;
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	
}
