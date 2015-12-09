package org.wxh.user.dao.impl;



import java.util.List;

import org.wxh.basic.dao.BaseDao;
import org.wxh.basic.model.Pager;
import org.wxh.topic.dao.impl.ChannelDao;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.ChannelTree;
import org.wxh.topic.model.ChannelType;
import org.wxh.user.dao.IGroupDao;
import org.wxh.user.model.Group;
import org.wxh.user.model.GroupChannel;
import org.springframework.stereotype.Repository;

@Repository("groupDao")
public class GroupDao extends BaseDao<Group> implements IGroupDao {

	@Override
	public List<Group> listGroup() {
		return this.list("from Group");
	}

	@Override
	public Pager<Group> findGroup() {
		return this.find("from Group");
	}

	@Override
	public void deleteGroupUsers(int gid) {
		this.updateByHql("delete UserGroup ug where ug.group.id=?",gid);
	}

	@Override
	public void addGroupChannel(Group group, Channel channel) {
		GroupChannel gc = this.loadGroupChannel(group.getId(), channel.getId());
		if(gc!=null) return;
		gc = new GroupChannel();
		gc.setGroup(group);
		gc.setChannel(channel);
		this.getSession().save(gc); //此处不能用BaseDao的add方法，因为add加了泛型，该DAO的泛型为Group
	}

	@Override
	public GroupChannel loadGroupChannel(int gid, int cid) {
		return (GroupChannel)this.queryObject("from GroupChannel where group.id=? and channel.id=?",
				new Object[]{gid,cid});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> listGroupChannelIds(int gid) {
		String hql = "select gc.channel.id from GroupChannel gc where gc.group.id=?";
		//此处不能用BaseDao的list方法，因为list加了泛型，该DAO的泛型为Group
		return this.getSession().createQuery(hql).setParameter(0, gid).list();
	}

	@Override
	public List<ChannelTree> generateGroupChannelTree(int gid) {
		String sql = "select c.id as id,c.name as name,c.pid as pid from " +
				"t_group_channel gc left join t_channel c on(gc.c_id=c.id) " +
				"where gc.g_id=?";
		List<ChannelTree> cts = this.listBySql(sql,gid,ChannelTree.class, false);
		ChannelDao.initTreeNode(cts);
		return cts;
	}

	@Override
	public List<ChannelTree> generateUserChannelTree(int uid) {
		String sql = "select distinct c.id as id,c.name as name,c.pid as pid from " +
				"t_group_channel gc left join t_channel c on(gc.c_id=c.id) left join t_user_group ug on(ug.g_id=gc.g_id)" +
				"where ug.u_id=? and c.type="+ChannelType.TOPIC_LIST.ordinal()+" or c.type="+ChannelType.NAV_CHANNEL.ordinal();
		List<ChannelTree> cts = this.listBySql(sql,uid,ChannelTree.class, false);
		ChannelDao.initTreeNode(cts);
		return cts;
	}
	@Override
	public List<ChannelTree> generateUserChannelTreeAll(int uid) {
		String sql = "select distinct c.id as id,c.name as name,c.pid as pid from " +
				"t_group_channel gc left join t_channel c on(gc.c_id=c.id) left join t_user_group ug on(ug.g_id=gc.g_id)" +
				"where ug.u_id=?";
		List<ChannelTree> cts = this.listBySql(sql,uid,ChannelTree.class, false);
		ChannelDao.initTreeNode(cts);
		return cts;
	}

	@Override
	public void clearGroupChannel(int gid) {
		this.updateByHql("delete GroupChannel gc where gc.group.id=?",gid);
	}

	@Override
	public void deleteGroupChannel(int gid, int cid) {
		this.updateByHql("delete GroupChannel gc where gc.group.id=? and gc.channel.id=?",new Object[]{gid,cid});
	}

	@Override
	public void deleteGroupChannel(int gid) {
		this.updateByHql("delete GroupChannel gc where gc.group.id=?",gid);
	}
}
