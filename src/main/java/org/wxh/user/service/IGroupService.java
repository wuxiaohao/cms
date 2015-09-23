package org.wxh.user.service;



import java.util.List;

import org.wxh.basic.model.Pager;
import org.wxh.topic.model.ChannelTree;
import org.wxh.user.model.Group;
import org.wxh.user.model.GroupChannel;

/**
 * 用户组业务层
 * @author wxh
 *
 */
public interface IGroupService {
	/**
	 * 添加用户组
	 * @param group
	 */
	public void add(Group group);
	/**
	 * 删除用户组
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 加载用户组
	 * @param id
	 * @return
	 */
	public Group load(int id);
	/**
	 * 修改用户组
	 * @param group
	 */
	public void update(Group group);
	/**
	 * 添加或修改用户组
	 * @param group
	 */
	public void saveOrupdate(Group group);
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
	public void addGroupChannel(int gid,int cid);
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
	 * 删除用户栏目
	 * @param gid
	 * @param cid
	 */
	public void deleteGroupChannel(int gid,int cid);
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
	 * 获取某个用户的栏目树
	 * @param uid
	 * @return
	 */
	public List<ChannelTree> generateUserChannelTree(int uid);
}
