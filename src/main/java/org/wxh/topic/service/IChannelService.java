package org.wxh.topic.service;

import java.util.List;

import org.wxh.topic.model.Channel;
import org.wxh.topic.model.ChannelTree;
import org.wxh.topic.model.ChannelType;

/**
 * 栏目业务层
 * @author wxh
 *
 */
public interface IChannelService {
	
	/**
	 * 添加栏目
	 * @param channel
	 * @param pid
	 */
	public void add(Channel channel,Integer pid);
	/**
	 * 更新栏目
	 * @param channel
	 * @param oldIsTopNav 原来是否为导航栏目
	 */
	public void update(Channel channel,int oldIsTopNav);
	/**
	 * 删除栏目
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 清空栏目中的文章
	 * @param id
	 */
	public void clearTopic(int id);
	
	public Channel load(int id);
	/**
	 * 根据父亲id加载栏目，该方面首先检查SystemContext中是否存在排序如果没有存在把orders加进去
	 * @param pid
	 * @return
	 */
	public List<Channel> listByParent(Integer pid);
	
	/**
	 * 获取所有的指定类型文章和导航栏目获取并生成一颗完整的树
	 * @return
	 */
	public List<ChannelTree> generateTree(int type);
	/**
	 * 获取所有的指定类型文章和导航栏目获取并生成一颗完整的树
	 * @return
	 */
	public List<ChannelTree> generateTree();
	/**
	 * 获取所有的栏目获取并生成一颗完整的树
	 * @return
	 */
	public List<ChannelTree> generateTreeAll();
	/**
	 * 根据父类对象获取子类栏目，并且生成树列表（用于异步加载）
	 * @param pid
	 * @return
	 */
	public List<ChannelTree> generateTreeByParent(Integer pid);
	/**
	 * 获取所有的可以发布文章的栏目，栏目的状态必须为启用状态
	 * @return
	 */
	public List<Channel> listPublishChannel(int type);
	/**
	 * 根据用户id获取所有的可以发布文章的栏目，栏目的状态必须为启用状态
	 * @param uid 用户Id
	 * @return
	 */
	public List<Channel> listPublishChannel(int uid,int type);
	/**
	 * 获取指定数量的顶部导航栏目，栏目的状态必须为已经启用
	 * @param num 数量 
	 * @return
	 */
	public List<Channel> listTopNavChannel(int num);
	/**
	 * 获取所有的顶部导航栏目，栏目的状态必须为已经启用(所有数据)
	 * @return
	 */
	public List<Channel> listTopNavChannelAll();
	/**
	 * 更新栏目序号
	 * @param ids
	 */
	public void updateSort(Integer[] ids);
	/**
	 * 更新顶部导航栏目序号
	 * @param ids
	 */
	public void updateTopNavSort(Integer[] ids);
	/**
	 * 根据栏目类型获取所有的首页栏目
	 * @param ct
	 * @return
	 */
	public List<Channel> listAllIndexChannel(ChannelType ct);
	/**
	 * 获取导航栏目中的第一个子栏目
	 * @param cid
	 * @return
	 */
	public Channel loadFirstChannelByNav(int cid);
	/**
	 * 根据父节点获取已启用的子栏目
	 * @param cid 栏目id
	 * @return
	 */
	public List<Channel> listUseChannelByParent(Integer cid);
	
	/**
	 * 通过类型来获取所有未停用的栏目
	 * @param ct
	 * @return
	 */
	public List<Channel> listChannelByType(ChannelType ct);
}
