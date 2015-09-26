package org.wxh.topic.controller;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wxh.basic.model.SystemContext;
import org.wxh.util.JsonUtil;
import org.wxh.topic.model.Attachment;
import org.wxh.topic.model.ChannelTree;
import org.wxh.topic.model.Topic;
import org.wxh.topic.model.dto.AjaxObj;
import org.wxh.topic.model.dto.TopicDto;
import org.wxh.topic.service.IAttachmentService;
import org.wxh.topic.service.IChannelService;
import org.wxh.topic.service.IKeywordService;
import org.wxh.topic.service.ITopicService;
import org.wxh.user.auth.AuthClass;
import org.wxh.user.auth.AuthMethod;
import org.wxh.user.model.User;
import org.wxh.user.service.IGroupService;

/**
 * 文章
 * @author wxh
 *
 */

@Controller
@AuthClass("login")
@RequestMapping("/admin/topic")
public class TopicController {
	
	private static final Logger logger = Logger.getLogger(TopicController.class);
	
	@Autowired
	private ITopicService topicService;
	@Autowired
	private IChannelService channelService;
	@Autowired
	private IKeywordService keywordService;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private IGroupService groupService;
	
	private final static List<String> imgTypes = Arrays.asList("jpg","jpeg","gif","png");
	
	public ITopicService getTopicService() {
		return topicService;
	}
	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}
	public IChannelService getChannelService() {
		return channelService;
	}
	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}
	public IKeywordService getKeywordService() {
		return keywordService;
	}
	public void setKeywordService(IKeywordService keywordService) {
		this.keywordService = keywordService;
	}
	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}
	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	public IGroupService getGroupService() {
		return groupService;
	}
	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}
	
	private void initList(String con,Integer cid,Model model,HttpSession session,Integer status) {
		SystemContext.setSort("t.publishDate");
		SystemContext.setOrder("desc");
		boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		if(isAdmin) { //如果是超级管理员，则返回所有的文章
			model.addAttribute("datas",topicService.find(cid, con, status));
		} else {	//如果不是超级管理员，则返回该用户所有的文章
			User loginUser = (User)session.getAttribute("loginUser");
			model.addAttribute("datas", topicService.find(loginUser.getId(),cid, con, status));
		}
		if(con==null) con="";
		SystemContext.removeOrder();
		SystemContext.removeSort();
		model.addAttribute("con",con);
		model.addAttribute("cid",cid);
		model.addAttribute("status",status);
		model.addAttribute("cs",channelService.listPublishChannel());
	}
	/**
	 * 显示已经发布的文章
	 * @param con
	 * @param cid
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/audits")
	@AuthMethod(role="ROLE_PUBLISH,ROLE_AUDIT")
	public String auditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		//清空session残留
		if(session.getAttribute("message") != null){
			session.removeAttribute("message");
			logger.info("文章残留的session已清除干净");
		}
		initList(con, cid, model, session,1);
		return "topic/list";
	}
	/**
	 * 显示没有发布的文章
	 * @param con
	 * @param cid
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/unaudits")
	@AuthMethod(role="ROLE_PUBLISH,ROLE_AUDIT")
	public String unauditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,0);
		return "topic/list";
	}
	/**
	 * 跳转到首页，再从首页跳转到文章列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/returnAuditList")
	public String returnAuditList(HttpSession session){
		session.setAttribute("message","1");
		return "redirect:/admin";
	}
	
	/**
	 * 处理取消发布或发布
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/changeStatus/{id}")
	@AuthMethod(role="ROLE_AUDIT")
	public String changeStatus(@PathVariable int id,Integer status,Model model,HttpSession session) {
		topicService.updateStatus(id);
		Topic t = topicService.load(id);
		if(topicService.isUpdateIndex(t.getChannel().getId())) {
			//indexService.generateBody();
		}
		if(status==0) {
			model.addAttribute("success", "文章发布成功!");
			return unauditList(null,null,model,session);
		} else {
			model.addAttribute("success", "文章已取消发布!");
			return auditList(null,null,model,session);
		}
	}
	/**
	 * 删除文章
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}" ,method = RequestMethod.POST)
	@AuthMethod(role="ROLE_PUBLISH")
	public String delete(@PathVariable int id,Integer status,Model model,HttpSession session) {
		Topic t = topicService.load(id);
		topicService.delete(id);
		model.addAttribute("success","文章删除成功!");
		if(topicService.isUpdateIndex(t.getChannel().getId())) {
			//indexService.generateBody();
		}
		if(status==0) {
			return unauditList(null, null, model, session);
		} else {
			return auditList(null, null, model, session);
		}
	}
	/**
	 * 添加文章的界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	@AuthMethod(role="ROLE_PUBLISH")
	public String add(Model model) {
		Topic t = new Topic();
		t.setPublishDate(new Date());
		TopicDto td = new TopicDto(t);
		model.addAttribute("topicDto",td);
		return "topic/add";
	}
	/**
	 * 添加文章
	 * @param topicDto
	 * @param br
	 * @param aks 关键字
	 * @param aids 附件id
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated TopicDto topicDto,BindingResult br,String[]aks,Integer[] aids,HttpSession session) {
		if(br.hasErrors()) {
			return "topic/add";
		}
		Topic t = topicDto.getTopic();
		User loginUser = (User)session.getAttribute("loginUser");
		StringBuffer keys = new StringBuffer();
		if(aks!=null) {
			for(String k:aks) {
				keys.append(k).append("|");
				keywordService.addOrUpdate(k);
			}
		}
		t.setKeyword(keys.toString());
		topicService.add(t, topicDto.getCid(), loginUser.getId(),aids);
		if(topicDto.getStatus()==1&&topicService.isUpdateIndex(topicDto.getCid())) {
			//indexService.generateBody();
		}
		return "redirect:/jsp/common/addSuc.jsp";
	}
	/**
	 * 修改文章信息的页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	@AuthMethod(role="ROLE_PUBLISH")
	public String update(@PathVariable int id,Model model) {
		Topic t = topicService.load(id);
		String keyword = t.getKeyword();
		if(keyword!=null&&!"".equals(keyword.trim()))
			model.addAttribute("keywords",keyword.split("\\|"));
		model.addAttribute("atts",attachmentService.listByTopic(id));
		TopicDto td = new TopicDto(t,t.getChannel().getId());
		model.addAttribute("topicDto",td);
		model.addAttribute("cname",t.getChannel().getName());
		return "topic/update";
	}
	/**
	 *  修改文章信息
	 * @param id
	 * @param topicDto
	 * @param br
	 * @param aks
	 * @param aids
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Validated TopicDto topicDto,BindingResult br,String[]aks,Integer[] aids,HttpSession session) {
		if(br.hasErrors()) {
			return "topic/update";
		}
		Topic tt = topicService.load(id);
		Topic t = topicDto.getTopic();
		StringBuffer keys = new StringBuffer();
		if(aks!=null) {
			for(String k:aks) {
				keys.append(k).append("|");
				keywordService.addOrUpdate(k);
			}
		}
		tt.setKeyword(keys.toString());
		tt.setChannelPicId(t.getChannelPicId());
		tt.setContent(t.getContent());
		tt.setPublishDate(t.getPublishDate());
		tt.setRecommend(t.getRecommend());
		tt.setStatus(t.getStatus());
		tt.setSummary(t.getSummary());
		tt.setTitle(t.getTitle());
		topicService.update(tt, topicDto.getCid(),aids);
		if(topicService.isUpdateIndex(topicDto.getCid())) {
			//indexService.generateBody();
		}
		return "redirect:/jsp/common/addSuc.jsp";
	}
	/**
	 * 显示某个文章信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/{id}")
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(topicService.load(id)); //文章信息
		model.addAttribute("atts",attachmentService.listByTopic(id));//文章附件信息
		return "topic/show";
	}
	/**
	 * 自动补全关键字
	 * @param term
	 * @return
	 */
	@RequestMapping(value="/searchKeyword")
	@AuthMethod(role="ROLE_PUBLISH")
	public @ResponseBody List<String> searchKeyword(String term) {
		return keywordService.listKeywordStringByCon(term);
	}
	/**
	 * 附件上传
	 * @param attach
	 * @param resp
	 * @throws IOException
	 */
	//返回的是json类型的值，而uploadify只能接受字符串。所以不能用@ResponseBody返回json数据
	//这里用HttpServletResponse来返回字符串形式的json对象
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@AuthMethod(role="ROLE_PUBLISH")
	public void upload(MultipartFile attach,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain;charset=utf-8");
		AjaxObj ao = null;
		try {
			Attachment att = new Attachment();
			String ext = FilenameUtils.getExtension(attach.getOriginalFilename());
			logger.info(ext);
			att.setIsAttach(0);
			if(imgTypes.contains(ext)) att.setIsImg(1);
			else att.setIsImg(0);
			att.setIsIndexPic(0);
			att.setNewName(String.valueOf(new Date().getTime())+"."+ext);
			att.setOldName(FilenameUtils.getBaseName(attach.getOriginalFilename()));
			att.setSuffix(ext);
			att.setType(attach.getContentType());
			att.setTopic(null);
			att.setSize(attach.getSize());
			attachmentService.add(att, attach.getInputStream());
			ao = new AjaxObj(1,null,att);
		} catch (IOException e) {
			ao = new AjaxObj(0,e.getMessage());
		}
		resp.getWriter().write(JsonUtil.getInstance().obj2json(ao));
	}
	/**
	 * 返回ztree的json数据
	 * @param session
	 * @return
	 */
	@RequestMapping("/treeAll")
	@AuthMethod(role="ROLE_PUBLISH")
	public @ResponseBody List<ChannelTree> tree(HttpSession session) {
		boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		User loginUser = (User)session.getAttribute("loginUser");
		if(isAdmin)
			return channelService.generateTree();
		else
			return groupService.generateUserChannelTree(loginUser.getId());
	}	
	
}
