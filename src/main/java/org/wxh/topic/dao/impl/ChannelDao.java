package org.wxh.topic.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.wxh.basic.common.Constant;
import org.wxh.basic.dao.BaseDao;
import org.wxh.topic.dao.IChannelDao;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.ChannelTree;
import org.wxh.topic.model.ChannelType;

/**
 * 栏目的dao
 * @author wxh
 *
 */
@Repository("channelDao")
public class ChannelDao extends BaseDao<Channel> implements IChannelDao {
	
	/**
	 * 初始化树
	 */
	public static void initTreeNode(List<ChannelTree> cts) {
		cts.add(0,new ChannelTree(Constant.ROOT_ID,Constant.ROOT_NAME,-1));//设置根目录
		for(ChannelTree ct:cts) {  
			if(ct.getPid()==null)ct.setPid(0);  //如果没有父节点，则设置父节点为0
		}
	}

	@Override
	public List<Channel> listByParent(Integer pid) {
		String hql = "select c from Channel c left join fetch c.parent cp where cp.id="+pid+" order by c.orders";
		if(pid==null||pid==0) hql = "select c from Channel c where c.parent is null order by c.orders";
		return this.list(hql);
	}

	@Override
	public int getMaxOrderByParent(Integer pid) {
		String hql = "select max(c.orders) from Channel c where c.parent.id="+pid;
		if(pid==null||pid==0) hql = "select max(c.orders) from Channel c where c.parent is null";
		Object obj = this.queryObject(hql);
		if(obj==null) return 0;
		return (Integer)obj;
	}

	@Override
	public List<ChannelTree> generateTree(int type) {
		String sql = "select id,name,pid from t_channel c where c.type="+type+" or c.type="+ChannelType.NAV_CHANNEL.ordinal()+" order by orders";
		List<ChannelTree> cts = this.listBySql(sql, ChannelTree.class, false);
		initTreeNode(cts);	//初始化树
		return cts;
	}
	@Override
	public List<ChannelTree> generateTree() {
		String sql = "select id,name,pid from t_channel c where c.type!="+ChannelType.IMG_NEW.ordinal()+" and c.type!="+ChannelType.VIDEO_NEW.ordinal()+" order by orders";
		List<ChannelTree> cts = this.listBySql(sql, ChannelTree.class, false);
		initTreeNode(cts);	//初始化树
		return cts;
	}
	@Override
	public List<ChannelTree> generateTreeAll() {
		String sql = "select id,name,pid from t_channel order by orders";
		List<ChannelTree> cts = this.listBySql(sql, ChannelTree.class, false);
		initTreeNode(cts);	//初始化树
		return cts;
	}

	@Override
	public List<ChannelTree> generateTreeByParent(Integer pid) {
		if(pid==null||pid==0) {
			return this.listBySql("select id,name,pid from t_channel where pid is null order by orders", ChannelTree.class, false);
		} else {
			return this.listBySql("select id,name,pid from t_channel where pid="+pid+" order by orders", 
					ChannelTree.class,false);
		}
	}

	@Override
	public void updateSort(Integer[] ids) {
		int index = 1;
		String hql = "update Channel c set c.orders=? where c.id=?";
		for(Integer id:ids) {
			this.updateByHql(hql, new Object[]{index++,id});
		}
	}
	@Override
	public void updateTopNavSort(Integer[] ids){
		int navOrder = 1;
		String hql = "update Channel c set c.navOrder=? where c.id=?";
		for(Integer id:ids){
			this.updateByHql(hql, new Object[]{navOrder++,id});
		}
	};
	@Override
	public List<Channel> listPublishChannel(int type) {
		String hql = "select new Channel(c.id,c.name) from Channel c where c.status=0 and c.type="+type;
		return this.list(hql);
	}
	@Override
	public List<Channel> listPublishChannel() {
		String hql = "select new Channel(c.id,c.name) from Channel c where c.status=0 and c.type!="+ChannelType.IMG_NEW.ordinal() + " and c.type!="+ChannelType.VIDEO_NEW.ordinal();
		return this.list(hql);
	}
	@Override
	public List<Channel> listPublishChannelByUid(int uid,int type) {
		String sql = "select distinct c.id as id,c.name as name from t_group_channel gc "
				+ "left join t_channel c on(gc.c_id=c.id) left join t_user_group ug on(ug.g_id=gc.g_id) "
				+ "where ug.u_id=? and c.status=0 and c.type=?";
		List<Channel> cts = this.listBySql(sql,new Object[]{uid,type},Channel.class, false);
		return cts;
	}
	@Override
	public List<Channel> listPublishChannelByUid(int uid) {
		String sql = "select distinct c.id as id,c.name as name from t_group_channel gc "
				+ "left join t_channel c on(gc.c_id=c.id) left join t_user_group ug on(ug.g_id=gc.g_id) "
				+ "where ug.u_id=? and c.status=0 and c.type!="+ChannelType.IMG_NEW.ordinal() + " and c.type !="+ChannelType.VIDEO_NEW.ordinal();
		List<Channel> cts = this.listBySql(sql,uid,Channel.class, false);
		return cts;
	}
	@Override
	public List<Channel> listTopNavChannel(int num) {
		String hql = "select new Channel(c.id,c.name,c.customLink,c.customLinkUrl) " +
				"from Channel c where c.status=0 and c.isTopNav=1 order by navOrder";
		return this.getSession().createQuery( hql ).setFirstResult( 0 ).setMaxResults( num ).list();
	}
	@Override
	public List<Channel> listTopNavChannelAll() {
		String hql = "select c from Channel c where c.status=0 and c.isTopNav=1 order by navOrder";
		return this.list(hql);
	}

	@Override
	public List<Channel> listAllIndexChannel(ChannelType ct) {
		String hql = "select new Channel(c.id,c.name) " +
				"from Channel c where c.status=0 and c.isIndex=1";
		if(ct!=null) {
			hql+=" and c.type="+ct.ordinal();
		}
		return this.list(hql);
	}

	@Override
	public void deleteChannelGroups(int cid) {
		String hql = "delete GroupChannel gc where gc.channel.id=?";
		this.getSession().createQuery(hql).setParameter(0, cid).executeUpdate();
	}

	@Override
	public Channel loadFirstChannelByNav(int pid) {
		String hql = "select new Channel(c.id,c.name,c.type) from Channel c where c.parent.id=? order by c.orders";
		return (Channel)this.getSession().createQuery(hql)
			.setFirstResult(0).setMaxResults(1).setParameter(0, pid).uniqueResult();
	}

	@Override
	public List<Channel> listUseChannelByParent(Integer pid) {
		String hql;
		if( pid == null || pid == 0 ) {
			hql = "select c from Channel c where c.parent is null and c.status=0 order by c.orders";
		} else {
			hql = "select c from Channel c where c.parent.id=" + pid + " and c.status=0 order by c.orders";
			//hql = "select c from Channel c left join fetch c.parent cp where cp.id="+pid+" and cp.status=0 order by c.orders";
		}
		return this.list(hql);
	}

	@Override
	public List<Channel> listChannelByType(ChannelType ct) {
		String hql = "select new Channel(c.id,c.name) from Channel c where c.status=0 and c.type=?";
		return this.list(hql,ct);
	}

	@Override
	public Integer getMaxIsTopNav() {
		String hql = "select max(c.navOrder) from Channel c where c.isTopNav=?";
		Object obj = this.queryObject(hql, 1);
		if(obj==null) return 0;
		return (Integer)obj;
	}
}
