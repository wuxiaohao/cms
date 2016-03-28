package org.wxh.user.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.exception.MyException;
import org.wxh.user.dao.IRoleDao;
import org.wxh.user.dao.IUserDao;
import org.wxh.user.model.Role;
import org.wxh.user.model.User;
import org.wxh.user.service.IRoleService;

@Service("roleService")
public class RoleService implements IRoleService {
	
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IUserDao userDao;
	
	
	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void add(Role role) {
		roleDao.add(role);
	}

	@Override
	public void delete(int id) {
		List<User> us = userDao.listRoleUsers(id);
		if(us!=null&&us.size()>0) throw new MyException("删除的角色对象中还有用户，不能删除！");
		roleDao.delete(id);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public Role load(int id) {
		return roleDao.load(id);
	}

	@Override
	public List<Role> listRole() {
		return roleDao.listRole();
	}

	@Override
	public void deleteRoleUsers(int rid) {
		roleDao.deleteRoleUsers(rid);
	}

}
