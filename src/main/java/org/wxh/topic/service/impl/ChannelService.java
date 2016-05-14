package org.wxh.topic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.common.Constant;
import org.wxh.basic.exception.MyException;
import org.wxh.topic.dao.IChannelDao;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.ChannelTree;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.Topic;
import org.wxh.topic.service.IChannelService;
import org.wxh.topic.service.ITopicService;
/**
 * 栏目的业务层
 * @author wxh
 *
 */
@Service("channelService")
public class ChannelService implements IChannelService {
	
	@Autowired
	private IChannelDao channelDao;
	@Autowired
	private ITopicService topicService;
	
	public ITopicService getTopicService() {
		return topicService;
	}
	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}
	public IChannelDao getChannelDao() {
		return channelDao;
	}
	public void setChannelDao(IChannelDao channelDao) {
		this.channelDao = channelDao;
	}

	public void add(Channel channel, Integer pid) {
		Integer orders = channelDao.getMaxOrderByParent(pid);
		if(pid!=null&&pid>0) {
			Channel pc = channelDao.load(pid);
			if(pc==null) throw new MyException("要添加栏目的父类对象不正确!");
			channel.setParent(pc);
		}
		channel.setOrders(orders+1);
		//如果是导航栏目，设置导航的排序
		if(channel.getIsTopNav() == Constant.YES) {
			Integer navOrder = channelDao.getMaxIsTopNav();
			channel.setNavOrder(navOrder+1);
		}
		
		//如果customLinkUrl不为空，则设置为指定链接的栏目
		if ( !channel.getCustomLinkUrl().trim().isEmpty() ) {
			channel.setCustomLink( Constant.YES );
		}
		
		channelDao.add(channel);
	}

	public void update(Channel channel,int oldIsTopNav) {
		//如果是导航栏目，设置导航的排序
		if(channel.getIsTopNav() == Constant.YES && oldIsTopNav == Constant.NO) {
			Integer navOrder = channelDao.getMaxIsTopNav();
			channel.setNavOrder(navOrder+1);
		}
		
		channelDao.update(channel);
	}

	public void delete(int id) {
		//1、需要判断是否存在子栏目
		List<Channel> cs = channelDao.listByParent(id);
		if(cs!=null&&cs.size()>0) throw new MyException("要删除的栏目还有子栏目，请先删除子栏目");
		//2、需要判断是否存在文章
		List<Topic> ts = topicService.listTopicByChannel(id);
		if(ts.size()>0) {
			throw new MyException("该栏目还有相应的文章信息，请先删除文章");
		}
		//3、需要删除和组的关联关系
		channelDao.deleteChannelGroups(id);
		channelDao.delete(id);
	}

	public void clearTopic(int id) {
		List<Topic> tops = topicService.listTopicByChannel(id);
		for(Topic t:tops) {
			topicService.delete(t.getId());
		}
	}

	public Channel load(int id) {
		return channelDao.load(id);
	}

	public List<Channel> listByParent(Integer pid) {
		return channelDao.listByParent(pid);
	}
	@Override
	public List<ChannelTree> generateTree(int type) {
		return channelDao.generateTree(type);
	}
	@Override
	public List<ChannelTree> generateTree() {
		return channelDao.generateTree();
	}
	@Override
	public List<ChannelTree> generateTreeAll() {
		return channelDao.generateTreeAll();
	}
	@Override
	public List<ChannelTree> generateTreeByParent(Integer pid) {
		return channelDao.generateTreeByParent(pid);
	}
	@Override
	public void updateSort(Integer[] ids) {
		channelDao.updateSort(ids);
	}
	@Override
	public void updateTopNavSort(Integer[] ids) {
		channelDao.updateTopNavSort(ids);
	}
	@Override
	public List<Channel> listPublishChannel(int...type) {
		return channelDao.listPublishChannel(type);
	}
	@Override
	public List<Channel> listPublishChannel(int uid,int type) {
		return channelDao.listPublishChannel(uid,type);
	}
	@Override
	public List<Channel> listTopNavChannel(int num) {
		return channelDao.listTopNavChannel( num );
	}
	@Override
	public List<Channel> listTopNavChannelAll() {
		return channelDao.listTopNavChannelAll();
	}
	@Override
	public List<Channel> listAllIndexChannel(ChannelType ct) {
		return channelDao.listAllIndexChannel(ct);
	}

	@Override
	public Channel loadFirstChannelByNav(int cid) {
		return channelDao.loadFirstChannelByNav(cid);
	}

	@Override
	public List<Channel> listUseChannelByParent(Integer cid) {
		return channelDao.listUseChannelByParent(cid);
	}

	@Override
	public List<Channel> listChannelByType(ChannelType ct) {
		return channelDao.listChannelByType(ct);
	}
}
