package org.wxh.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wxh.basic.common.Constant;
import org.wxh.basic.exception.MyException;
import org.wxh.topic.model.ChannelTree;
import org.wxh.user.auth.AuthClass;
import org.wxh.user.model.Group;
import org.wxh.user.service.IGroupService;
import org.wxh.user.service.IUserService;

/**
 * 用户组管理的控制层
 * @author wxh
 *
 */

@Controller
@RequestMapping("/admin/group")
@AuthClass
public class GroupController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IUserService userService;
	
	/**
	 * 用户组列表
	 */
	@RequestMapping("/groups")
	public String list(Model model) {
		model.addAttribute("datas",groupService.findGroup());
		return "group/list";
	}
	
	/**
	 * 添加用户组的界面
	 */
	@RequestMapping(value="/addUI",method=RequestMethod.POST)
	public String add(Model model) {
		model.addAttribute(new Group());
		return "group/add";
	}
	
	/**
	 * 添加用户组
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated Group group,BindingResult br, Model model) {
		if(br.hasErrors()) {
			return "group/add";
		}
		groupService.add(group);
		model.addAttribute(Constant.BaseCode.SUCCESS, "用户组添加成功!");
		return list(model);
	}
	
	/**
	 * 修改用户组的界面
	 */
	@RequestMapping(value="/updateUI/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,Model model) {
		model.addAttribute(groupService.load(id));
		return "group/update";
	}
	
	/**
	 * 修改用户组
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Validated Group group,BindingResult br,Model model) {
		if(br.hasErrors()) {
			return "group/update";
		}
		Group ug = groupService.load(id);
		ug.setDescr(group.getDescr());
		ug.setName(group.getName());
		groupService.update(ug);
		model.addAttribute(Constant.BaseCode.SUCCESS, "用户组修改成功!");
		return list(model);
	}
	
	/**
	 * 删除用户组
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable int id,Model model) {
		try {
			groupService.delete(id);
			model.addAttribute(Constant.BaseCode.SUCCESS, "用户组删除成功!");
		} catch (MyException e) {
			model.addAttribute(Constant.BaseCode.ERROR, e.getMessage());
		} finally {
			return list(model);
		}
	}
	
	/**
	 * 显示用户组
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(groupService.load(id));
		model.addAttribute("us", userService.listGroupUsers(id));
		return "group/show";
	}
	
	/**
	 * 清空用户
	 */
	@RequestMapping(value="/clearUsers/{id}",method=RequestMethod.POST)
	public String clearGroupUsers(Model model,@PathVariable int id) {
		groupService.deleteGroupUsers(id);
		model.addAttribute(Constant.BaseCode.SUCCESS, "已清除该用户组所有用户!");
		return list(model);
	}
	
	/**
	 * 查询管理栏目的界面
	 */
	@RequestMapping(value="/listChannels/{gid}",method=RequestMethod.POST)
	public String listChannels(@PathVariable int gid,Model model){
		model.addAttribute(groupService.load(gid));
		return "/group/listChannel";
	}
	
	/**
	 * 返回tree的json格式数据
	 */
	@RequestMapping(value="/groupTree/{gid}",method=RequestMethod.POST)
	public @ResponseBody List<ChannelTree> groupTree(@PathVariable Integer gid){
		
		return groupService.generateGroupChannelTree(gid);
	}
	
	/**
	 * 根据组id获取该组的所有管理栏目的id
	 */
	@RequestMapping(value="/groupChannelIds/{gid}",method=RequestMethod.POST)
	public @ResponseBody List<Integer> groupChannelIds(@PathVariable Integer gid){
		return groupService.listGroupChannelIds(gid);
	}
	
	/**
	 * 设置管理栏目的界面
	 */
	/*@RequestMapping(value="/setChannels/{gid}",method=RequestMethod.POST)
	public String setChannels(@PathVariable int gid,Model model) {
		model.addAttribute(groupService.load(gid));
		model.addAttribute("cids",groupService.listGroupChannelIds(gid));
		return "/group/setChannel";
	}*/
}
