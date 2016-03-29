package org.wxh.topic.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wxh.basic.common.Constant;
import org.wxh.basic.model.SystemContext;
import org.wxh.index.service.IIndexService;
import org.wxh.topic.model.Attachment;
import org.wxh.topic.model.ChannelTree;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.Topic;
import org.wxh.topic.model.dto.AjaxObj;
import org.wxh.topic.model.dto.AttachmentDto;
import org.wxh.topic.model.dto.TopicDto;
import org.wxh.topic.service.IAttachmentService;
import org.wxh.topic.service.IChannelService;
import org.wxh.topic.service.IKeywordService;
import org.wxh.topic.service.ITopicService;
import org.wxh.user.auth.AuthClass;
import org.wxh.user.auth.AuthMethod;
import org.wxh.user.model.User;
import org.wxh.user.service.IGroupService;
import org.wxh.util.JsonUtil;

/**
 * 文章
 * @author wxh
 *
 */

@Controller
@AuthClass("login")
@RequestMapping("/admin/topic")
public class TopicController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
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
		if ( status == Constant.YES ) { //如果是获取已发布文章的列表，则按发布时间排序
			SystemContext.setSort("t.publishDate"); 
		} else { //如果是获取未发布文章的列表，则按创建时间排序
			SystemContext.setSort("t.createDate"); 
		}
		SystemContext.setOrder("desc");
		boolean isAdmin = (Boolean)session.getAttribute(Constant.AuthConstant.IS_ADMIN);
		if ( isAdmin ) { //如果是超级管理员，则返回所有的文章
			model.addAttribute("datas",topicService.find(cid, con, status));
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.TOPIC_LIST.ordinal()));//返回所有文章栏目
		} else {	//如果不是超级管理员，则返回该用户能管理的所有文章
			User loginUser = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
			model.addAttribute("datas", topicService.find(loginUser.getId(),cid, con, status));
			model.addAttribute("cs",channelService.listPublishChannel(loginUser.getId(),ChannelType.TOPIC_LIST.ordinal()));//返回该用户可以操作的文章栏目
		}
		if ( con == null ) con = "";
		model.addAttribute("con",con);
		model.addAttribute("cid",cid);
		model.addAttribute("status",status);
	}
	/**
	 * 显示已经发布的文章
	 * @param con 文章标题关键字
	 * @param cid 文章栏目
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/audits")
	@AuthMethod( role = { Constant.AuthConstant.ROLE_PUBLISH, Constant.AuthConstant.ROLE_AUDIT } )
	public String auditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		//清空session残留
		if(session.getAttribute("messageByTopic") != null){
			session.removeAttribute("messageByTopic");
			logger.info("文章残留的session已清除干净");
		}
		initList(con, cid, model, session,Constant.YES);
		return "topic/list";
	}
	/**
	 * 显示没有发布的文章
	 * @param con 文章标题关键字
	 * @param cid 文章栏目
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/unaudits")
	@AuthMethod( role = { Constant.AuthConstant.ROLE_PUBLISH, Constant.AuthConstant.ROLE_AUDIT } )
	public String unauditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,Constant.NO);
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
	@AuthMethod( role = Constant.AuthConstant.ROLE_AUDIT )
	public String changeStatus(@PathVariable int id,@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Integer status,Model model,HttpSession session) {
		User loginUser = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
		topicService.updateStatus(id,loginUser);
		Topic t = topicService.load(id);
		if(topicService.isUpdateIndex(t.getChannel().getId())) {
			indexService.generateBody();//重新生成首页
		}
		if(status==Constant.NO) {
			model.addAttribute(Constant.BaseCode.SUCCESS, "文章发布成功!");
			return unauditList(con,cid,model,session);
		} else {
			model.addAttribute(Constant.BaseCode.SUCCESS, "文章已取消发布!");
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
	@AuthMethod(role = { Constant.AuthConstant.ROLE_PUBLISH, Constant.AuthConstant.ROLE_AUDIT })
	public String delete(@PathVariable int id,@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Integer status,Model model,HttpSession session) {
		Topic t = topicService.load(id);
		topicService.delete(id);
		model.addAttribute(Constant.BaseCode.SUCCESS,"文章删除成功!");
		if(topicService.isUpdateIndex(t.getChannel().getId())) {
			indexService.generateBody();//重新生成首页
		}
		if(status==Constant.NO) {
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
	@AuthMethod(role = Constant.AuthConstant.ROLE_PUBLISH)
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
	@AuthMethod(role = Constant.AuthConstant.ROLE_PUBLISH)
	public String add(@Validated TopicDto topicDto,BindingResult br,String[]aks,Integer[] aids,HttpSession session) {
		if(br.hasErrors()) {
			return "topic/add";
		}
		User loginUser = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
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
		if(topicDto.getStatus()==Constant.YES&&topicService.isUpdateIndex(topicDto.getCid())) {
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
	@AuthMethod(role = Constant.AuthConstant.ROLE_PUBLISH)
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
	@AuthMethod(role = Constant.AuthConstant.ROLE_PUBLISH)
	public String update(@PathVariable int id,@Validated TopicDto topicDto,BindingResult br,String[]aks,Integer[] aids,HttpSession session) {
		if(br.hasErrors()) {
			return "topic/update";
		}
		Topic t = topicDto.getTopicByUpdate(topicService.load(id),(User)session.getAttribute(Constant.BaseCode.LOGIN_USER));
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
	@AuthMethod( role = { Constant.AuthConstant.ROLE_AUDIT, Constant.AuthConstant.ROLE_PUBLISH } ) 
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
	@AuthMethod( role=Constant.AuthConstant.ROLE_PUBLISH )
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
	@AuthMethod( role = Constant.AuthConstant.ROLE_PUBLISH )
	public void upload(MultipartFile attach,HttpServletResponse resp) throws IOException {
		resp.setContentType( Constant.CONTENT_TYPE );
		AjaxObj ao = new AjaxObj();
		try {
			Attachment att = new Attachment();
			String ext = FilenameUtils.getExtension(attach.getOriginalFilename());
			att.setIsAttach( Constant.NO );
			att.setIsIndexPic( Constant.NO );
			att.setNewName(String.valueOf(new Date().getTime())+"."+ext);
			att.setOldName(FilenameUtils.getBaseName(attach.getOriginalFilename()));
			att.setSuffix(ext);
			att.setType(attach.getContentType());
			att.setTopic(null);
			att.setSize(attach.getSize());
			if(imgTypes.contains(ext)) {
				att.setIsImg( Constant.YES );
			} else { 
				att.setIsImg( Constant.NO );
			}
			attachmentService.add(att, attach.getInputStream());
			ao.setResult(Constant.YES);
			if(imgTypes.contains(ext)) {
				//获取上传图片的宽度，高度
				BufferedImage bi = ImageIO.read(attach.getInputStream());
				int nw = bi.getWidth();
				int nh = bi.getHeight();
				AttachmentDto dto = new AttachmentDto(att,nw,nh);
				ao.setObj(dto);
			} else {
				ao.setObj(att);
			}
		} catch (IOException e) {
			ao.setResult(Constant.NO);
			ao.setMsg(e.getMessage());
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
		response.setContentType(Constant.CONTENT_TYPE);
		AjaxObj ao = null;
		List<Topic> list = null;
		try {
			 boolean isAdmin = (Boolean)session.getAttribute(Constant.BaseCode.ISADMIN);
			 if(isAdmin) { //如果是超级管理员，则返回所有符合检索条件的文章
				list = topicService.list(cid, con, status); 
			 } else {	//如果不是超级管理员，则返回该用户的文章
				User loginUser = (User)session.getAttribute(Constant.BaseCode.LOGINUSER);
				list = topicService.list(loginUser.getId(),cid, con, status); 
			 }
			ao = new AjaxObj(Constant.YES,null,list);
		} catch (Exception e) {
			ao = new AjaxObj(Constant.NO,e.getMessage());
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
	@AuthMethod( role = Constant.AuthConstant.ROLE_PUBLISH )
	public @ResponseBody List<ChannelTree> tree(HttpSession session) {
		boolean isAdmin = (Boolean)session.getAttribute(Constant.AuthConstant.IS_ADMIN);
		User loginUser = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
		if(isAdmin)
			return channelService.generateTree();
		else
			return groupService.generateUserChannelTree(loginUser.getId());
	}	
}
