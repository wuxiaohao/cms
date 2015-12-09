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
import org.wxh.basic.model.Pager;
import org.wxh.basic.model.SystemContext;
import org.wxh.index.service.IIndexService;
import org.wxh.util.JsonUtil;
import org.wxh.topic.model.Attachment;
import org.wxh.topic.model.ChannelTree;
import org.wxh.topic.model.ChannelType;
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
	@Autowired
	private IIndexService indexService;
	
	private final static List<String> imgTypes = Arrays.asList("jpg","jpeg","gif","png");
	
	private void initList(String con,Integer cid,Model model,HttpSession session,Integer status) {
		if(status == 1){ //如果是获取已发布文章的列表，则按发布时间排序
			SystemContext.setSort("t.publishDate"); 
		} else { //如果是获取未发布文章的列表，则按创建时间排序
			SystemContext.setSort("t.createDate"); 
		}
		SystemContext.setOrder("desc");
		boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		if(isAdmin) { //如果是超级管理员，则返回所有的文章
			model.addAttribute("datas",topicService.find(cid, con, status));
			SystemContext.removeOrder();
			SystemContext.removeSort();
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.TOPIC_LIST.ordinal()));//返回所有文章栏目
		} else {	//如果不是超级管理员，则返回该用户所有的文章
			User loginUser = (User)session.getAttribute("loginUser");
			model.addAttribute("datas", topicService.find(loginUser.getId(),cid, con, status));
			SystemContext.removeOrder();
			SystemContext.removeSort();
			model.addAttribute("cs",channelService.listPublishChannel(loginUser.getId(),ChannelType.TOPIC_LIST.ordinal()));//返回该用户可以操作的文章栏目
		}
		if(con==null) con="";
		model.addAttribute("con",con);
		model.addAttribute("cid",cid);
		model.addAttribute("status",status);
	}
	/**
	 * 显示已经发布的文章
	 * @param con 文章标题
	 * @param cid
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/audits")
	@AuthMethod(role="ROLE_PUBLISH,ROLE_AUDIT")
	public String auditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		//清空session残留
		if(session.getAttribute("messageByTopic") != null){
			session.removeAttribute("messageByTopic");
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
	 * 处理取消发布或发布
	 * @param id 文章id
	 * @param con 文章标题关键字
	 * @param cid 栏目id
	 * @param status 文章状态
	 * @return
	 */
	@RequestMapping("/changeStatus/{id}")
	@AuthMethod(role="ROLE_AUDIT")
	public String changeStatus(@PathVariable int id,@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Integer status,Model model,HttpSession session) {
		User loginUser = (User)session.getAttribute("loginUser");
		topicService.updateStatus(id,loginUser);
		Topic t = topicService.load(id);
		if(topicService.isUpdateIndex(t.getChannel().getId())) {
			indexService.generateBody();//重新生成首页
		}
		if(status==0) {
			model.addAttribute("success", "文章发布成功!");
			return unauditList(con,cid,model,session);
		} else {
			model.addAttribute("success", "文章已取消发布!");
			return auditList(con,cid,model,session);
		}
	}
	/**
	 * 删除文章
	 * @param id 文章id
	 * @param con 文章标题关键字
	 * @param cid 栏目id
	 * @param status 文章状态
	 * @param model 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}" ,method = RequestMethod.POST)
	@AuthMethod(role="ROLE_PUBLISH")
	public String delete(@PathVariable int id,@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Integer status,Model model,HttpSession session) {
		Topic t = topicService.load(id);
		topicService.delete(id);
		model.addAttribute("success","文章删除成功!");
		if(topicService.isUpdateIndex(t.getChannel().getId())) {
			indexService.generateBody();//重新生成首页
		}
		if(status==0) {
			return unauditList(con, cid, model, session);
		} else {
			return auditList(con, cid, model, session);
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
		User loginUser = (User)session.getAttribute("loginUser");
		Topic t = topicDto.getTopic(loginUser);
		StringBuffer keys = new StringBuffer();
		if(aks!=null) {
			for(String k:aks) {
				keys.append(k).append("|");
				keywordService.addOrUpdate(k);
			}
		}
		t.setKeyword(keys.toString());
		topicService.add(t, topicDto.getCid(), loginUser,aids);
		if(topicDto.getStatus()==1&&topicService.isUpdateIndex(topicDto.getCid())) {
			indexService.generateBody();//重新生成首页
		}
		return "redirect:/jsp/common/addSucByTopic.jsp";
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
		Topic t = topicDto.getTopicByUpdate(topicService.load(id),(User)session.getAttribute("loginUser"));
		//设置文章关键字
		StringBuffer keys = new StringBuffer();
		if(aks!=null) {
			for(String k:aks) {
				keys.append(k).append("|");
				keywordService.addOrUpdate(k);
			}
		}
		t.setKeyword(keys.toString());
		topicService.update(t, topicDto.getCid(),aids);
		indexService.generateBody(); //重新生成首页
		return "redirect:/jsp/common/updateSucByTopic.jsp";
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
	 * 根据条件检索文章
	 * @param con 文章标题
	 * @param cid 栏目id
	 * @param status 文章的状态
	 * @throws IOException 
	 */
/*	@RequestMapping(value = "/queryTopic" ,method = RequestMethod.POST)
	@AuthMethod(role="ROLE_PUBLISH,ROLE_AUDIT")
	public String queryTopic(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,@RequestParam Integer status,HttpServletResponse response,Model model,HttpSession session) throws IOException{
		response.setContentType("text/plain;charset=utf-8");
		AjaxObj ao = null;
		List<Topic> list = null;
		try {
			 boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
			 if(isAdmin) { //如果是超级管理员，则返回所有符合检索条件的文章
				list = topicService.list(cid, con, status); 
			 } else {	//如果不是超级管理员，则返回该用户的文章
				User loginUser = (User)session.getAttribute("loginUser");
				list = topicService.list(loginUser.getId(),cid, con, status); 
			 }
			ao = new AjaxObj(1,null,list);
		} catch (Exception e) {
			ao = new AjaxObj(0,e.getMessage());
		}
		response.getWriter().write(JsonUtil.getInstance().obj2json(ao));
		return null;
	}*/
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
