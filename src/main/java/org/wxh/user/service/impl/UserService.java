package org.wxh.user.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.exception.CmsException;
import org.wxh.basic.exception.MyException;
import org.wxh.basic.model.Pager;
import org.wxh.basic.util.JsonUtils;
import org.wxh.user.dao.IGroupDao;
import org.wxh.user.dao.IRoleDao;
import org.wxh.user.dao.IUserDao;
import org.wxh.user.model.Group;
import org.wxh.user.model.Role;
import org.wxh.user.model.User;
import org.wxh.user.service.IUserService;
import org.wxh.util.SecurityUtil;

@Service("userService")
public class UserService implements IUserService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IGroupDao groupDao;
	

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IGroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}
	
	private void addUserRole(User user,int rid) {
		//1、检查角色对象是否存在，如果不存在，就抛出异常
		Role role = roleDao.load(rid);
		if(role==null) {
			logger.error("要添加的角色不存在，请求参数：[user:{},rid:{}]",new Object[]{JsonUtils.object2String(user),rid});
			throw new CmsException("要添加的用户角色不存在");
		}
		//2、检查用户角色对象是否已经存在，如果存在，就不添加
		userDao.addUserRole(user, role);
	}
	
	private void addUserGroup(User user,int gid) {
		Group group = groupDao.load(gid);
		if(group==null) {
			logger.error("要添加的角色不存在，请求参数：[user:{},gid:{}]",new Object[]{JsonUtils.object2String(user),gid});
			throw new CmsException("要添加用户的组对象不存在");
		}
		userDao.addUserGroup(user, group);
	}

	@Override
	public void add(User user, Integer[] rids, Integer[] gids) {
		user.setCreateDate(new Date());
		try {
			user.setPassword(SecurityUtil.md5(user.getUsername(),user.getPassword())); //MD5密码加密
		} catch (NoSuchAlgorithmException e) {
			logger.error("密码加密失败，请求参数：[user:{},rids:{},gids:{}]",new Object[]{JsonUtils.object2String(user),rids.toString(),gids.toString()});
			throw new CmsException("密码加密失败:"+e.getMessage());
		}
		userDao.add(user);
		//添加角色对象
		for(Integer rid:rids) {
			this.addUserRole(user, rid);
		}
		//添加用户组对象
		for(Integer gid:gids) {
			addUserGroup(user, gid);
		}
	}
	
	public boolean checkUserName(String username){
		User user = userDao.loadByUsername(username);
		if(user != null ) return true;
		return false;
	}
	

	@Override
	public void delete(int id) {
		//TODO 需要进行用户是否有文章的判断
		
		//1、删除用户管理的角色对象
		userDao.deleteUserGroups(id);
		//2、删除用户管理的组对象
		userDao.deleteUserRoles(id);
		userDao.delete(id);
	}

	@Override
	public void update(User user, Integer[] rids, Integer[] gids) {
		//1、获取用户已经存在的组id和角色id
		List<Integer> erids = userDao.listUserRoleIds(user.getId());
		List<Integer> egids = userDao.listUserGroupIds(user.getId());
		//2、判断，如果erids中不存在rids就要进行添加
		for(Integer rid:rids) {
			if(!erids.contains(rid)) {
				addUserRole(user, rid);
			}
		}
		for(Integer gid:gids) {
			if(!egids.contains(gid)) {
				addUserGroup(user,gid);
			}
		}
		//3、进行删除
		for(Integer erid:erids) {
			if(!ArrayUtils.contains(rids, erid)) {
				userDao.deleteUserRole(user.getId(), erid);
			}
		}
		
		for(Integer egid:egids) {
			if(!ArrayUtils.contains(gids, egid)) {
				userDao.deleteUserGroup(user.getId(), egid);
			}
		}
	}

	@Override
	public void updateStatus(int id) {
		User u = userDao.load(id);
		if(u==null) throw new CmsException("修改状态的用户不存在");
		if(u.getStatus()==0) u.setStatus(1);
		else u.setStatus(0);
		userDao.update(u);
	}

	@Override
	public Pager<User> findUser() {
		return userDao.findUser();
	}

	@Override
	public User load(int id) {
		return userDao.load(id);
	}

	@Override
	public List<Role> listUserRoles(int id) {
		return userDao.listUserRoles(id);
	}

	@Override
	public List<Group> listUserGroups(int id) {
		return userDao.listUserGroups(id);
	}
	@Override
	public List<Integer> listUserRoleIds(int id) {
		return userDao.listUserRoleIds(id);
	}
	@Override
	public List<Integer> listUserGroupIds(int id) {
		return userDao.listUserGroupIds(id);
	}
	@Override
	public List<User> listGroupUsers(int gid) {
		return userDao.listGroupUsers(gid);
	}
	@Override
	public List<User> listRoleUsers(int rid) {
		return userDao.listRoleUsers(rid);
	}
	@Override
	public User login(String username, String password) {
		User user = userDao.loadByUsername(username);
		if(user==null) {
			logger.error("用户名或者密码不正确，请求参数：[username:{},password:{}]", new Object[]{username, password});
			throw new MyException("用户名或者密码不正确");
		}
		try {
			if(!SecurityUtil.md5(username,password).equals(user.getPassword())) {
				logger.error("用户名或者密码不正确，请求参数：[username:{},password:{}]", new Object[]{username, password});
				throw new MyException("用户名或者密码不正确");
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error("密码加密失败，请求参数：[username:{},password:{}]", new Object[]{username, password});
			throw new MyException("密码加密失败:"+e.getMessage());
		}
		if(user.getStatus()==0) {
			logger.error("用户已经停用，请求参数：[username:{},password:{}]", new Object[]{username, password});
			throw new MyException("用户已经停用，请与管理员联系");
		}
		return user;
	}
	@Override
	public void update(User user) {
		userDao.update(user);
	}
	@Override
	public void updatePwd(int uid, String oldPwd, String newPwd) {
		try {
			User u = userDao.load(uid);
			if(!SecurityUtil.md5(u.getUsername(),oldPwd).equals(u.getPassword())) {
				throw new MyException("原始密码输入不正确!");
			}
			u.setPassword(SecurityUtil.md5(u.getUsername(), newPwd));
			userDao.update(u);
		} catch (NoSuchAlgorithmException e) {
			throw new MyException("更新密码失败:"+e.getMessage());
		}
	}

	@Override
	public void updatePwdByNewPwd(int uid,String newPwd) {
		try {
			User user = userDao.load(uid);
			user.setPassword(SecurityUtil.md5(user.getUsername(),newPwd));
			userDao.update(user);
		} catch (NoSuchAlgorithmException e) {
			logger.error("修改密码异常，异常信息：[{}]",e.getMessage());
		}
	}
	
}
