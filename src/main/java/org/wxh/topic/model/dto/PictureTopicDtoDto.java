package org.wxh.topic.model.dto;

import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.PictureTopic;
import org.wxh.user.model.User;

public class PictureTopicDtoDto {
	
	private int id;
	/**
	 * 图片新闻标题
	 */
	private String title;
	/**
	 * 关键字:通过|来分割不同的关键字
	 */
	private String keyword;
	/**
	 * 图片新闻说明
	 */
	private String explain;
	/**
	 * 图片新闻的发布时间，用来进行排序的
	 */
	private String publishDate;
	/**
	 * 栏目id
	 */
	private int cid;
	/**
	 * 图片新闻的状态，默认为0表示未发表，1表示已发布
	 */
	private int status;
	/**
	 * 是否是推荐文章,0表示不推荐，1表示推荐
	 */
	private int recommend;
	/**
	 * 封面图片的id
	 */
	private int pictureId;
	/**
	 * 组图信息的所有图片id
	 */
	private Integer[] pics;
	
	public PictureTopicDtoDto() {
		super();
	}
	public PictureTopicDtoDto(PictureTopic t) {
		this.setExplain(t.getExplain());
		this.setId(t.getId());
		this.setKeyword(t.getKeyword());
		this.setRecommend(t.getRecommend());
		this.setStatus(t.getStatus());
		this.setTitle(t.getTitle());
	}
	
	public PictureTopicDtoDto(PictureTopic t, Integer cid) {
		this.setExplain(t.getExplain());
		this.setId(t.getId());
		this.setKeyword(t.getKeyword());
		this.setRecommend(t.getRecommend());
		this.setStatus(t.getStatus());
		this.setTitle(t.getTitle());
		this.setPictureId(t.getPictureId());
		this.setCid(cid);
	}
	public int getPictureId() {
		return pictureId;
	}
	public void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Integer[] getPics() {
		return pics;
	}
	public void setPics(Integer[] pics) {
		this.pics = pics;
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
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
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
	/**
	 * 封装添加组图的dto数据
	 * @param loginUser
	 * @return
	 */
	public PictureTopic getPictureTopic(User u) {
		PictureTopic pt = new PictureTopic();
		pt.setExplain(this.getExplain());
		pt.setTitle(this.getTitle());
		if(this.getStatus() == 1) {
			pt.setPublishDate(new Date());
			pt.setAuditor(u.getNickname());
		}
		pt.setStatus(this.getStatus());
		pt.setRecommend(this.getRecommend());
		pt.setPictureId(this.getPictureId());
		return pt;
	}
	/**
	 * 封装更新后的数据
	 * @param pt 加载一个组图新闻实体进来
	 * @param u 当前执行操作的用户
	 * @return
	 */
	public PictureTopic getPicTopicByUpdate(PictureTopic pt, User u) {
		if(pt.getStatus() != this.getStatus()) { //如果组图新闻状态有变动
			if(this.getStatus() == 1) {//发布
				pt.setPublishDate(new Date());
				pt.setAuditor(u.getNickname());
			}
			if(this.getStatus() == 0){ //取消发布
				pt.setPublishDate(null);
				pt.setAuditor(null);
			}
			pt.setStatus(this.getStatus());
		}
		pt.setExplain(this.getExplain());
		pt.setTitle(this.getTitle());
		pt.setRecommend(this.getRecommend());
		pt.setPictureId(this.getPictureId());
		return pt;
	}
}
