package org.wxh.sys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wxh.sys.model.CmsLink;
import org.wxh.sys.service.ICmsLinkService;
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
	public String list(Model model,String type) {
		model.addAttribute("datas", cmsLinkService.findByType(type));
		model.addAttribute("types",cmsLinkService.listAllType());
		Map<String,Integer> m = cmsLinkService.getMinAndMaxPos();
		model.addAttribute("min", m.get("min"));
		model.addAttribute("max",m.get("max"));
		return "cmsLink/list";
	}
	/**
	 * 返回添加超链接的界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
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
	public String add(@Validated CmsLink cmsLink,BindingResult br) {
		if(br.hasFieldErrors()) {
			return "cmsLink/add";
		}
		cmsLinkService.add(cmsLink);
		return "redirect:/admin/cmsLink/links";
	}
	/**
	 * 删除超链接
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		cmsLinkService.delete(id);
		return "redirect:/admin/cmsLink/links";
	}
	/**
	 * 返回修改超链接的界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
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
	public String update(@PathVariable int id,@Validated CmsLink cmsLink,BindingResult br) {
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
		return "redirect:/admin/cmsLink/links";
	}
	/**
	 * 显示某个超链接详细信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(cmsLinkService.load(id));
		return "cmsLink/show";
	}
}
