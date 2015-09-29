package org.wxh.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

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
import org.wxh.sys.model.CmsLink;
import org.wxh.sys.service.ICmsLinkService;
import org.wxh.topic.controller.TopicController;
import org.wxh.user.auth.AuthClass;

/**
 * 超链接管理
 * @author wxh
 *
 */

@Controller
@AuthClass
@RequestMapping("/admin/cmsLink")
public class CmsLinkController {
	
	private static final Logger logger = Logger.getLogger(TopicController.class);
	
	@Autowired
	private ICmsLinkService cmsLinkService;
	
	public ICmsLinkService getCmsLinkService() {
		return cmsLinkService;
	}
	public void setCmsLinkService(ICmsLinkService cmsLinkService) {
		this.cmsLinkService = cmsLinkService;
	}
	/**
	 * 返回所有超链接的列表页面
	 * @param model
	 * @param type
	 * @return
	 */
	@RequestMapping("/links")
	public String list(Model model,String type,HttpSession session) {
		model.addAttribute("datas", cmsLinkService.findByType(type));
		model.addAttribute("types",cmsLinkService.listAllType());
		Map<String,Integer> m = cmsLinkService.getMinAndMaxPos();
		model.addAttribute("min", m.get("min"));
		model.addAttribute("max",m.get("max"));
		model.addAttribute("type",type);
		//清空session残留
		if(session.getAttribute("type") != null || session.getAttribute("message2") !=null ){
			session.removeAttribute("type");
			session.removeAttribute("message2");
			logger.info("超级接跳转导致残留的session已清除干净");
		}
		
		return "cmsLink/list";
	}
	/**
	 * 跳转到首页，再从首页跳转到超链接列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/redirectIndex")
	public String redirectIndex(@RequestParam(required=false) String type,HttpSession session){
		session.setAttribute("type",type);
		session.setAttribute("message2","2");
		return "redirect:/admin";
	}
	/**
	 * 返回添加超链接的界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addUI",method=RequestMethod.POST)
	public String addUI(Model model) {
		model.addAttribute(new CmsLink());
		model.addAttribute("types",cmsLinkService.listAllType());
		return "cmsLink/add";
	}
	/**
	 * 添加超链接
	 * @param cmsLink
	 * @param br
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated CmsLink cmsLink,BindingResult br,Model model,HttpSession session) {
		if(br.hasFieldErrors()) {
			return "cmsLink/add";
		}
		cmsLinkService.add(cmsLink);
		model.addAttribute("success", "超链接添加成功!");
		return list(model,null,session);
	}
	/**
	 * 删除超链接
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable int id ,Model model,HttpSession session) {
		cmsLinkService.delete(id);
		model.addAttribute("success", "超链接删除成功!");
		return list(model,null,session);
	}
	/**
	 * 返回修改超链接的界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updateUI/{id}",method=RequestMethod.POST)
	public String updateUI(@PathVariable int id,Model model) {
		model.addAttribute(cmsLinkService.load(id));
		model.addAttribute("types",cmsLinkService.listAllType());
		return "cmsLink/update";
	}
	/**
	 * 修改超链接
	 * @param id
	 * @param cmsLink
	 * @param br
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Validated CmsLink cmsLink,BindingResult br,Model model,HttpSession session) {
		if(br.hasFieldErrors()) {
			return "cmsLink/update";
		}
		CmsLink tcl = cmsLinkService.load(id);
		tcl.setNewWin(cmsLink.getNewWin());
		tcl.setTitle(cmsLink.getTitle());
		tcl.setType(cmsLink.getType());
		tcl.setUrl(cmsLink.getUrl());
		tcl.setUrlClass(cmsLink.getUrlClass());
		tcl.setUrlId(cmsLink.getUrlId());
		cmsLinkService.update(tcl);
		model.addAttribute("success", "修改成功!");
		return list(model,null,session);
	}
	/**
	 * 显示某个超链接详细信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(cmsLinkService.load(id));
		return "cmsLink/show";
	}
}
