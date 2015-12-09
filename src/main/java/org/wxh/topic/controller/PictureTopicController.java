package org.wxh.topic.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wxh.basic.model.SystemContext;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.PictureTopic;
import org.wxh.topic.model.Topic;
import org.wxh.topic.service.IChannelService;
import org.wxh.topic.service.IKeywordService;
import org.wxh.topic.service.IPictureService;
import org.wxh.topic.service.IPictureTopicService;
import org.wxh.user.auth.AuthMethod;
import org.wxh.user.model.User;
import org.wxh.user.service.IGroupService;

/**
 * 图片文章控制层
 * @author wxh
 *
 */
@Controller
@RequestMapping("/admin/picTopic")
public class PictureTopicController {
	private static final Logger logger = Logger.getLogger(TopicController.class);
	
	@Autowired
	private IPictureTopicService pictureTopicService;
	@Autowired
	private IPictureService pictureService;
	@Autowired
	private IKeywordService keywordService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IChannelService channelService;
	
	private void initList(String con,Integer cid,Model model,HttpSession session,Integer status) {
		if(status == 1){ //如果是获取已发布文章的列表，则按发布时间排序
			SystemContext.setSort("t.publishDate"); 
		} else { //如果是获取未发布文章的列表，则按创建时间排序
			SystemContext.setSort("t.createDate"); 
		}
		SystemContext.setOrder("desc");
		boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		if(isAdmin) { //如果是超级管理员，则返回所有的文章
			model.addAttribute("datas",pictureTopicService.find(cid, con, status));
			SystemContext.removeOrder();
			SystemContext.removeSort();
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.TOPIC_IMG.ordinal()));//返回所有新闻图片栏目
		} else {
			User loginUser = (User)session.getAttribute("loginUser");
			model.addAttribute("datas", pictureTopicService.find(loginUser.getId(),cid, con, status));
			SystemContext.removeOrder();
			SystemContext.removeSort();
			model.addAttribute("cs",channelService.listPublishChannel(loginUser.getId(),ChannelType.TOPIC_IMG.ordinal()));//返回该用户可以操作的新闻图片栏目
		}
		if(con==null) con="";
		model.addAttribute("con",con);
		model.addAttribute("cid",cid);
		model.addAttribute("status",status);
	}
	
	/**
	 * 显示已经发布的新闻图片
	 * @param con 新闻图片标题
	 * @param cid 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/audits")
	public String auditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,1);
		return "picTopic/list";
	}
	/**
	 * 显示没有发布的新闻图片
	 * @param con 新闻图片标题
	 * @param cid
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/unaudits")
	public String unauditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,0);
		return "picTopic/list";
	}
	/**
	 * 处理取消发布或发布
	 * @param id 文章id
	 * @param con 文章标题关键字
	 * @param cid 栏目id
	 * @param status 文章状态
	 * @return
	 */
	@RequestMapping("/changeStatus/{id}")
	public String changeStatus(@PathVariable int id,@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Integer status,Model model,HttpSession session) {
		User loginUser = (User)session.getAttribute("loginUser");
		pictureTopicService.updateStatus(id,loginUser);
		PictureTopic t = pictureTopicService.load(id);
		if(status==0) {
			model.addAttribute("success", "新闻图片发布成功!");
			return unauditList(con,cid,model,session);
		} else {
			model.addAttribute("success", "新闻图片已取消发布!");
			return auditList(con,cid,model,session);
		}
	}
	/**
	 * 删除新闻图片
	 * @param id 文章id
	 * @param con 文章标题关键字
	 * @param cid 栏目id
	 * @param status 文章状态
	 * @param model 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}" ,method = RequestMethod.POST)
	public String delete(@PathVariable int id,@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Integer status,Model model,HttpSession session) {
		PictureTopic t = pictureTopicService.load(id);
		pictureTopicService.delete(id);
		model.addAttribute("success","新闻图片删除成功!");
		if(status==0) {
			return unauditList(con, cid, model, session);
		} else {
			return auditList(con, cid, model, session);
		}
	}
	
}
