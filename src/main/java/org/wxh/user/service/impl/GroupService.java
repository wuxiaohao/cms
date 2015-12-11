package org.wxh.user.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.exception.CmsException;
import org.wxh.basic.exception.MyException;
import org.wxh.basic.model.Pager;
import org.wxh.topic.dao.IChannelDao;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.ChannelTree;
import org.wxh.user.dao.IGroupDao;
import org.wxh.user.dao.IUserDao;
import org.wxh.user.model.Group;
import org.wxh.user.model.GroupChannel;
import org.wxh.user.model.User;
import org.wxh.user.service.IGroupService;

@Service("groupService")
public class GroupService implements IGroupService{
	@Autowired
	private IGroupDao groupDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IChannelDao channelDao;

	
	public IChannelDao getChannelDao() {
		return channelDao;
	}
	
	public void setChannelDao(IChannelDao channelDao) {
		this.channelDao = channelDao;
	}
	public IGroupDao getGroupDao() {
		return groupDao;
	}
	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void add(Group group) {
		groupDao.add(group);
	}

	@Override
	public void delete(int id) {
		List<User> users = userDao.listGroupUsers(id);
		if(users!=null&&users.size()>0) throw new MyException("删除的组中还有用户，不能删除");
		//删除用户组-栏目关联实体
		groupDao.deleteGroupChannel(id);
		//删除用户组
		groupDao.delete(id);
	}

	@Override
	public Group load(int id) {
		return groupDao.load(id);
	}

	@Override
	public void update(Group group) {
		groupDao.update(group);
	}

	@Override
	public List<Group> listGroup() {
		return groupDao.listGroup();
	}

	@Override
	public Pager<Group> findGroup() {
		return groupDao.findGroup();
	}

	@Override
	public void deleteGroupUsers(int gid) {
		groupDao.deleteGroupUsers(gid);
	}
	@Override
	public void addGroupChannel(int gid, int cid) {
		Group g = groupDao.load(gid);
		Channel c = channelDao.load(cid);
		if(c==null||g==null)throw new CmsException("要添加的组频道关联对象不存在");
		groupDao.addGroupChannel(g, c);
	}
	@Override
	public GroupChannel loadGroupChannel(int gid, int cid) {
		return groupDao.loadGroupChannel(gid, cid);
	}
	@Override
	public void clearGroupChannel(int gid) {
		groupDao.clearGroupChannel(gid);
	}
	@Override
	public void deleteGroupChannel(int gid, int cid) {
		groupDao.deleteGroupChannel(gid, cid);
	}
	@Override
	public List<Integer> listGroupChannelIds(int gid) {
		return groupDao.listGroupChannelIds(gid);
	}
	@Override
	public List<ChannelTree> generateGroupChannelTree(int gid) {
		return groupDao.generateGroupChannelTree(gid);
	}
	@Override
	public List<ChannelTree> generateUserChannelTree(int uid,int type) {
		return groupDao.generateUserChannelTree(uid,type);
	}
	@Override
	public List<ChannelTree> generateUserChannelTree(int uid) {
		return groupDao.generateUserChannelTree(uid);
	}
	@Override
	public void saveOrupdate(Group group) {
		groupDao.saveOrupdate(group);
	}

	@Override
	public List<ChannelTree> generateUserChannelTreeAll(int uid) {
		return groupDao.generateUserChannelTreeAll(uid);
	}

}
