package org.wxh.topic.model.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.wxh.topic.model.Topic;
import org.wxh.user.model.User;


public class TopicDto {
	/*private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");*/ 
	private int id;
	private String title;
	/**
	 * 关键字:通过|来分割不同的关键字
	 */
	private String keyword;
	/**
	 * 文章的状态，默认为0表示未发表，1表示已发布
	 */
	private int status;
	/**
	 * 是否是推荐文章,0表示不推荐，1表示推荐
	 */
	private int recommend;
	/**
	 * 文章的内容
	 */
	private String content;
	/**
	 * 文章的摘要
	 */
	private String summary;
	/**
	 * 栏目图片id，如果该栏目是图片类型的栏目，就会显示这个id的图片
	 */
	private int channelPicId;
	/**
	 * 文章的发布时间，用来进行排序的
	 */
	private Date publishDate;
	/**
	 * 文章的栏目id
	 */
	private int cid;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 浏览量
	 */
	private int viewCount;
	/**
	 * 上一篇文章的id
	 */
	private Integer preId; 
	/**
	 * 下一篇文章的id
	 */
	private Integer nextId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@NotEmpty(message="文章标题不能为空")
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRecommend() {
		return recommend;
	}
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getChannelPicId() {
		return channelPicId;
	}
	public void setChannelPicId(int channelPicId) {
		this.channelPicId = channelPicId;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	@Min(value=1,message="必须选择一个栏目id")
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public Integer getPreId() {
		return preId;
	}
	public void setPreId(Integer preId) {
		this.preId = preId;
	}
	public Integer getNextId() {
		return nextId;
	}
	public void setNextId(Integer nextId) {
		this.nextId = nextId;
	}
	/**
	 * 封装添加文章的dto数据
	 * @param u 当前执行操作的用户
	 * @return
	 */
	public Topic getTopic(User u) {
		Topic t = new Topic();
		t.setChannelPicId(this.getChannelPicId());
		t.setContent(this.getContent());
		t.setId(this.getId());
		if(this.getStatus() == 1) {
			t.setPublishDate(new Date());
			t.setAuditor(u.getNickname());
		}
		/*try {
			Date d = sdf.parse(this.getPublishDate());
			Calendar cd = Calendar.getInstance();
			cd.setTime(d);
			Calendar ca = Calendar.getInstance();
			ca.setTime(new Date());
			ca.set(cd.get(Calendar.YEAR), cd.get(Calendar.MONTH), cd.get(Calendar.DATE));
			t.setPublishDate(ca.getTime());
		} catch (ParseException e) {
			t.setPublishDate(new Date());
		}*/
		t.setRecommend(this.getRecommend());
		t.setStatus(this.getStatus());
		t.setSummary(this.getSummary());
		t.setTitle(this.getTitle());
		return t;
	}
	/**
	 * 封装更新后的数据
	 * @param t 加载一个文章实体进来
	 * @param u 当前执行操作的用户
	 * @return
	 */
	public Topic getTopicByUpdate(Topic t,User u) {
		if(t.getStatus() != this.getStatus()) { //如果文章状态有变动
			if(this.getStatus() == 1){ //发布
				t.setPublishDate(new Date());
				t.setAuditor(u.getNickname());
			}
			if(this.getStatus() == 0){ //取消发布
				t.setPublishDate(null);
				t.setAuditor(null);
			}
			t.setStatus(this.getStatus());
		}
		t.setChannelPicId(this.getChannelPicId());
		t.setContent(this.getContent());
		t.setId(this.getId());
		t.setKeyword(this.getKeyword());
		t.setRecommend(this.getRecommend());
		t.setSummary(this.getSummary());
		t.setTitle(this.getTitle());
		return t;
	}
	
	public TopicDto(Topic topic) {
		this.setChannelPicId(topic.getChannelPicId());
		this.setContent(topic.getContent());
		this.setId(topic.getId());
		this.setKeyword(topic.getKeyword());
		this.setRecommend(topic.getRecommend());
		this.setStatus(topic.getStatus());
		this.setSummary(topic.getSummary());
		this.setTitle(topic.getTitle());
	}
	
	public TopicDto(Topic topic,Integer cid) {
		this.setChannelPicId(topic.getChannelPicId());
		this.setContent(topic.getContent());
		this.setId(topic.getId());
		this.setCid(cid);
		this.setKeyword(topic.getKeyword());
		this.setRecommend(topic.getRecommend());
		this.setStatus(topic.getStatus());
		this.setSummary(topic.getSummary());
		this.setTitle(topic.getTitle());
		this.setAuthor(topic.getAuthor());
		this.setViewCount(topic.getViewCount());
		this.setPublishDate(topic.getPublishDate());
	}
	
	
}
