package org.wxh.user.service;


import java.util.List;

import org.wxh.user.model.Role;




/**
 * 角色业务层
 * @author wxh
 *
 */
public interface IRoleService {
	/**
	 * 添加角色
	 * @param role
	 */
	public void add(Role role);
	/**
	 * 删除角色
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 修改角色
	 * @param role
	 */
	public void update(Role role);
	/**
	 * 加载角色
	 * @param id
	 * @return
	 */
	public Role load(int id);
	/**
	 * 显示所有角色
	 * @return
	 */
	public List<Role> listRole();
	/**
	 * 根据角色ID，删除角色-用户实体
	 * @param gid
	 */
	public void deleteRoleUsers(int rid);
}
