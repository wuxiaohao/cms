package org.wxh.topic.dao;


import java.util.List;

import org.wxh.basic.dao.IBaseDao;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.ChannelTree;
import org.wxh.topic.model.ChannelType;

public interface IChannelDao extends IBaseDao<Channel> {
	/**
	 * 根据父id获取所有的子栏目
	 * @param pid
	 * @return
	 */
	public List<Channel> listByParent(Integer pid);
	/**
	 * 获取子栏目的最大的排序号
	 * @param pid 父节点id
	 * @return
	 */
	public int getMaxOrderByParent(Integer pid);
	/**
	 * 把所有的文章和导航栏目获取并生成一颗完整的树
	 * @param type 
	 * @return
	 */
	public List<ChannelTree> generateTree(int type);
	/**
	 * 把所有的文章和导航栏目获取并生成一颗完整的树
	 * @param type 
	 * @return
	 */
	public List<ChannelTree> generateTree();
	/**
	 * 把所有栏目获取并生成一颗完整的树
	 * @return
	 */
	public List<ChannelTree> generateTreeAll();
	/**
	 * 根据父类对象获取子类栏目，并且生成树列表（用户异步加载）
	 * @param pid
	 * @return
	 */
	public List<ChannelTree> generateTreeByParent(Integer pid);
	/**
	 * 通过一个数组来完成栏目的排序
	 * @param ids
	 */
	public void updateSort(Integer[] ids);
	/**
	 * 通过一个数组来完成顶部导航栏目的排序
	 * @param ids
	 */
	public void updateTopNavSort(Integer[] ids);
	/**
	 * 所有的可以发布文章的栏目，栏目的状态必须为启用状态
	 * @return
	 */
	public List<Channel> listPublishChannel(int type);
	/**
	 * 根据用户id获取所有的可以发布文章的栏目，栏目的状态必须为启用状态
	 * @param uid
	 * @return
	 */
	public List<Channel> listPublishChannel(int uid,int type);
	/**
	 * 根据栏目类型获取所有的首页栏目
	 * @return
	 */
	public List<Channel> listAllIndexChannel(ChannelType ct);
	/**
	 * 获取指定数量顶部导航栏目
	 * @param num 
	 * @return
	 */
	public List<Channel> listTopNavChannel(int num);
	/**
	 * 获取所有顶部导航栏目的所有数据
	 * @return
	 */
	public List<Channel> listTopNavChannelAll();
	/**
	 * 删除频道和组的对应关系
	 * @param cid
	 * @return
	 */
	public void deleteChannelGroups(int cid);
	/**
	 * 获取导航栏目中的第一个子栏目
	 * @param cid
	 * @return
	 */
	public Channel loadFirstChannelByNav(int cid);
	
	public List<Channel> listUseChannelByParent(Integer cid);
	/**
	 * 通过类型来获取所有未停用的栏目
	 * @param ct
	 * @return
	 */
	public List<Channel> listChannelByType(ChannelType ct);
	/**
	 * 获取导航栏目最大排序号
	 * @return
	 */
	public Integer getMaxIsTopNav();
}
