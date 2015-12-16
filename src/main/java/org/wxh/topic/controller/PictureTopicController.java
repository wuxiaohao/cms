package org.wxh.topic.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
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
import org.wxh.topic.model.Attachment;
import org.wxh.topic.model.ChannelTree;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.Picture;
import org.wxh.topic.model.PictureTopic;
import org.wxh.topic.model.Topic;
import org.wxh.topic.model.dto.AjaxObj;
import org.wxh.topic.model.dto.PictureTopicDtoDto;
import org.wxh.topic.model.dto.TopicDto;
import org.wxh.topic.service.IChannelService;
import org.wxh.topic.service.IKeywordService;
import org.wxh.topic.service.IPictureService;
import org.wxh.topic.service.IPictureTopicService;
import org.wxh.user.auth.AuthMethod;
import org.wxh.user.model.User;
import org.wxh.user.service.IGroupService;
import org.wxh.util.JsonUtil;

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
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.IMG_NEW.ordinal()));//返回所有新闻图片栏目
		} else {
			User loginUser = (User)session.getAttribute("loginUser");
			model.addAttribute("datas", pictureTopicService.find(loginUser.getId(),cid, con, status));
			SystemContext.removeOrder();
			SystemContext.removeSort();
			model.addAttribute("cs",channelService.listPublishChannel(loginUser.getId(),ChannelType.IMG_NEW.ordinal()));//返回该用户可以操作的新闻图片栏目
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
			model.addAttribute("success", "发布成功!");
			return unauditList(con,cid,model,session);
		} else {
			model.addAttribute("success", "已取消发布!");
			return auditList(con,cid,model,session);
		}
	}
	/**
	 * 添加新闻图片的界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addUI", method = RequestMethod.POST)
	public String add(Model model,HttpSession session) {
		PictureTopic t = new PictureTopic();
		PictureTopicDtoDto td = new PictureTopicDtoDto(t);
		model.addAttribute("pictureTopicDto",td);
		boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		if(isAdmin) { //如果是超级管理员，则返回所有的文章
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.IMG_NEW.ordinal()));//返回所有新闻图片栏目
		} else {
			User loginUser = (User)session.getAttribute("loginUser");
			model.addAttribute("cs",channelService.listPublishChannel(loginUser.getId(),ChannelType.IMG_NEW.ordinal()));//返回该用户可以操作的新闻图片栏目
		}
		return "picTopic/add";
	}
	/**
	 * 添加文章
	 * @param br
	 * @param aks 关键字
	 * @param aids 附件id
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(PictureTopicDtoDto pictureTopicDto,BindingResult br,String[]aks,HttpSession session,Model model) {
		if(pictureTopicDto.getPics() == null) { //如果没有上传图片
			model.addAttribute("error", "请选择图片！");
			return add(model,session);
		}
		User loginUser = (User) session.getAttribute("loginUser");
		PictureTopic pt = pictureTopicDto.getPictureTopic(loginUser);
		StringBuffer keys = new StringBuffer();
		if(aks != null) {
			for(String k:aks) {
				keys.append(k).append("|");//拼接关键字
				keywordService.addOrUpdate(k);
			}
		}
		pt.setKeyword(keys.toString());
		pictureTopicService.add(pt,pictureTopicDto.getCid(),loginUser,pictureTopicDto.getPics());
		/*if(topicDto.getStatus()==1&&topicService.isUpdateIndex(topicDto.getCid())) {
			indexService.generateBody();//重新生成首页
		}*/
		//return "redirect:/jsp/common/addSucByPicTopic.jsp";
		model.addAttribute("success", "添加组图成功!");
		return auditList(null,null,model,session);
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
		model.addAttribute("success","组图新闻删除成功!");
		if(status==0) {
			return unauditList(con, cid, model, session);
		} else {
			return auditList(con, cid, model, session);
		}
	}
	/**
	 * 修改文章信息的页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateUI/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,Model model,HttpSession session) {
		PictureTopic t = pictureTopicService.load(id);
		String keyword = t.getKeyword();
		if(keyword != null && !"".equals(keyword.trim()))
			model.addAttribute("keywords",keyword.split("\\|"));
		model.addAttribute("pics",pictureService.listByPicTopic(id));
		PictureTopicDtoDto ptd = new PictureTopicDtoDto(t,t.getChannel().getId());
		model.addAttribute("pictureTopicDto",ptd);
		//返回相应的栏目
		boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		if(isAdmin) { //如果是超级管理员，则返回所有的文章
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.IMG_NEW.ordinal()));//返回所有新闻图片栏目
		} else {
			User loginUser = (User)session.getAttribute("loginUser");
			model.addAttribute("cs",channelService.listPublishChannel(loginUser.getId(),ChannelType.IMG_NEW.ordinal()));//返回该用户可以操作的新闻图片栏目
		}
		return "picTopic/update";
	}
	/**
	 *  修改组图新闻信息
	 * @param id
	 * @param topicDto
	 * @param br
	 * @param aks
	 * @param aids
	 * @param oldAks 原来的图片id数组
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,PictureTopicDtoDto pictureTopicDto,BindingResult br,String[]aks,String[] oldAks,HttpSession session,Model model) {
		if(oldAks == null && pictureTopicDto.getPics() == null) { //如果把原来图片删除并且没有上传新的图片
			model.addAttribute("error", "请选择图片！");
			return update(id,model,session);
		}
		PictureTopic t = pictureTopicDto.getPicTopicByUpdate(pictureTopicService.load(id),(User)session.getAttribute("loginUser"));
		//设置文章关键字
		StringBuffer keys = new StringBuffer();
		if(aks!=null) {
			for(String k:aks) {
				keys.append(k).append("|");
				keywordService.addOrUpdate(k);
			}
		}
		t.setKeyword(keys.toString());
		pictureTopicService.update(t, pictureTopicDto.getCid(),pictureTopicDto.getPics());
		/*indexService.generateBody();*/ //重新生成首页
		//return "redirect:/jsp/common/updateSucByPicTopic.jsp";
		model.addAttribute("success", "修改组图成功!");
		return auditList(null, null, model, session);
	}
	/**
	 * 图片上传
	 * @param attach
	 * @param resp
	 * @throws IOException
	 */
	//返回的是json类型的值，而uploadify只能接受字符串。所以不能用@ResponseBody返回json数据
	//这里用HttpServletResponse来返回字符串形式的json对象
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public void upload(MultipartFile attach,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain;charset=utf-8");
		AjaxObj ao = null;
		try {
			Picture pic = new Picture();
			String ext = FilenameUtils.getExtension(attach.getOriginalFilename());//获取文件后缀
			logger.info(ext);
			pic.setPicName((String.valueOf(new Date().getTime())+"."+ext));
			pic.setPicNameOld((FilenameUtils.getBaseName(attach.getOriginalFilename())));
			pic.setSuffix(ext);
			pic.setPictureTopic(null);
			pic.setSize(attach.getSize());
			pictureService.add(pic, attach.getInputStream());
			ao = new AjaxObj(1,null,pic);
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
	/*@RequestMapping("/treeAll")
	public @ResponseBody List<ChannelTree> tree(HttpSession session) {
		boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		User loginUser = (User)session.getAttribute("loginUser");
		if(isAdmin)
			return channelService.generateTree(ChannelType.TOPIC_IMG.ordinal());
		else
			return groupService.generateUserChannelTree(loginUser.getId(),ChannelType.TOPIC_IMG.ordinal());
	}	*/
	
}
