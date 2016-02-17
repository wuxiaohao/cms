package org.wxh.topic.controller;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.wxh.basic.common.GlobalResult;
import org.wxh.basic.model.SystemContext;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.Video;
import org.wxh.topic.model.dto.AjaxObj;
import org.wxh.topic.service.IChannelService;
import org.wxh.topic.service.IVideoService;
import org.wxh.user.model.User;
import org.wxh.util.JsonUtil;

import com.fasterxml.jackson.annotation.JsonFormat.Value;

/**
 * 视频
 * @author wxh
 *
 */
@Controller
@RequestMapping("/admin/video")
public class VideoController {
	private static final Logger logger = Logger.getLogger(VideoController.class);
	
	@Autowired
	private IVideoService videoService;
	@Autowired
	private IChannelService channelService;
	
	private void initList(String con,Integer cid,Model model,HttpSession session,Integer status) {
		if(status == 1) {  //如果是获取已发布视频新闻的列表，则按发布时间排序
			SystemContext.setSort("v.publishDate"); 
		} else { //如果是获取未发布视频新闻的列表，则按创建时间排序
			SystemContext.setSort("v.createDate"); 
		}
		SystemContext.setOrder("desc");
		boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		if(isAdmin) { //如果是超级管理员，则返回所有
			model.addAttribute("datas",videoService.find(cid, con, status));
			SystemContext.removeOrder();
			SystemContext.removeSort();
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.VIDEO_NEW.ordinal()));//返回所有视频新闻栏目
		} else {
			User loginUser = (User)session.getAttribute("loginUser");
			model.addAttribute("datas", videoService.find(loginUser.getId(),cid, con, status));
			SystemContext.removeOrder();
			SystemContext.removeSort();
			model.addAttribute("cs",channelService.listPublishChannel(loginUser.getId(),ChannelType.VIDEO_NEW.ordinal()));//返回该用户可以操作的视频新闻栏目
		}
		if(con==null) con="";
		model.addAttribute("con",con);
		model.addAttribute("cid",cid);
		model.addAttribute("status",status);
	}
	/**
	 * 显示已经发布的视频新闻
	 * @param con 视频新闻标题
	 * @param cid
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/audits")
	public String auditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,1);
		return "video/list";
	}
	/**
	 * 显示没有发布的视频新闻
	 * @param con 视频新闻标题
	 * @param cid
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/unaudits")
	public String unauditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,0);
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
	public String changeStatus(@PathVariable int id,@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Integer status,Model model,HttpSession session) {
		User loginUser = (User)session.getAttribute("loginUser");
		videoService.updateStatus(id,loginUser);
		Video v = videoService.load(id);
		if(status==0) {
			model.addAttribute("success", "发布成功!");
			return unauditList(con,cid,model,session);
		} else {
			model.addAttribute("success", "已取消发布!");
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
	public String add(Model model,HttpSession session) {
		Video v = new Video();
		model.addAttribute("video",v);
		boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		if(isAdmin) { //如果是超级管理员
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.VIDEO_NEW.ordinal()));//返回所有视频新闻栏目
		} else {
			User loginUser = (User)session.getAttribute("loginUser");
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
	public String add(Video video,int cid,BindingResult br,HttpSession session,Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		if(video.getStatus() == 1) { //如果视频要立刻发布
			video.setPublishDate(new Date()); //设置发布时间
			video.setAuditor(loginUser.getNickname()); //设置发布人
		}
		videoService.add(video,cid,loginUser);
		model.addAttribute("success", "添加视频新闻成功!");
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
	public String delete(@PathVariable int id,@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Integer status,Model model,HttpSession session) {
		Video v = videoService.load(id);
		videoService.delete(id);
		model.addAttribute("success","视频新闻删除成功!");
		if(status==0) {
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
	public String update(@PathVariable int id,Model model,HttpSession session) {
		Video v = videoService.load(id);
		model.addAttribute("video",v);
		//返回相应的栏目
		boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		if(isAdmin) { //如果是超级管理员
			model.addAttribute("cs",channelService.listPublishChannel(ChannelType.VIDEO_NEW.ordinal()));//返回所有视频新闻栏目
		} else {
			User loginUser = (User)session.getAttribute("loginUser");
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
	public String update(@PathVariable int id,Video video,int cid,HttpSession session,Model model) {
		Video vOld = videoService.load(id);
		video.getVideo(vOld,(User)session.getAttribute("loginUser"));
		videoService.update(video,vOld,cid);
		model.addAttribute("success", "修改视频新闻成功!");
		return auditList(null, null, model, session);
	}
	/**
	 * 视频上传
	 * @param attach
	 * @param resp
	 * @throws IOException
	 */
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public void upload(MultipartFile attach,HttpServletResponse resp) throws IOException {
		AjaxObj ao = null;
		try {
			resp.setContentType("text/plain;charset=utf-8");
			Video v = new Video();
			String ext = FilenameUtils.getExtension(attach.getOriginalFilename());//获取文件后缀
			logger.info(ext);
			v.setVideoName(String.valueOf(new Date().getTime()+"."+ext));
			v.setSize(attach.getSize());
			videoService.addVideo(v,attach.getInputStream());
			String realPath = SystemContext.getRealPath();
			String path = realPath+GlobalResult.UPLOAD_VIDEO + "/" + v.getVideoName();
			//截图视频图片
			String picName = processImg(path);
			v.setPicName(picName);
			ao = new AjaxObj(1,null,v);
		} catch (IOException e) {
			ao = new AjaxObj(0,e.getMessage());
		}
		resp.getWriter().write(JsonUtil.getInstance().obj2json(ao));
	}
	/**
	 * 缩略图上传
	 * @param attach
	 * @param resp
	 * @throws IOException
	 */
	@RequestMapping(value="/uploadPic",method=RequestMethod.POST)
	/*public void uploadPic(MultipartFile attachPic,HttpServletResponse resp) throws IOException {
		AjaxObj ao = null;
		try {
			resp.setContentType("text/plain;charset=utf-8");
			String ext = FilenameUtils.getExtension(attachPic.getOriginalFilename());//获取文件后缀
			logger.info(ext);
			String picName =String.valueOf(new Date().getTime()+"."+ext);
			videoService.addPic(picName,attachPic.getInputStream());
			ao = new AjaxObj(1,null,picName);
		} catch (IOException e) {
			ao = new AjaxObj(0,e.getMessage());
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
		if (!file.exists()) {
			System.err.println("路径[" + veido_path + "]对应的视频文件不存在!");
			return null;
		}
		String realPath = SystemContext.getRealPath();
		String ffmpeg_path = "D:/ffmpeg.exe";//注意这里，一定要有这个插件啊
		String PicPath = realPath+GlobalResult.UPLOAD_VIDEO + "thumbnail/";//视频截图存放的位置
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
		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commands);
			builder.start();
			System.out.println("截取成功");
			return picName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
