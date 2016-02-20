package org.wxh.topic.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.wxh.basic.model.Pager;
import org.wxh.topic.model.Video;
import org.wxh.user.model.User;

/**
 * 视频新闻业务层
 * @author wxh
 *
 */
public interface IVideoService {

	/**
	 * 根据栏目和标题和状态进行视频新闻的检索
	 * @param cid 栏目id
	 * @param title 视频新闻标题
	 * @param status 视频新闻状态
	 * @return
	 */
	public Pager<Video> find(Integer cid, String title, Integer status);
	/**
	 * 根据用户，栏目和标题和状态进行视频新闻检索
	 * @param uid 用户id
	 * @param cid 栏目id
	 * @param title 视频新闻标题
	 * @param status 视频新闻状态
	 * @return
	 */
	public Pager<Video> find(Integer uid,Integer cid,String title,Integer status);
	/**
	 * 更新视频新闻的状态
	 * @param tid 视频新闻id
	 * @param u 用户
	 */
	public void updateStatus(int vid, User loginUser);
	/**
	 * 加载一个视频新闻
	 * @param id
	 * @return
	 */
	public Video load(int id);
	/**
	 * 添加视频新闻
	 * @param video 视频新闻实体
	 * @param cid 栏目id
	 * @param loginUser 用户
	 */
	public void add(Video video, int cid, User loginUser);
	/**
	 * 删除视频新闻，先删除视频新闻信息，再删除视频
	 * @param id 视频新闻id
	 */
	public void delete(int id);
	/**
	 * 更新视频新闻
	 * @param v
	 * @param cid
	 */
	public void update(Video v,Video vOld, int cid);
	/**
	 * 添加视频
	 * @param v
	 * @param inputStream 
	 * @param loginUser
	 */
	public void addVideo(Video v, InputStream inputStream)throws IOException;
	/**
	 * 添加缩略图
	 * @param picName
	 * @param inputStream
	 * @throws IOException 
	 */
	public void addPic(String picName, InputStream inputStream) throws IOException;
	/**
	 * 根据栏目id和数量，获取视频列表
	 * @param cid 栏目id
	 * @param num 数量
	 */
	public List<Video> listVideoByNum(int cid, int num);
	/**
	 * 根据栏目id获取视频列表
	 * @param cid
	 * @return
	 */
	public Pager<Video> findVideoByCid(int cid);
	
}
