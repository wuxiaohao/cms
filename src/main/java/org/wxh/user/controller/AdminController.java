package org.wxh.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wxh.basic.filter.CmsSessionContext;
import org.wxh.user.auth.AuthClass;
import org.wxh.user.auth.AuthMethod;

@Controller
@AuthClass("/login")
public class AdminController {
	
	/**
	 * 访问首页
	 * @return
	 */
	@RequestMapping("/admin")
	@AuthMethod
	public String index(){
		return "admin/index";
	}
	
	/**
	 * 退出登陆
	 * @param session
	 * @return
	 */
	@AuthMethod
	@RequestMapping("/admin/logout")
	public String logout(HttpSession session) {
		CmsSessionContext.removeSession(session);
		session.invalidate();
		return "redirect:/login";
	}
	
	/**
	 * 跳转到首页，再从首页跳转到文章列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/redirectIndexByTopic")
	public String redirectIndexByTopic(HttpSession session){
		session.setAttribute("messageByTopic","1");
		return "redirect:/admin";
	}
	/**
	 * 跳转到首页，再从首页跳转到首页图片宣传列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/redirectIndexByImg")
	public String redirectIndexByImg(HttpSession session){
		session.setAttribute("messageByImg","1");
		return "redirect:/admin";
	}
	/**
	 * 跳转到首页，再从首页跳转到超链接列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/redirectIndexByLink")
	public String redirectIndexByLink(@RequestParam(required=false) String type,HttpSession session){
		System.out.println("过来了");
		session.setAttribute("type",type);
		session.setAttribute("message2","2");
		return "redirect:/admin";
	}
}
