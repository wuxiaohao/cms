package org.wxh.user.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wxh.basic.exception.CmsException;
import org.wxh.basic.exception.MyException;
import org.wxh.basic.filter.CmsSessionContext;
import org.wxh.util.Captcha;
import org.wxh.util.Common;
import org.wxh.topic.model.dto.AjaxObj;
import org.wxh.user.auth.AuthMethod;
import org.wxh.user.model.Role;
import org.wxh.user.model.RoleType;
import org.wxh.user.model.User;
import org.wxh.user.service.IUserService;

@Controller
public class LoginController {
	
	@Autowired
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 返回用户登陆的界面
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(HttpServletRequest request,Model model) {
		String usernamecode=Common.getcookie(request,"cms_cookie_username");
		model.addAttribute("cms_cookie_username", usernamecode);
		return "admin/login";
	}
	
	/**
	 * 处理用户登陆
	 * @param username
	 * @param password
	 * @param checkcode
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username,String password,String checkcode,String remember,Model model,
			HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		String cc = (String)session.getAttribute("cc");
		if(!cc.equals(checkcode)) {
			model.addAttribute("errorCode","验证码出错，请重新输入");
			return "admin/login";
		}
		try {
			User loginUser = userService.login(username, password);
			session.setAttribute("loginUser", loginUser);
			List<Role> rs = userService.listUserRoles(loginUser.getId());
			boolean isAdmin = isRole(rs,RoleType.ROLE_ADMIN);
			session.setAttribute("isAdmin", isAdmin);
			if(!isAdmin) {
				session.setAttribute("allActions", getAllActions(rs, session));
				session.setAttribute("isAudit", isRole(rs,RoleType.ROLE_AUDIT));
				session.setAttribute("isPublish", isRole(rs,RoleType.ROLE_PUBLISH));
			}
			session.removeAttribute("cc");
			//保存登陆信息
			CmsSessionContext.addSessoin(session);
			//记住状态需跳转到单独登陆页面，只需输入密码即可，密码不能保存在cookie里；
			if(null!=remember&&remember.equals("true")){
				Cookie usercookie = new Cookie("cms_cookie_username", username);
				response.addCookie(usercookie);
			} else {  //删除Cookie
				Cookie usercookie = new Cookie("cms_cookie_username", username);
				usercookie.setMaxAge(0);
				response.addCookie(usercookie);
			} 
			
			return "admin/blackmain";
		} catch (MyException e) {
			//登陆失败，返回失败信息
			System.out.println(e.getMessage());
			model.addAttribute("error",e.getMessage());
			return "admin/login";
		}
		
	}
	
	/**
	 * 获取所有能够访问的方法 
	 */
	@SuppressWarnings("unchecked")
	private Set<String> getAllActions(List<Role> rs,HttpSession session) {
		Set<String> actions = new HashSet<String>();
		Map<String,Set<String>> allAuths = (Map<String,Set<String>>)session.getServletContext().getAttribute("allAuths");
		actions.addAll(allAuths.get("base"));
		for(Role r:rs) {
			if(r.getRoleType()==RoleType.ROLE_ADMIN) continue;
			actions.addAll(allAuths.get(r.getRoleType().name()));
		}
		return actions;
	}
	
	
	/**
	 * 判断是否有指定的角色
	 * @param rs
	 * @param rt
	 * @return
	 */
	private boolean isRole(List<Role> rs,RoleType rt) {
		for(Role r:rs) {
			if(r.getRoleType()==rt) return true;
		}
		return false;
	}
	
	
	@RequestMapping("/drawCheckCode")
	public void drawCheckCode(HttpServletResponse resp,HttpSession session) throws IOException {
		resp.setContentType("image/jpg");
		int width = 150;
		int height = 40;
		Captcha c = Captcha.getInstance();
		c.set(width, height);
		String checkcode = c.generateCheckcode();
		session.setAttribute("cc", checkcode);
		OutputStream os = resp.getOutputStream();
		ImageIO.write(c.generateCheckImg(checkcode), "jpg", os);
	}
	
	/*@RequestMapping("/upload")
	public @ResponseBody AjaxObj upload(MultipartFile attach){
		System.out.println(attach.getOriginalFilename());
		return new AjaxObj();
	}*/
	
}
