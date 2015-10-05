package org.wxh.sys.controller;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wxh.basic.model.SystemContext;
import org.wxh.index.service.IIndexPicService;
import org.wxh.index.service.IIndexService;
import org.wxh.sys.model.BaseInfo;
import org.wxh.topic.service.IAttachmentService;
import org.wxh.user.auth.AuthClass;
import org.wxh.util.BaseInfoUtil;

/**
 * 系统管理
 * @author wxh
 *
 */

@RequestMapping("/admin/system")
@Controller
@AuthClass
public class SystemController {
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private IIndexPicService indexPicService;
	@Autowired
	private IIndexService indexService;

	public IIndexService getIndexService() {
		return indexService;
	}
	public void setIndexService(IIndexService indexService) {
		this.indexService = indexService;
	}
	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}
	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	public IIndexPicService getIndexPicService() {
		return indexPicService;
	}
	public void setIndexPicService(IIndexPicService indexPicService) {
		this.indexPicService = indexPicService;
	}
	/**
	 * 显示网站基本信息的界面
	 * @return
	 */
	@RequestMapping("/baseinfo")
	public String showBaseInfo(Model model) {
		return "system/showBaseInfo";
	}
	/**
	 * 修改网站基本信息的界面
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/baseinfo/updateUI",method=RequestMethod.POST)
	public String updateBaseInfo(HttpSession session,Model model) {
		model.addAttribute("baseInfo", session.getServletContext().getAttribute("baseInfo"));
		return "system/updateBaseInfo";
	}
	/**
	 * 修改网站基本信息
	 * @param baseInfo
	 * @param br
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/baseinfo/update",method=RequestMethod.POST)
	public String updateBaseInfo(@Validated BaseInfo baseInfo,BindingResult br,HttpSession session,Model model) {
		if(br.hasErrors()) {
			return "system/updateBaseInfo";
		}
		BaseInfo bi = BaseInfoUtil.getInstacne().write(baseInfo);
		session.getServletContext().setAttribute("baseInfo", bi);
		//更新静态页面的首部、底部的信息
		indexService.generateBottom();
		indexService.generateTop();
		model.addAttribute("success", "修改成功!");
		return showBaseInfo(model);
	}
	/**
	 * 显示未引用的垃圾附件和首页图片的页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/cleans")
	public String listCleans(Model model) {
		model.addAttribute("attNums", attachmentService.findNoUseAttachmentNum());
		model.addAttribute("indexPics", listNoUseIndexPicNum(indexPicService.listAllIndexPicName()));
		return "system/cleans";
	}
	/**
	 * 获取indexPic文件夹下所有首页图片
	 * @return
	 */
	private File[] listPicFile() {
		String path = SystemContext.getRealPath();
		File f = new File(path+"/resources/indexPic");
		File[] fs = f.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if(pathname.isDirectory())
					return false;
				return true;
			}
		});
		return fs;
	}
	/**
	 * 获取没有使用的首页图片数量
	 * @param pics
	 * @return
	 */
	private int listNoUseIndexPicNum(List<String> pics) {
		File[] fs = listPicFile();
		int count = 0;
		for(File file:fs) {
			if(!pics.contains(file.getName())) count++;
		}
		return count;
	}
	/**
	 * 获取没有使用的首页图片列表
	 * @param pics
	 * @return
	 */
	private List<String> listNoUseIndexPic(List<String> pics) {
		File[] fs = listPicFile();
		List<String> npics = new ArrayList<String>();
		for(File f:fs) {
			if(!pics.contains(f.getName())) npics.add(f.getName());
		}
		return npics;
	}
	/**
	 * 显示所有未引用的文章附件或首页图片的列表
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping("/cleanList/{name}")
	public String cleanList(@PathVariable String name,Model model) {
		if(name.equals("atts")) {
			model.addAttribute("datas", attachmentService.findNoUseAttachment());
			return "system/cleanAtts";
		} else if(name.equals("pics")) {
			model.addAttribute("datas", listNoUseIndexPic(indexPicService.listAllIndexPicName()));
			return "system/cleanPics";
		}
		return "";
	}
	/**
	 * 清理未引用的文章附件或首页图片
	 * @param name
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/clean/{name}")
	public String clean(@PathVariable String name,Model model) throws IOException {
		if(name.equals("atts")) {
			attachmentService.clearNoUseAttachment();
		} else if(name.equals("pics")) {
			indexPicService.cleanNoUseIndexPic(listNoUseIndexPic(indexPicService.listAllIndexPicName()));
		}
		model.addAttribute("success", "清理完成!");
		return listCleans(model);
	}
	
}
