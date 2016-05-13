package org.wxh.topic.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.wxh.basic.common.Constant;
import org.wxh.basic.model.AjaxObj;
import org.wxh.basic.model.SystemContext;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.Picture;
import org.wxh.topic.model.PictureTopic;
import org.wxh.topic.model.dto.PictureTopicDto;
import org.wxh.topic.service.IChannelService;
import org.wxh.topic.service.IPictureService;
import org.wxh.topic.service.IPictureTopicService;
import org.wxh.user.auth.AuthClass;
import org.wxh.user.auth.AuthMethod;
import org.wxh.user.model.User;
import org.wxh.util.JsonUtil;

/**
 * 组图新闻控制层
 * @author wxh
 *
 */
@Controller
@RequestMapping("/admin/picTopic")
@AuthClass("login")
public class PictureTopicController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPictureTopicService pictureTopicService;
	@Autowired
	private IPictureService pictureService;
	@Autowired
	private IChannelService channelService;
	
	private void initList(String con,Integer cid,Model model,HttpSession session,Integer status) {
		if ( status == Constant.YES ) { //如果是获取已发布文章的列表，则按发布时间排序
			SystemContext.setSort("t.publishDate"); 
		} else { //如果是获取未发布文章的列表，则按创建时间排序
			SystemContext.setSort("t.createDate"); 
		}
		SystemContext.setOrder("desc");
		boolean isAdmin = (Boolean)session.getAttribute(Constant.AuthConstant.IS_ADMIN);
		if ( isAdmin ) { //如果是超级管理员，则返回所有的文章
			model.addAttribute("datas",pictureTopicService.find(cid, con, status));
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.IMG_NEW.ordinal()));//返回所有新闻图片栏目
		} else {  //如果不是超级管理员，则返回该用户能管理的所有文章
			User loginUser = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
			model.addAttribute("datas", pictureTopicService.find(loginUser.getId(),cid, con, status));
			model.addAttribute("cs",channelService.listPublishChannel(loginUser.getId(),ChannelType.IMG_NEW.ordinal()));//返回该用户可以操作的新闻图片栏目
		}
		if ( con == null ) con = "";
		model.addAttribute("con",con);
		model.addAttribute("cid",cid);
		model.addAttribute("status",status);
	}
	
	/**
	 * 显示已经发布的新闻图片
	 * @param con 新闻图片标题关键字
	 * @param cid 新闻图片栏目
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/audits")
	@AuthMethod( role = { Constant.AuthConstant.ROLE_PUBLISH, Constant.AuthConstant.ROLE_AUDIT } )
	public String auditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,Constant.YES);
		return "picTopic/list";
	}
	/**
	 * 显示没有发布的新闻图片
	 * @param con 新闻图片标题关键字
	 * @param cid 新闻图片栏目
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/unaudits")
	@AuthMethod( role = { Constant.AuthConstant.ROLE_PUBLISH, Constant.AuthConstant.ROLE_AUDIT } )
	public String unauditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,Constant.NO);
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
	@AuthMethod( role = Constant.AuthConstant.ROLE_AUDIT )
	public String changeStatus(@PathVariable int id,@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Integer status,Model model,HttpSession session) {
		User loginUser = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
		pictureTopicService.updateStatus(id,loginUser);
		PictureTopic t = pictureTopicService.load(id);
		if(status==Constant.NO) {
			model.addAttribute(Constant.BaseCode.SUCCESS, "发布成功!");
			return unauditList(con,cid,model,session);
		} else {
			model.addAttribute(Constant.BaseCode.SUCCESS, "已取消发布!");
			return auditList(con,cid,model,session);
		}
	}
	/**
	 * 添加组图新闻的界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addUI", method = RequestMethod.POST)
	@AuthMethod(role = Constant.AuthConstant.ROLE_PUBLISH)
	public String add(Model model,HttpSession session) {
		PictureTopic t = new PictureTopic();
		PictureTopicDto td = new PictureTopicDto(t);
		model.addAttribute("pictureTopicDto",td);
		boolean isAdmin = (Boolean)session.getAttribute(Constant.AuthConstant.IS_ADMIN);
		if(isAdmin) { //如果是超级管理员
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.IMG_NEW.ordinal()));//返回所有新闻图片栏目
		} else {
			User loginUser = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
			model.addAttribute("cs",channelService.listPublishChannel(loginUser.getId(),ChannelType.IMG_NEW.ordinal()));//返回该用户可以操作的新闻图片栏目
		}
		return "picTopic/add";
	}
	/**
	 * 添加组图新闻
	 * @param pictureTopicDto
	 * @param br
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@AuthMethod(role = Constant.AuthConstant.ROLE_PUBLISH)
	public String add(PictureTopicDto pictureTopicDto,BindingResult br,HttpSession session,Model model) {
		if(pictureTopicDto.getPics() == null) { //如果没有上传图片
			model.addAttribute(Constant.BaseCode.ERROR, "请选择图片！");
			return add(model,session);
		}
		User loginUser = (User) session.getAttribute(Constant.BaseCode.LOGIN_USER);
		PictureTopic pt = pictureTopicDto.getPictureTopic(loginUser);

		if(pictureTopicDto.getPicNameOlds().length != pictureTopicDto.getPics().length) {
			model.addAttribute(Constant.BaseCode.ERROR, "图片名称不能为空！");
			return add(model,session);
		} else {
			pictureService.updateNameAndSort(pictureTopicDto.getPicNameOlds(),pictureTopicDto.getPics());
		}
		pictureTopicService.add(pt,pictureTopicDto.getCid(),loginUser,pictureTopicDto.getPics());
		/*if(topicDto.getStatus()==Constant.YES&&topicService.isUpdateIndex(topicDto.getCid())) {
			indexService.generateBody();//重新生成首页
		}*/
		//return "redirect:/jsp/common/addSucByPicTopic.jsp";
		model.addAttribute(Constant.BaseCode.SUCCESS, "添加组图成功!");
		return auditList(null,null,model,session);
	}
	
	/**
	 * 删除组图新闻
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
		PictureTopic t = pictureTopicService.load(id);
		pictureTopicService.delete(id);
		model.addAttribute(Constant.BaseCode.SUCCESS,"组图新闻删除成功!");
		if(status==Constant.NO) {
			return unauditList(con, cid, model, session);
		} else {
			return auditList(con, cid, model, session);
		}
	}
	/**
	 * 修改组图新闻的页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateUI/{id}",method=RequestMethod.POST)
	@AuthMethod(role = Constant.AuthConstant.ROLE_PUBLISH)
	public String update(@PathVariable int id,Model model,HttpSession session) {
		PictureTopic t = pictureTopicService.load(id);
		model.addAttribute("pics",pictureService.listByPicTopic(id));
		PictureTopicDto ptd = new PictureTopicDto(t,t.getChannel().getId());
		model.addAttribute("pictureTopicDto",ptd);
		//返回相应的栏目
		boolean isAdmin = (Boolean)session.getAttribute(Constant.AuthConstant.IS_ADMIN);
		if(isAdmin) { //如果是超级管理员，则返回所有的文章
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.IMG_NEW.ordinal()));//返回所有新闻图片栏目
		} else {
			User loginUser = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
			model.addAttribute("cs",channelService.listPublishChannel(loginUser.getId(),ChannelType.IMG_NEW.ordinal()));//返回该用户可以操作的新闻图片栏目
		}
		return "picTopic/update";
	}
	/**
	 * 修改组图新闻信息
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
	@AuthMethod(role = Constant.AuthConstant.ROLE_PUBLISH)
	public String update(@PathVariable int id,PictureTopicDto pictureTopicDto,BindingResult br,HttpSession session,Model model) {
		if(pictureTopicDto.getPics() == null) { //如果没有上传图片
			model.addAttribute(Constant.BaseCode.ERROR, "请选择图片！");
			return update(id,model,session);
		}
		//修改图片信息
		if(pictureTopicDto.getPicNameOlds().length != pictureTopicDto.getPics().length) {
			model.addAttribute(Constant.BaseCode.ERROR, "图片名称不能为空！");
			return add(model,session);
		} else {
			pictureService.updateNameAndSort(pictureTopicDto.getPicNameOlds(),pictureTopicDto.getPics());
		}
		
		PictureTopic t = pictureTopicDto.getPicTopicByUpdate(pictureTopicService.load(id),(User)session.getAttribute(Constant.BaseCode.LOGIN_USER));

		pictureTopicService.update(t, pictureTopicDto.getCid(),pictureTopicDto.getPics());
		/*indexService.generateBody();*/ //重新生成首页
		//return "redirect:/jsp/common/updateSucByPicTopic.jsp";
		model.addAttribute(Constant.BaseCode.SUCCESS, "修改组图成功!");
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
	@AuthMethod(role = Constant.AuthConstant.ROLE_PUBLISH)
	public void upload(MultipartFile attach,HttpServletResponse resp) throws IOException {
		resp.setContentType(Constant.CONTENT_TYPE);
		AjaxObj ao = null;
		try {
			Picture pic = new Picture();
			String ext = FilenameUtils.getExtension(attach.getOriginalFilename());//获取文件后缀
			pic.setPicName((String.valueOf(new Date().getTime())+"."+ext));
			pic.setPicNameOld((FilenameUtils.getBaseName(attach.getOriginalFilename())));
			pic.setSuffix(ext);
			pic.setPictureTopic(null);
			pic.setSize(attach.getSize());
			pictureService.add(pic, attach.getInputStream());
			ao = new AjaxObj(Constant.YES,null,pic);
		} catch (IOException e) {
			ao = new AjaxObj(Constant.NO,e.getMessage());
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
		boolean isAdmin = (Boolean)session.getAttribute(Constant.BaseCode.ISADMIN);
		User loginUser = (User)session.getAttribute(Constant.BaseCode.LOGINUSER);
		if(isAdmin)
			return channelService.generateTree(ChannelType.TOPIC_IMG.ordinal());
		else
			return groupService.generateUserChannelTree(loginUser.getId(),ChannelType.TOPIC_IMG.ordinal());
	}	*/
	
}
