package org.wxh.topic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wxh.basic.common.Constant;
import org.wxh.basic.exception.MyException;
import org.wxh.basic.model.AjaxObj;
import org.wxh.index.service.IIndexService;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.ChannelTree;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.dto.TreeDto;
import org.wxh.topic.service.IChannelService;
import org.wxh.user.auth.AuthClass;
import org.wxh.user.auth.AuthMethod;
import org.wxh.util.EnumUtils;

/**
 * 栏目管理
 * @author wxh
 *
 */

@Controller
@RequestMapping("/admin/channel")
@AuthClass("login")
public class ChannelController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IChannelService channelService;
	@Autowired
	private IIndexService indexService;
	
	@RequestMapping(value = "/channels",method=RequestMethod.POST)
	@AuthMethod(role={Constant.AuthConstant.ROLE_COMMADMIN})
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
	@AuthMethod(role={Constant.AuthConstant.ROLE_COMMADMIN})
	public @ResponseBody List<ChannelTree> tree() {
		return channelService.generateTreeAll();
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
	@AuthMethod(role={Constant.AuthConstant.ROLE_COMMADMIN})
	public String listChild(@PathVariable Integer pid,@Param Integer refresh,Model model) {
		Channel pc = null;
		if(refresh==null) {
			model.addAttribute("refresh",0);
		} else {
			model.addAttribute("refresh",1);
		}
		if(pid==null||pid<=0) {
			pc = new Channel();
			pc.setName(Constant.ROOT_NAME);
			pc.setId(Constant.ROOT_ID);
		} else
			pc = channelService.load(pid);
		model.addAttribute("pc", pc);
		model.addAttribute("channels",channelService.listByParent(pid));
		return "channel/list_child";
	}
	
	private void initAdd(Model model,Integer pid) {
		if(pid==null) pid = 0;
		Channel pc = null;
		if(pid==Constant.NO) {
			pc = new Channel();
			pc.setId(Constant.ROOT_ID);
			pc.setName(Constant.ROOT_NAME);
		} else {
			pc = channelService.load(pid);
		}
		model.addAttribute("pc", pc);
		Map<String, String> enu = EnumUtils.enumProp2NameMap(ChannelType.class, "name");
		enu.remove("TOPIC_IMG");
		model.addAttribute("types", enu);
	}
	/**
	 * 添加栏目的界面
	 */
	@RequestMapping(value="/add/{pid}",method=RequestMethod.GET)
	@AuthMethod(role={Constant.AuthConstant.ROLE_COMMADMIN})
	public String add(@PathVariable Integer pid,Model model) {
		initAdd(model, pid);
		model.addAttribute(new Channel());
		return "channel/add";
	}
	/**
	 * 添加栏目
	 */
	@RequestMapping(value="/add/{pid}",method=RequestMethod.POST)
	@AuthMethod(role={Constant.AuthConstant.ROLE_COMMADMIN})
	public String add(@PathVariable Integer pid,Channel channel,BindingResult br,Model model) {
		if(br.hasErrors()) {
			initAdd(model, pid);
			return "channel/add";
		}
		try {
			channelService.add(channel, pid);
			indexService.generateTop(); //重新生成静态页面的顶部
			model.addAttribute(Constant.BaseCode.SUCCESS, "栏目已添加成功!");	
		} catch (MyException e) {
			model.addAttribute(Constant.BaseCode.ERROR, e.getMessage());	
			logger.error("栏目添加异常！异常消息：{}", e.getMessage());
		} finally {
			return listChild(pid,1,model);
		}
	}
	/**
	 * 删除栏目
	 */
	@RequestMapping(value = "/delete/{pid}/{id}", method = RequestMethod.GET)
	@AuthMethod(role={Constant.AuthConstant.ROLE_COMMADMIN})
	public String delete(@PathVariable Integer pid,@PathVariable Integer id,Model model) {
		try {
			channelService.delete(id);
			indexService.generateTop(); //重新生成静态页面的顶部
			model.addAttribute(Constant.BaseCode.SUCCESS, "栏目已删除成功!");	
		} catch (MyException e) {
			model.addAttribute(Constant.BaseCode.ERROR, e.getMessage());	
			logger.error("栏目删除异常！异常消息：{}", e.getMessage());
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
	@AuthMethod(role={Constant.AuthConstant.ROLE_COMMADMIN})
	public String update(@PathVariable Integer id,Model model) {
		Channel c = channelService.load(id);
		model.addAttribute("channel", c);
		Channel pc = null;
		if(c.getParent()==null) {
			pc = new Channel();
			pc.setId(Constant.ROOT_ID);
			pc.setName(Constant.ROOT_NAME);
		} else {
			pc = c.getParent();
		}
		model.addAttribute("pc",pc);
		Map<String, String> enu = EnumUtils.enumProp2NameMap(ChannelType.class, "name");
		enu.remove("TOPIC_IMG");
		model.addAttribute("types", enu);
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
	@AuthMethod(role={Constant.AuthConstant.ROLE_COMMADMIN})
	public String update(@PathVariable Integer id,Channel channel,BindingResult br,Model model) {
		if(br.hasErrors()) {
			model.addAttribute("types", EnumUtils.enumProp2NameMap(ChannelType.class, "name"));	
			return "channel/update";
		}
		Channel tc = channelService.load(id);
		
		int oldIsTopNav = tc.getIsTopNav(); //原来是否为导航栏目
		int pid = 0;
		if(tc.getParent()!=null) pid = tc.getParent().getId();
		
		tc.setCustomLinkUrl(channel.getCustomLinkUrl());
		tc.setIsIndex(channel.getIsIndex());
		tc.setIsTopNav(channel.getIsTopNav());
		tc.setName(channel.getName());
		tc.setRecommend(channel.getRecommend());
		tc.setStatus(channel.getStatus());
		tc.setType(channel.getType());
		//如果customLinkUrl不为空，则设置为指定链接的栏目
		if ( !channel.getCustomLinkUrl().trim().isEmpty() ) 
			tc.setCustomLink( Constant.YES );
		else
			tc.setCustomLink( Constant.NO );
		
		channelService.update(tc,oldIsTopNav);
		indexService.generateTop(); //重新生成静态页面的顶部
		model.addAttribute(Constant.BaseCode.SUCCESS, "栏目更新成功!");	
		return listChild(pid,1,model);
	}
	
	/**
	 * 存储拖动后保存的排序
	 * @param ids 栏目id
	 * @return
	 */
	@RequestMapping(value="/channels/updateSort",method=RequestMethod.POST)
	@AuthMethod(role={Constant.AuthConstant.ROLE_COMMADMIN})
	public @ResponseBody AjaxObj updateSort(@Param Integer[] ids) {
		try {
			channelService.updateSort(ids);
			indexService.generateTop(); //重新生成静态页面的顶部
		} catch (Exception e) {
			logger.error("生成静态顶部页面失败！异常消息：{}", e.getMessage());
			return new AjaxObj(Constant.NO,e.getMessage());
		}
		return new AjaxObj(Constant.YES);
	}
	/**
	 * 存储顶部栏目拖动后保存的排序
	 * @param ids 栏目id
	 * @return
	 */
	@RequestMapping(value = "/updateTopNavSort" ,method = RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public @ResponseBody AjaxObj updateTopNavSort(@Param Integer[] ids){
		try {
			channelService.updateTopNavSort(ids);
			indexService.generateTop(); //重新生成静态页面的顶部
		} catch (Exception e) {
			logger.error("生成静态顶部页面失败！异常消息：{}", e.getMessage());
			return new AjaxObj(Constant.NO,e.getMessage());
		}
		return new AjaxObj(Constant.YES);
	}
	
	/**
	 * 显示顶部栏目排序的页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/TopNavchannels",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String listTopNav(Model model){
		model.addAttribute("channels", channelService.listTopNavChannelAll());
		return "channel/listTopNavchannel";
	}
	
}
