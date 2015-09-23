package org.wxh.user.dao;



import java.util.List;

import org.wxh.basic.dao.IBaseDao;
import org.wxh.user.model.Role;
/**
 * 角色DAO
 * @author wxh
 *
 */
public interface IRoleDao extends IBaseDao<Role> {
	/**
	 * 获取所有角色列表
	 * @return
	 */
	public List<Role> listRole();
	/**
	 * 根据角色ID，删除用户-角色实体
	 * @param rid  角色ID
	 */ 
	public void deleteRoleUsers(int rid);
}
