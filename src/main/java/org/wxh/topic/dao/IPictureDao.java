package org.wxh.topic.dao;

import java.util.List;

import org.wxh.basic.dao.IBaseDao;
import org.wxh.topic.model.Picture;

public interface IPictureDao extends IBaseDao<Picture>{
	/**
	 * 获取某个图片
	 * @param tid 组图新闻idR
	 * @return
	 */
	public List<Picture> listByPicTopic(int tid);
	/**
	 * 删除某个组图新闻的所有图片
	 * @param tid 新闻图片id 
	 */
	public void deleteByPicTopic(int tid);
	/**
	 * 更新图片名称和图片序号
	 * @param pics 所有图片id
	 * @param picNameOlds 所有图片名称
	 * @param ids
	 */
	public void updateNameAndSort(String[] picNameOlds, Integer[] pics);
	/**
	 * 更新图片序号
	 * @param id 组图新闻的id
	 * @param orders 
	 */
	public void updateOrder(int id, int orders);
	
}
