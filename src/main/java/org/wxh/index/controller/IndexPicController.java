package org.wxh.index.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;
import org.wxh.basic.common.Constant;
import org.wxh.basic.model.SystemContext;
import org.wxh.basic.util.JsonUtils;
import org.wxh.index.model.IndexPic;
import org.wxh.index.model.dto.IndexPicDto;
import org.wxh.index.service.IIndexPicService;
import org.wxh.index.service.IIndexService;
import org.wxh.sys.model.BaseInfo;
import org.wxh.topic.model.dto.AjaxObj;
import org.wxh.topic.service.IAttachmentService;
import org.wxh.user.auth.AuthClass;
import org.wxh.user.auth.AuthMethod;
import org.wxh.util.JsonUtil;

/**
 * 首页宣传图片和新闻图片管理
 * @author wxh
 *
 */

@Controller
@RequestMapping("/admin/pic")
@AuthClass("login")
public class IndexPicController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IIndexPicService indexPicService;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private IIndexService indexService;
	
	/**
	 * 显示首页新闻图片列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/newPics")
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String listNewPic(Model model) {
		model.addAttribute("datas", attachmentService.listAllPic());
		return "pic/listNewPic";
	}
	/**
	 * 显示首页宣传图片列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/indexPics")
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String listIndexPic(Model model,HttpSession session) {
		Map<String,Integer> mm = indexPicService.getMinAdnMaxPos();
		model.addAttribute(Constant.BaseCode.MIN, mm.get(Constant.BaseCode.MIN));
		model.addAttribute(Constant.BaseCode.MAX, mm.get(Constant.BaseCode.MAX));
		model.addAttribute("datas",indexPicService.findIndexPic());
		return "pic/listIndexPic";
	}
	/**
	 * 添加首页宣传图片的界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addIndexPicUI",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String addIndexPic(Model model) {
		IndexPic ip = new IndexPic();
		ip.setStatus(Constant.YES);
		model.addAttribute("indexPic", ip);
		return "pic/addIndexPic";
	}
	/**
	 * 添加首页宣传图片
	 * @param indexPic
	 * @param br
	 * @return
	 */
	@RequestMapping(value="/addIndexPic",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String addIndexPic(@Validated IndexPic indexPic,BindingResult br,Model model,HttpSession session) {
		if(br.hasFieldErrors()) {
			logger.error("添加首页宣传图片失败，请求参数[indexPic:{}]",JsonUtils.object2String(indexPic));
			return "pic/addIndexPic";
		}
		indexPicService.add(indexPic);
		if(indexPic.getStatus()!=Constant.NO) {
			indexService.generateBody();
		}
		model.addAttribute(Constant.BaseCode.SUCCESS, "添加首页宣传图片成功!");
		return listIndexPic(model,session);
	}
	/**
	 * 修改首页宣传图片的界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateIndexPicUI/{id}",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String updateIndexPic(@PathVariable int id,Model model) {
		IndexPic ip = indexPicService.load(id);
		model.addAttribute("indexPic", ip);
		return "pic/updateIndexPic";
	}
	/**
	 * 修改首页宣传图片
	 * @param id
	 * @param indexPic
	 * @param br
	 * @return
	 */
	@RequestMapping(value="/updateIndexPic/{id}",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String updateIndexPic(@PathVariable int id,@Validated IndexPic indexPic,String oldPic,BindingResult br,Model model,HttpSession session) {
		if(br.hasErrors()) {
			logger.error("修改首页宣传图片失败，请求参数[indexPic:{}]",JsonUtils.object2String(indexPic));
			return "pic/updateIndexPic";
		}
		if( !oldPic.equals(indexPic.getNewName()) ) {
			//删除原来旧的图片
			String realPath = SystemContext.getRealPath();
			String path = realPath + Constant.UrlConstant.FILE_PATH + File.separator;//新闻图片存放的位置
			new File(path+oldPic).delete();
		}
		IndexPic tip = indexPicService.load(id);
		tip.setLinkType(indexPic.getLinkType());
		tip.setLinkUrl(indexPic.getLinkUrl());
		tip.setNewName(indexPic.getNewName());
		tip.setOldName(indexPic.getOldName());
		tip.setStatus(indexPic.getStatus());
		tip.setTitle(indexPic.getTitle());
		indexPicService.update(tip);
		indexService.generateBody();
		//return "redirect:/jsp/common/updateSucByImg.jsp";
		model.addAttribute(Constant.BaseCode.SUCCESS, "修改首页宣传图片成功!");
		return listIndexPic(model,session);
	}
	/**
	 * 显示某个首页宣传图片
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/indexPic/{id}")
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String showIndexPic(@PathVariable int id,Model model) {
		model.addAttribute("indexPic",indexPicService.load(id));
		return "pic/showIndexPic";
	}
	/**
	 * 删除首页宣传图片
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteIndexPic/{id}",method=RequestMethod.POST)
	public String deleteIndexPic(@PathVariable int id ,Model model,HttpSession session) {
		indexPicService.delete(id);
		indexService.generateBody();
		model.addAttribute(Constant.BaseCode.SUCCESS,"首页宣传图片删除成功!");
		return listIndexPic(model, session);
	}
	/**
	 * 修改首页宣传图片的状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value="updateIndexPicStatus/{id}",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public String updateIndexPicStatus(@PathVariable int id ,Model model,HttpSession session) {
		indexPicService.updateStatus(id);
		indexService.generateBody();
		model.addAttribute(Constant.BaseCode.SUCCESS,"状态修改成功!");
		return listIndexPic(model, session);
	}
	/**
	 * 上传首页宣传图片
	 * @param session
	 * @param resp
	 * @param pic
	 */
	@RequestMapping(value="/uploadIndexPic",method=RequestMethod.POST)
	@AuthMethod(role=Constant.AuthConstant.ROLE_COMMADMIN)
	public void uploadIndexPic(HttpSession session,HttpServletResponse resp,MultipartFile pic) {
		resp.setContentType(Constant.CONTENT_TYPE);
		AjaxObj ao = new AjaxObj();
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			String oldName = pic.getOriginalFilename(); //图片的原始名称
			String newName = new Date().getTime()+"."+FilenameUtils.getExtension(oldName);//图片的新名称
			//网站配置信息的宽度、高度
			BaseInfo baseInfo = (BaseInfo)session.getServletContext().getAttribute(Constant.BaseCode.BASE_INFO);
			double w = baseInfo.getIndexPicWidth();
			double h = baseInfo.getIndexPicHeight();
			//获取上传图片的宽度，高度
			BufferedImage bi = ImageIO.read(pic.getInputStream());
			double nw = bi.getWidth();
			double nh = bi.getHeight();
			if(nw == w && nh == h) { //图片的大小符合要求
				//保存图片
				indexPicService.savePic(newName, pic.getInputStream());
				IndexPicDto ipd = new IndexPicDto();
				ipd.setNewName(newName);
				ipd.setOldName(oldName);
				ao.setObj(ipd);
				ao.setResult(Constant.YES);
			} else {
				ao.setResult(Constant.NO);
				ao.setMsg("图片的尺寸不在有效范围中");
			}
			
		} catch (IOException e) {
			ao.setResult(Constant.NO);
			ao.setMsg(e.getMessage());
		}
		
		out.println(JsonUtil.getInstance().obj2json(ao));
		out.flush();
	}
	/**
	 * 上传首页宣传图片
	 * @param session
	 * @param resp
	 * @param pic
	 */
	/*@RequestMapping(value="/uploadIndexPic",method=RequestMethod.POST)
	public void uploadIndexPic(HttpSession session,HttpServletResponse resp,MultipartFile pic) {
		resp.setContentType(Constant.CONTENT_TYPE);
		AjaxObj ao = new AjaxObj();
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			String oldName = pic.getOriginalFilename(); //图片的原始名称
			String newName = new Date().getTime()+"."+FilenameUtils.getExtension(oldName);//图片的新名称
			String realPath = session.getServletContext().getRealPath("");
			//创建临时文件存放的位置
			File f = new File(realPath+GlobalResult.FILE_PATH+"/temp"); 
			logger.info(realPath+GlobalResult.FILE_PATH+"/temp");
			if(!f.exists()) {
				f.mkdirs();
			}
			//网站配置信息的宽度、高度
			BaseInfo baseInfo = (BaseInfo)session.getServletContext().getAttribute(Constant.BaseCode.BASEINFO);
			double w = baseInfo.getIndexPicWidth();
			double h = baseInfo.getIndexPicHeight();
			//获取上传图片的宽度，高度
			BufferedImage bi = ImageIO.read(pic.getInputStream());
			double nw = bi.getWidth();
			double nh = bi.getHeight();
			if(nw > w && nw/nh < w/h) { //图片的大小符合要求
				//判断是否进行缩放
				Builder<BufferedImage> b = Thumbnails.of(bi);
				if(nw-w > 150) { 
					b.scale((w+150)/nw); //缩放比例
				} else {
					b.scale(1.0);
				}
				BufferedImage bi2 = b.asBufferedImage();
				//保存图片
				b.toFile(realPath+GlobalResult.FILE_PATH+"/temp/"+newName);
				IndexPicDto ipd = new IndexPicDto();
				ipd.setNewName(newName);
				ipd.setOldName(oldName);
				ipd.setIndexPicHeight(new Double(h).intValue());
				ipd.setIndexPicWidth(new Double(w).intValue());
				ipd.setImgWidth(bi2.getWidth());
				ipd.setImgHeight(bi2.getHeight());
				ao.setObj(ipd);
				ao.setResult(1);
			} else {
				ao.setResult(0);
				ao.setMsg("图片的尺寸不在有效范围中");
			}
			
		} catch (IOException e) {
			ao.setResult(0);
			ao.setMsg(e.getMessage());
		}
		
		out.println(JsonUtil.getInstance().obj2json(ao));
		out.flush();
	}*/
	/**
	 * 处理被剪切后的图片的坐标，并上传
	 * @param session
	 * @param x 
	 * @param y
	 * @param w
	 * @param h
	 * @param newName
	 * @return
	 */
	/*@RequestMapping(value="/confirmPic",method=RequestMethod.POST)
	public @ResponseBody AjaxObj confirmPic(HttpSession session,int x,int y,int w,int h,String newName) {
		
		AjaxObj ao = new AjaxObj();
		try {
			BaseInfo baseInfo = (BaseInfo)session.getServletContext().getAttribute(Constant.BaseCode.BASEINFO);
			int pw = baseInfo.getIndexPicWidth();
			int ph = baseInfo.getIndexPicHeight();
			String path = session.getServletContext().getRealPath("");
			String tpath = path+GlobalResult.FILE_PATH+"/temp/"+newName; //临时存放的路径
			File tf = new File(tpath);
			BufferedImage bi = ImageIO.read(tf);
			String npath = path+GlobalResult.FILE_PATH+"/"+newName; //新的存放路径
			String ttpath = path+GlobalResult.FILE_PATH+"/thumbnail/"+newName;//缩略图的路径
			Builder<BufferedImage> b = Thumbnails.of(bi);
			//根据坐标切割图片
			BufferedImage bi2 = b.sourceRegion(x, y, w, h).size(pw, ph).asBufferedImage();
			//写原图
			b.toFile(npath);
			//写缩略图
			Thumbnails.of(bi2).scale((double)GlobalResult.T_W/(double)pw).toFile(ttpath);
			//删除临时图片
			tf.delete(); 
			ao.setResult(1);
			return ao;
		} catch (IOException e) {
			e.printStackTrace();
			ao.setResult(0);
			ao.setMsg(e.getMessage());
		}
		return ao;
		
	}*/
}
