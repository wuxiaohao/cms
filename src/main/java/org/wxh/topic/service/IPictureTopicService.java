package org.wxh.topic.service;

import java.util.List;

import org.wxh.basic.model.Pager;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.PictureTopic;
import org.wxh.topic.model.Topic;
import org.wxh.user.model.User;

/**
 * 图片新闻业务层
 * @author wxh
 *
 */
public interface IPictureTopicService {
	
	/**
	 * 根据栏目和标题和状态进行新闻图片的检索
	 * @param cid 栏目id
	 * @param title 文章标题
	 * @param status 文章状态
	 * @return
	 */
	public Pager<PictureTopic> find(Integer cid, String title, Integer status);
	/**
	 * 根据用户，栏目和标题和状态进行新闻图片检索
	 * @param uid 用户id
	 * @param cid 栏目id
	 * @param title 文章标题
	 * @param status 文章状态
	 * @return
	 */
	public Pager<PictureTopic> find(Integer uid,Integer cid,String title,Integer status);
	/**
	 * 更新新闻图片的状态
	 * @param tid 新闻图片id
	 * @param u 用户
	 */
	public void updateStatus(int tid,User u);
	/**
	 * 加载一个新闻图片
	 * @param id
	 * @return
	 */
	public PictureTopic load(int id);
	/**
	 * 删除新闻图片，先删除图片信息，再删除图片
	 * @param id 新闻图片id
	 */
	public void delete(int id);
}
