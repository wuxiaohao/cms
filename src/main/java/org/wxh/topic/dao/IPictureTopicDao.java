package org.wxh.topic.dao;

import java.util.List;

import org.wxh.basic.dao.IBaseDao;
import org.wxh.basic.model.Pager;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.PictureTopic;
import org.wxh.topic.model.Topic;

public interface IPictureTopicDao extends IBaseDao<PictureTopic>{

	/**
	 * 根据栏目和标题和状态进行新闻图片的检索
	 * @param cid 栏目id
	 * @param title 文章标题
	 * @param status 文章状态
	 * @return
	 */
	Pager<PictureTopic> find(Integer cid, String title, Integer status);
	/**
	 * 根据用户，栏目和标题和状态进行新闻图片检索
	 * @param uid 用户id
	 * @param cid 栏目id
	 * @param title 文章标题
	 * @param status 文章状态
	 * @return
	 */
	Pager<PictureTopic> find(Integer uid, Integer cid, String title, Integer status);
	

}
