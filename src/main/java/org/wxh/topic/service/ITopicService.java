package org.wxh.topic.service;


import java.util.List;

import org.wxh.basic.model.Pager;
import org.wxh.topic.model.Topic;
import org.wxh.user.model.User;

/**
 * 文章业务层
 * @author wxh
 *
 */
public interface ITopicService {
	/**
	 * 添加带有附件信息的文章
	 * @param topic 文章对象
	 * @param cid 文章的栏目
	 * @param uid 文章的用户
	 * @param aids 文章的附件id数组
	 */
	public void add(Topic topic,int cid,User u,Integer[] aids);
	/**
	 * 添加不带附件信息的文章对象
	 * @param topic
	 * @param cid
	 * @param uid
	 */
	public void add(Topic topic,int cid,User u);
	
	/**
	 * 删除文章，先删除文章的附件信息，再删除附件的文件对象
	 * @param id 文章id
	 */
	public void delete(int id);
	/**
	 * 更新文章，带附件信息更新
	 * @param topic
	 * @param cid 栏目id
	 * @param aids 附件id
	 */
	public void update(Topic topic,int cid,Integer[] aids);
	/**
	 * 没有带附件信息的文章更新
	 * @param topic 
	 * @param cid 栏目id
	 */
	public void update(Topic topic,int cid);
	/**
	 * 文章更新
	 */
	public void update(Topic topic);
	/**
	 * 更新文章的状态
	 * @param tid 文章id
	 * @param u 用户
	 */
	public void updateStatus(int tid,User u);
	
	/**
	 * 加载一个文章
	 * @param id
	 * @return
	 */
	public Topic load(int id);
	
	/**
	 * 根据栏目和标题和状态进行文章的检索
	 * @param cid 栏目id
	 * @param title 文章标题
	 * @param status 文章状态
	 * @return
	 */
	public Pager<Topic> find(Integer cid,String title,Integer status);
	/**
	 * 根据用户，栏目和标题和状态进行检索
	 * @param uid 用户id
	 * @param cid 栏目id
	 * @param title 文章标题
	 * @param status 文章状态
	 * @return
	 */
	public Pager<Topic> find(Integer uid,Integer cid,String title,Integer status);
	/**
	 * 根据关键字进行文章的检索，仅仅只是检索关键字类似的
	 * @param keyword
	 * @return
	 */
	public Pager<Topic> searchTopicByKeyword(String keyword);
	/**
	 * 通过某个条件来检索，该条件会在标题，内容和摘要中检索
	 * @param con
	 * @return
	 */
	public Pager<Topic> searchTopic(String con);
	/**
	 * 检索某个栏目的推荐文章，如果cid为空，表示检索全部的文章
	 * @param ci
	 * @return
	 */
	public Pager<Topic> findRecommendTopic(Integer ci);
	/**
	 * 根据栏目和数量来获取文章 
	 * @param cid 栏目id
	 * @param num 文章数量
	 * @return
	 */
	public List<Topic> listTopicByChannelAndNumber(int cid,int num);
	/**
	 * 根据栏目获取文章
	 * @param cid 栏目id
	 * @return
	 */
	public List<Topic> listTopicByChannel(int cid);
	/**
	 * 判断当前的栏目是否是主页栏目
	 * @param cid 栏目id
	 * @return
	 */
	public boolean isUpdateIndex(int cid);
	/**
	 * 获取某个栏目中的最新的可用文章
	 * @param cid 栏目id
	 * @return
	 */
	public Topic loadLastedTopicByColumn(int cid);
	/**
	 * 获取点击量最多的前10条文章
	 * @return
	 */
	public List<Topic> listTopic();
}
