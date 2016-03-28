package org.wxh.topic.service;

import org.wxh.basic.model.Pager;
import org.wxh.topic.model.PictureTopic;
import org.wxh.topic.model.dto.PictureDto;
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
	 * 加载一个组图新闻
	 * @param id
	 * @return
	 */
	public PictureTopic load(int id);
	/**
	 * 删除新闻图片，先删除图片信息，再删除图片
	 * @param id 新闻图片id
	 */
	public void delete(int id);
	/**
	 * 添加组图新闻
	 * @param pt 组图新闻对象
	 * @param cid 栏目id
	 * @param uid 用户
	 * @param pics 图片组的id
	 */
	public void add(PictureTopic pt, int cid, User uid, Integer[] pics);
	/**
	 * 更新组图新闻，带图片信息更新
	 * @param pt 组图新闻实体对象
	 * @param cid 栏目id
	 * @param pics 图片的id数组
	 */
	public void update(PictureTopic pt, int cid, Integer[] pics);
	/**
	 * 获取组图新闻的封面列表
	 * @param cid
	 * @return
	 */
	public Pager<PictureDto> findPicTopByCid(int cid);
	public void update(PictureTopic top);
}
