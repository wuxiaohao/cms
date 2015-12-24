package org.wxh.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wxh.basic.exception.MyException;
import org.wxh.util.EnumUtils;
import org.wxh.user.auth.AuthClass;
import org.wxh.user.model.Role;
import org.wxh.user.model.RoleType;
import org.wxh.user.service.IRoleService;
import org.wxh.user.service.IUserService;

/**
 * 角色管理的控制层
 * @author wxh
 *
 */

@Controller
@RequestMapping("/admin/role")
@AuthClass
public class RoleController {
	
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserService userService;
	
	public IRoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 角色列表（不分页）
	 */
	@RequestMapping(value="/roles",method=RequestMethod.POST)
	public String list(Model model) {
		model.addAttribute("roles", roleService.listRole());
		return "role/list";
	}
	
	/**
	 * 显示某个角色信息
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(roleService.load(id));
		model.addAttribute("us",userService.listRoleUsers(id));
		return "role/show";
	}
	
	/**
	 * 删除角色
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(Model model,@PathVariable int id) {
		try {
			roleService.delete(id);
			model.addAttribute("success", "角色删除成功!");
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		} finally {
			return list(model);
		}
	}
	
	/**
	 * 清空该角色的用户
	 */
	@RequestMapping(value="/clearUsers/{id}",method=RequestMethod.POST)
	public String clearUsers(Model model,@PathVariable int id) {
		roleService.deleteRoleUsers(id);
		model.addAttribute("success", "已清除该角色所有用户!");
		return list(model);
	}
	
	/**
	 * 返回添加角色的界面
	 */
	@RequestMapping(value="/addUI",method=RequestMethod.POST)
	public String addUI(Model model) {
		model.addAttribute(new Role());
		model.addAttribute("types", EnumUtils.enumProp2NameMap(RoleType.class,"name"));
		return "role/add";
	}
	
	/**
	 * 添加角色
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Model model,Role role) {
		roleService.add(role);
		model.addAttribute("success", "角色添加成功!");
		return list(model);
	}
	
	/**
	 * 修改角色的界面
	 */
	@RequestMapping(value="/updateUI/{id}",method=RequestMethod.POST)
	public String updateUI(@PathVariable int id,Model model) {
		model.addAttribute(roleService.load(id));
		model.addAttribute("types", EnumUtils.enumProp2NameMap(RoleType.class,"name"));
		return "role/update";
	}
	
	/**
	 * 修改角色
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(Model model,@PathVariable int id,Role role) {
		Role er = roleService.load(id);
		er.setName(role.getName());
		er.setRoleType(role.getRoleType());
		roleService.update(er);
		model.addAttribute("success", "角色修改成功!");
		return list(model);
	}
}
