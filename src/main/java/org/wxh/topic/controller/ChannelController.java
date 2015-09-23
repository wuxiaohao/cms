package org.wxh.topic.controller;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wxh.basic.common.GlobalResult;
import org.wxh.util.EnumUtils;
import org.wxh.util.JsonUtil;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.ChannelTree;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.dto.AjaxObj;
import org.wxh.topic.model.dto.TreeDto;
import org.wxh.topic.service.IChannelService;
import org.wxh.user.auth.AuthClass;

/**
 * 栏目
 * @author wxh
 *
 */

@Controller
@RequestMapping("/admin/channel")
@AuthClass
public class ChannelController {
	
	@Autowired
	private IChannelService channelService;

	public IChannelService getChannelService() {
		return channelService;
	}
	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}
	
	@RequestMapping("/channels")
	public String list(Model model) {
		//model.addAttribute("treeDatas", JsonUtil.getInstance().obj2json(channelService.generateTree()));
		//return "channel/list_async";
		return "channel/list";
	}
	
	/**
	 * 获取整颗ztree（通过@ResponseBody注解自动封装成json对象）
	 * @return
	 */
	@RequestMapping(value="/treeAll",method=RequestMethod.POST)
	public @ResponseBody List<ChannelTree> tree() {
		return channelService.generateTree();
	}
	
	/**
	 * 异步加载示例
	 * @return
	 */
	@RequestMapping("/treeAs")
	public @ResponseBody List<TreeDto> tree(@Param Integer pid) {
		List<TreeDto> tds = new ArrayList<TreeDto>();
		if(pid==null||pid<=0) {
			tds.add(new TreeDto(0,"网站根栏目",1));
			return tds;
		}
		List<ChannelTree> cts = channelService.generateTreeByParent(pid);
		for(ChannelTree ct:cts) {
			tds.add(new TreeDto(ct.getId(),ct.getName(),1));
		}
		return tds;
	}
	
	/**
	 * 根据父id获取子栏目的列表
	 */
	@RequestMapping("/channels/{pid}")
	public String listChild(@PathVariable Integer pid,@Param Integer refresh,Model model) {
		Channel pc = null;
		if(refresh==null) {
			model.addAttribute("refresh",0);
		} else {
			model.addAttribute("refresh",1);
		}
		if(pid==null||pid<=0) {
			pc = new Channel();
			pc.setName(GlobalResult.ROOT_NAME);
			pc.setId(GlobalResult.ROOT_ID);
		} else
			pc = channelService.load(pid);
		model.addAttribute("pc", pc);
		model.addAttribute("channels",channelService.listByParent(pid));
		return "channel/list_child";
	}
	
	private void initAdd(Model model,Integer pid) {
		if(pid==null) pid = 0;
		Channel pc = null;
		if(pid==0) {
			pc = new Channel();
			pc.setId(GlobalResult.ROOT_ID);
			pc.setName(GlobalResult.ROOT_NAME);
		} else {
			pc = channelService.load(pid);
		}
		model.addAttribute("pc", pc);
		model.addAttribute("types", EnumUtils.enumProp2NameMap(ChannelType.class, "name"));
	}
	
	/**
	 * 添加栏目的界面
	 */
	@RequestMapping(value="/add/{pid}",method=RequestMethod.GET)
	public String add(@PathVariable Integer pid,Model model) {
		initAdd(model, pid);
		model.addAttribute(new Channel());
		return "channel/add";
	}
	
	/**
	 * 添加栏目
	 */
	@RequestMapping(value="/add/{pid}",method=RequestMethod.POST)
	public String add(@PathVariable Integer pid,Channel channel,BindingResult br,Model model) {
		if(br.hasErrors()) {
			initAdd(model, pid);
			return "channel/add";
		}
		channelService.add(channel, pid);
		//indexService.generateTop();
		return "redirect:/admin/channel/channels/"+pid+"?refresh=1";
	}
	
	/**
	 * 删除栏目
	 */
	@RequestMapping("/delete/{pid}/{id}")
	public String delete(@PathVariable Integer pid,@PathVariable Integer id,Model model) {
		channelService.delete(id);
		//indexService.generateTop();
		return "redirect:/admin/channel/channels/"+pid+"?refresh=1";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable Integer id,Model model) {
		Channel c = channelService.load(id);
		model.addAttribute("channel", c);
		Channel pc = null;
		if(c.getParent()==null) {
			pc = new Channel();
			pc.setId(GlobalResult.ROOT_ID);
			pc.setName(GlobalResult.ROOT_NAME);
		} else {
			pc = c.getParent();
		}
		model.addAttribute("pc",pc);
		model.addAttribute("types", EnumUtils.enumProp2NameMap(ChannelType.class, "name"));
		return "channel/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable Integer id,Channel channel,BindingResult br,Model model) {
		if(br.hasErrors()) {
			model.addAttribute("types", EnumUtils.enumProp2NameMap(ChannelType.class, "name"));	
			return "channel/update";
		}
		Channel tc = channelService.load(id);
		int pid = 0;
		if(tc.getParent()!=null) pid = tc.getParent().getId();
		tc.setCustomLink(channel.getCustomLink());
		tc.setCustomLinkUrl(channel.getCustomLinkUrl());
		tc.setIsIndex(channel.getIsIndex());
		tc.setIsTopNav(channel.getIsTopNav());
		tc.setName(channel.getName());
		tc.setRecommend(channel.getRecommend());
		tc.setStatus(channel.getStatus());
		tc.setType(channel.getType());
		tc.setNavOrder(channel.getNavOrder());
		channelService.update(tc);
		//indexService.generateTop();
		return "redirect:/admin/channel/channels/"+pid+"?refresh=1";
	}
	
	/**
	 * 存储拖动后保存的排序
	 */
	@RequestMapping("/channels/updateSort")
	public @ResponseBody AjaxObj updateSort(@Param Integer[] ids) {
		try {
			channelService.updateSort(ids);
			//indexService.generateTop();
		} catch (Exception e) {
			return new AjaxObj(0,e.getMessage());
		}
		return new AjaxObj(1);
	}
	
	
}
