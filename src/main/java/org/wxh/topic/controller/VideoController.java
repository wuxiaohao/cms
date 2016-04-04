package org.wxh.topic.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

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
import org.wxh.basic.common.Constant.UrlConstant;
import org.wxh.basic.model.AjaxObj;
import org.wxh.basic.model.SystemContext;
import org.wxh.index.service.IIndexService;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.Video;
import org.wxh.topic.service.IChannelService;
import org.wxh.topic.service.IVideoService;
import org.wxh.user.auth.AuthClass;
import org.wxh.user.auth.AuthMethod;
import org.wxh.user.model.User;
import org.wxh.util.JsonUtil;

/**
 * 视频
 * @author wxh
 *
 */
@Controller
@RequestMapping("/admin/video")
@AuthClass("login")
public class VideoController {
	
	private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
	
	@Autowired
	private IVideoService videoService;
	@Autowired
	private IChannelService channelService;
	@Autowired
	private IIndexService indexService;
	
	private void initList(String con,Integer cid,Model model,HttpSession session,Integer status) {
		if ( status == Constant.YES ) {  //如果是获取已发布视频新闻的列表，则按发布时间排序
			SystemContext.setSort("v.publishDate"); 
		} else { //如果是获取未发布视频新闻的列表，则按创建时间排序
			SystemContext.setSort("v.createDate"); 
		}
		SystemContext.setOrder("desc");
		boolean isAdmin = (Boolean)session.getAttribute(Constant.AuthConstant.IS_ADMIN);
		if ( isAdmin ) { //如果是超级管理员，则返回所有
			model.addAttribute("datas",videoService.find(cid, con, status));
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.VIDEO_NEW.ordinal()));//返回所有视频新闻栏目
		} else {  //如果不是超级管理员，则返回该用户能管理的所有文章
			User loginUser = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
			model.addAttribute("datas", videoService.find(loginUser.getId(),cid, con, status));
			model.addAttribute("cs",channelService.listPublishChannel(loginUser.getId(),ChannelType.VIDEO_NEW.ordinal()));//返回该用户可以操作的视频新闻栏目
		}
		if ( con == null ) con = "";
		model.addAttribute("con",con);
		model.addAttribute("cid",cid);
		model.addAttribute("status",status);
	}
	/**
	 * 显示已经发布的视频新闻
	 * @param con 视频新闻标题关键字
	 * @param cid 视频新闻栏目
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/audits")
	@AuthMethod( role = { Constant.AuthConstant.ROLE_PUBLISH, Constant.AuthConstant.ROLE_AUDIT } )
	public String auditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,Constant.YES);
		return "video/list";
	}
	/**
	 * 显示没有发布的视频新闻
	 * @param con 视频新闻标题关键字
	 * @param cid 视频新闻栏目
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/unaudits")
	@AuthMethod( role = { Constant.AuthConstant.ROLE_PUBLISH, Constant.AuthConstant.ROLE_AUDIT } )
	public String unauditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,Constant.NO);
		return "video/list";
	}
	/**
	 * 处理取消发布或发布
	 * @param id 视频新闻id
	 * @param con 视频新闻标题关键字
	 * @param cid 栏目id
	 * @param status 视频新闻状态
	 * @return
	 */
	@RequestMapping("/changeStatus/{id}")
	@AuthMethod( role = Constant.AuthConstant.ROLE_AUDIT )
	public String changeStatus(@PathVariable int id,@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Integer status,Model model,HttpSession session) {
		User loginUser = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
		videoService.updateStatus(id,loginUser);
		Video v = videoService.load(id);
		indexService.generateBody();//重新生成首页body
		if(status==Constant.NO) {
			model.addAttribute(Constant.BaseCode.SUCCESS, "发布成功!");
			return unauditList(con,cid,model,session);
		} else {
			model.addAttribute(Constant.BaseCode.SUCCESS, "已取消发布!");
			return auditList(con,cid,model,session);
		}
	}
	/**
	 * 添加视频新闻的界面
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/addUI", method = RequestMethod.POST)
	@AuthMethod(role = Constant.AuthConstant.ROLE_PUBLISH)
	public String add(Model model,HttpSession session) {
		Video v = new Video();
		model.addAttribute("video",v);
		boolean isAdmin = (Boolean)session.getAttribute(Constant.AuthConstant.IS_ADMIN);
		if(isAdmin) { //如果是超级管理员
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.VIDEO_NEW.ordinal()));//返回所有视频新闻栏目
		} else {
			User loginUser = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
			model.addAttribute("cs",channelService.listPublishChannel(loginUser.getId(),ChannelType.VIDEO_NEW.ordinal()));//返回该用户可以操作的视频新闻栏目
		}
		return "video/add";
	}
	/**
	 * 添加视频新闻
	 * @param video 视频新闻实体
	 * @param cid 栏目id
	 * @param br
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method = RequestMethod.POST)
	@AuthMethod(role = Constant.AuthConstant.ROLE_PUBLISH)
	public String add(Video video,int cid,BindingResult br,HttpSession session,Model model) {
		User loginUser = (User) session.getAttribute(Constant.BaseCode.LOGIN_USER);
		if(video.getStatus() == Constant.YES) { //如果视频要立刻发布
			video.setPublishDate(new Date()); //设置发布时间
			video.setAuditor(loginUser.getNickname()); //设置发布人
		}
		videoService.add(video,cid,loginUser);
		indexService.generateBody();//重新生成首页body
		model.addAttribute(Constant.BaseCode.SUCCESS, "添加视频新闻成功!");
		return auditList(null,null,model,session);
	}
	/**
	 * 删除视频新闻
	 * @param id 视频新闻id
 	 * @param con 视频新闻标题关键字
	 * @param cid 栏目id
	 * @param status 文章状态
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}" ,method = RequestMethod.POST)
	@AuthMethod(role = { Constant.AuthConstant.ROLE_PUBLISH, Constant.AuthConstant.ROLE_AUDIT })
	public String delete(@PathVariable int id,@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Integer status,Model model,HttpSession session) {
		Video v = videoService.load(id);
		videoService.delete(id);
		model.addAttribute(Constant.BaseCode.SUCCESS,"视频新闻删除成功!");
		indexService.generateBody();//重新生成首页body
		if(status==Constant.NO) {
			return unauditList(con, cid, model, session);
		} else {
			return auditList(con, cid, model, session);
		}
	}
	/**
	 * 修改视频新闻的页面
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updateUI/{id}",method=RequestMethod.POST)
	@AuthMethod(role = Constant.AuthConstant.ROLE_PUBLISH)
	public String update(@PathVariable int id,Model model,HttpSession session) {
		Video v = videoService.load(id);
		model.addAttribute("video",v);
		//返回相应的栏目
		boolean isAdmin = (Boolean)session.getAttribute(Constant.AuthConstant.IS_ADMIN);
		if(isAdmin) { //如果是超级管理员
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.VIDEO_NEW.ordinal()));//返回所有视频新闻栏目
		} else {
			User loginUser = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
			model.addAttribute("cs",channelService.listPublishChannel(loginUser.getId(),ChannelType.VIDEO_NEW.ordinal()));//返回该用户可以操作的视频新闻栏目
		}
		return "video/update";
	}
	/**
	 * 修改视频新闻信息
	 * @param id
	 * @param video
	 * @param br
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	@AuthMethod(role = Constant.AuthConstant.ROLE_PUBLISH)
	public String update(@PathVariable int id,Video video,int cid,HttpSession session,Model model) {
		Video vOld = videoService.load(id);
		video.getVideo(vOld,(User)session.getAttribute(Constant.BaseCode.LOGIN_USER));
		videoService.update(video,vOld,cid);
		model.addAttribute(Constant.BaseCode.SUCCESS, "修改视频新闻成功!");
		indexService.generateBody();//重新生成首页body
		return auditList(null, null, model, session);
	}
	/**
	 * 视频上传
	 * @param attach
	 * @param resp
	 * @throws IOException
	 */
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@AuthMethod(role = Constant.AuthConstant.ROLE_PUBLISH)
	public void upload(MultipartFile attach,HttpServletResponse resp) throws IOException {
		AjaxObj ao = null;
		try {
			resp.setContentType(Constant.CONTENT_TYPE);
			Video v = new Video();
			String ext = FilenameUtils.getExtension(attach.getOriginalFilename());//获取文件后缀
			logger.info(ext);
			v.setVideoName(String.valueOf(new Date().getTime()+"."+ext));
			v.setSize(attach.getSize());
			videoService.addVideo(v,attach.getInputStream());
			String realPath = SystemContext.getRealPath();
			String path = realPath+Constant.UrlConstant.UPLOAD_VIDEO + v.getVideoName();
			//截图视频图片
			String picName = processImg(path);
			v.setPicName(picName);
			ao = new AjaxObj(Constant.YES,null,v);
		} catch (IOException e) {
			ao = new AjaxObj(Constant.NO,e.getMessage());
		}
		resp.getWriter().write(JsonUtil.getInstance().obj2json(ao));
	}
	/**
	 * 缩略图上传
	 * @param attach
	 * @param resp
	 * @throws IOException
	 */
	/*@RequestMapping(value="/uploadPic",method=RequestMethod.POST)
	public void uploadPic(MultipartFile attachPic,HttpServletResponse resp) throws IOException {
		AjaxObj ao = null;
		try {
			resp.setContentType(Constant.CONTENT_TYPE);
			String ext = FilenameUtils.getExtension(attachPic.getOriginalFilename());//获取文件后缀
			logger.info(ext);
			String picName =String.valueOf(new Date().getTime()+"."+ext);
			videoService.addPic(picName,attachPic.getInputStream());
			ao = new AjaxObj(Constant.YES,null,picName);
		} catch (IOException e) {
			ao = new AjaxObj(Constant.NO,e.getMessage());
		}
		resp.getWriter().write(JsonUtil.getInstance().obj2json(ao));
	}*/
	
	/**
	 * 截图视频图片
	 * @param veido_path 视频路径
	 * @return
	 */
	private static String processImg(String veido_path) {
		File file = new File(veido_path);
		Properties prop = new Properties();
		try {
			prop.load(VideoController.class.getClassLoader().getResourceAsStream("baseinfo.properties"));
			if (!file.exists()) {
				System.err.println("路径[" + veido_path + "]对应的视频文件不存在!");
				return null;
			}
			String realPath = SystemContext.getRealPath();
			String ffmpeg_path = prop.getProperty("ffmpeg_path");//注意这里，一定要有这个插件啊
			String PicPath = realPath+UrlConstant.UPLOAD_VIDEO_THUM;//视频截图存放的位置
			File fp = new File(PicPath);
			if( !fp.exists() ) fp.mkdirs();//如果目录不存在则创建目录
			String picName = String.valueOf(new Date().getTime() + ".jpg" );
			List<String> commands = new java.util.ArrayList<String>();
			commands.add(ffmpeg_path);
			commands.add("-i");
			commands.add(veido_path);
			commands.add("-y");
			commands.add("-f");
			commands.add("image2");
			commands.add("-ss");
			commands.add("8");// 这个参数是设置截取视频多少秒时的画面
			// commands.add("-t");
			// commands.add("0.001");
			commands.add("-s");
			commands.add("499x431");//设置截取的图片宽高
			commands.add( PicPath + picName );
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commands);
			builder.start();
			logger.info("视频截取成功！");
			return picName;
		} catch (IOException e1) {
			logger.error("视频截取异常,异常信息：[{}]",e1.getMessage());
			e1.printStackTrace();
		}
		return null;
	}
}
