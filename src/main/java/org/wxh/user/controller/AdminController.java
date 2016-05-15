package org.wxh.user.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.wxh.basic.common.Constant;
import org.wxh.basic.filter.CmsSessionContext;
import org.wxh.user.auth.AuthClass;
import org.wxh.user.auth.AuthMethod;

@Controller
@AuthClass("/login")
public class AdminController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 访问首页
	 * @return
	 */
	@RequestMapping("/admin")
	@AuthMethod
	public ModelAndView index() {
		
		return new ModelAndView( "admin/index" );
		
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
}
