package org.wxh.user.dao;



import java.util.List;

import org.wxh.basic.dao.IBaseDao;
import org.wxh.basic.model.Pager;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.ChannelTree;
import org.wxh.user.model.Group;
import org.wxh.user.model.GroupChannel;

/**
 * 用户组DAO
 * @author wxh
 *
 */
public interface IGroupDao extends IBaseDao<Group> {
	/**
	 * 显示所有组
	 * @return
	 */
	public List<Group> listGroup();
	/**
	 * 显示所有组（分页）
	 * @return
	 */
	public Pager<Group> findGroup();
	/**
	 * 根据用户组ID，删除用户组-用户实体
	 * @param gid
	 */
	public void deleteGroupUsers(int gid);
	
	/**
	 * 添加GroupChannel对象
	 * @param group
	 * @param channel
	 */
	public void addGroupChannel(Group group,Channel channel);
	/**
	 * 加载GroupChannel对象
	 * @param gid
	 * @param cid
	 * @return
	 */
	public GroupChannel loadGroupChannel(int gid,int cid);
	/**
	 * 清空组所管理的栏目
	 * @param gid
	 */
	public void clearGroupChannel(int gid);
	/**
	 * 删除用户组栏目
	 * @param gid
	 * @param cid
	 */
	public void deleteGroupChannel(int gid,int cid);
	/**
	 * 删除用户组栏目
	 * @param gid
	 */
	public void deleteGroupChannel(int gid);
	/**
	 * 获取某个组的所有管理栏目的id
	 * @param gid
	 * @return
	 */
	public List<Integer> listGroupChannelIds(int gid);
	/**
	 * 获取某个组的栏目树
	 * @param gid
	 * @return
	 */
	public List<ChannelTree> generateGroupChannelTree(int gid);
	/**
	 * 获取某个用户的文章和导航栏目树
	 * @param uid
	 * @return
	 */
	public List<ChannelTree> generateUserChannelTree(int uid);
	/**
	 * 获取某个用户的所有栏目树
	 * @param uid
	 * @return
	 */
	public List<ChannelTree> generateUserChannelTreeAll(int uid);

}
