package org.wxh.topic.dao;

import org.wxh.basic.dao.IBaseDao;
import org.wxh.basic.model.Pager;
import org.wxh.topic.model.Video;

public interface IVideoDao extends IBaseDao<Video>{

	/**
	 * 根据栏目和标题和状态进行视频新闻的检索
	 * @param cid 栏目id
	 * @param title 视频新闻标题
	 * @param status 视频新闻状态
	 * @return
	 */
	Pager<Video> find(Integer cid, String title, Integer status);
	/**
	 * 根据用户，栏目和标题和状态进行视频新闻检索
	 * @param uid 用户id
	 * @param cid 栏目id
	 * @param title 视频新闻标题
	 * @param status 视频新闻状态
	 * @return
	 */
	Pager<Video> find(Integer uid, Integer cid, String title,Integer status);
	/**
	 * 加载一个视频新闻，不缓存！
	 * @param id
	 * @return
	 */
	Video loadVideo(int id);

}
