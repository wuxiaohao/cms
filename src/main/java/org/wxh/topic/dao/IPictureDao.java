package org.wxh.topic.dao;

import java.util.List;

import org.wxh.basic.dao.IBaseDao;
import org.wxh.topic.model.Attachment;
import org.wxh.topic.model.Picture;
import org.wxh.topic.model.PictureTopic;

public interface IPictureDao extends IBaseDao<Picture>{
	/**
	 * 获取某个图片
	 * @param tid 新闻图片id
	 * @return
	 */
	public List<Picture> listByPicTopic(int tid);
	/**
	 * 删除某个新闻图片的所有图片
	 * @param tid 新闻图片id 
	 */
	public void deleteByPicTopic(int tid);
	
}
