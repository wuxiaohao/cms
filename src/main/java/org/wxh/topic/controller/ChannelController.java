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
import org.wxh.basic.exception.MyException;
import org.wxh.index.service.IIndexService;
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
 * 栏目管理
 * @author wxh
 *
 */

@Controller
@RequestMapping("/admin/channel")
@AuthClass
public class ChannelController {
	
	@Autowired
	private IChannelService channelService;
	@Autowired
	private IIndexService indexService;

	public IIndexService getIndexService() {
		return indexService;
	}
	public void setIndexService(IIndexService indexService) {
		this.indexService = indexService;
	}
	public IChannelService getChannelService() {
		return channelService;
	}
	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}
	
	@RequestMapping(value = "/channels",method=RequestMethod.POST)
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
	@RequestMapping(value="/treeAs",method=RequestMethod.POST)
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
		try {
			channelService.add(channel, pid);
			indexService.generateTop(); //重新生成静态页面的顶部
			model.addAttribute("success", "栏目已添加成功!");	
		} catch (MyException e) {
			model.addAttribute("error", e.getMessage());	
		} finally {
			return listChild(pid,1,model);
		}
	}
	/**
	 * 删除栏目
	 */
	@RequestMapping(value = "/delete/{pid}/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer pid,@PathVariable Integer id,Model model) {
		try {
			channelService.delete(id);
			indexService.generateTop(); //重新生成静态页面的顶部
			model.addAttribute("success", "栏目已删除成功!");	
		} catch (MyException e) {
			model.addAttribute("error", e.getMessage());	
		} finally {
			return listChild(pid,1,model);
		}
	}
	/**
	 * 修改栏目的页面
	 * @param id
	 * @param model
	 * @return
	 */
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
	/**
	 * 修改栏目
	 * @param id
	 * @param channel
	 * @param br
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable Integer id,Channel channel,BindingResult br,Model model) {
		if(br.hasErrors()) {
			model.addAttribute("types", EnumUtils.enumProp2NameMap(ChannelType.class, "name"));	
			return "channel/update";
		}
		Channel tc = channelService.load(id);
		int oldIsTopNav = tc.getIsTopNav(); //原来是否为导航栏目
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
		channelService.update(tc,oldIsTopNav);
		indexService.generateTop(); //重新生成静态页面的顶部
		model.addAttribute("success", "栏目更新成功!");	
		return listChild(pid,1,model);
	}
	
	/**
	 * 存储拖动后保存的排序
	 * @param ids 栏目id
	 * @return
	 */
	@RequestMapping(value="/channels/updateSort",method=RequestMethod.POST)
	public @ResponseBody AjaxObj updateSort(@Param Integer[] ids) {
		try {
			channelService.updateSort(ids);
			indexService.generateTop(); //重新生成静态页面的顶部
		} catch (Exception e) {
			return new AjaxObj(0,e.getMessage());
		}
		return new AjaxObj(1);
	}
	/**
	 * 存储顶部栏目拖动后保存的排序
	 * @param ids 栏目id
	 * @return
	 */
	@RequestMapping(value = "/updateTopNavSort" ,method = RequestMethod.POST)
	public @ResponseBody AjaxObj updateTopNavSort(@Param Integer[] ids){
		try {
			channelService.updateTopNavSort(ids);
			indexService.generateTop(); //重新生成静态页面的顶部
		} catch (Exception e) {
			return new AjaxObj(0,e.getMessage());
		}
		return new AjaxObj(1);
	}
	
	/**
	 * 显示顶部栏目排序的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/TopNavchannels",method=RequestMethod.POST)
	public String listTopNav(Model model){
		model.addAttribute("channels", channelService.listTopNavChannelAll());
		return "channel/listTopNavchannel";
	}
	
}
