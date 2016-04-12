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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.wxh.basic.common.Constant;
import org.wxh.basic.common.LoginConstant;
import org.wxh.basic.exception.MyException;
import org.wxh.basic.filter.CmsSessionContext;
import org.wxh.basic.util.JsonUtils;
import org.wxh.user.model.Role;
import org.wxh.user.model.RoleType;
import org.wxh.user.model.User;
import org.wxh.user.model.dto.LoginDto;
import org.wxh.user.service.IUserService;
import org.wxh.util.Captcha;
import org.wxh.util.Common;

/**
 * 用户登录Controller
 * @author wxh
 *
 */
@Controller
public class LoginController implements LoginConstant {
	
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	
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
		String usernamecode=Common.getcookie(request,Constant.BaseCode.COOKIE);
		model.addAttribute(Constant.BaseCode.COOKIE, usernamecode);
		return LOGIN;
	}
	
	/**
	 * 处理用户登陆
	 * @param request
	 * @param model
	 * @param req
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(LoginDto dto,HttpServletRequest req,HttpSession session,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView( BLACKMAIN );
		
		if( dto.getUsername() == null ) {
			logger.error("用户名为空，请求参数：[{}]",JsonUtils.object2String(dto));
			mv.setViewName( LOGIN );
			return mv;
		}
		if( dto.getPassword() == null ) {
			logger.error("密码为空，请求参数：[{}]",JsonUtils.object2String(dto));
			mv.setViewName( LOGIN );
			return mv;
		}
		if( dto.getCheckcode() == null ) {
			logger.error("用户名为空，请求参数：[{}]",JsonUtils.object2String(dto));
			mv.setViewName( LOGIN );
			return mv;
		}
		if( dto.getUsername() == null ) {
			logger.error("验证码为空，请求参数：[{}]",JsonUtils.object2String(dto));
			mv.setViewName( LOGIN );
			return mv;
		}
		
		//校验验证码是否正确
		String cc = (String)session.getAttribute(Constant.BaseCode.CHECK_CODE);
		if ( cc == null || dto.getCheckcode( ) == null ) {
			mv.setViewName( LOGIN );
			return mv;
		}
		
		if (!cc.equals(dto.getCheckcode())) {
			mv.addObject(Constant.BaseCode.ERROR_CODE,"验证码出错，请重新输入");
			mv.setViewName( LOGIN );
			return mv;
		}
		
		try {
			User loginUser = userService.login( dto.getUsername(), dto.getPassword());
			session.setAttribute(Constant.BaseCode.LOGIN_USER, loginUser);
			List<Role> rs = userService.listUserRoles(loginUser.getId());
			boolean isAdmin = isRole(rs,RoleType.ROLE_ADMIN);
			session.setAttribute(Constant.AuthConstant.IS_ADMIN, isAdmin);
			if( !isAdmin ) {
				Set<String> actions = getAllActions(rs, session);
				session.setAttribute(Constant.AuthConstant.ALL_ACTIONS, actions);
				session.setAttribute(Constant.AuthConstant.IS_AUDIT, isRole(rs,RoleType.ROLE_AUDIT));
				session.setAttribute(Constant.AuthConstant.IS_PUBLISH, isRole(rs,RoleType.ROLE_PUBLISH));
				logger.info("用户[{}],所拥有的权限信息：[method:{},size:{}]", new Object[]{loginUser.getUsername(),actions,actions.size()});
			} else {
				logger.info("用户[{}],为超级管理员角色，拥有系统所有权限", loginUser.getUsername());
			}
			session.removeAttribute(Constant.BaseCode.CHECK_CODE);
			//保存登陆信息
			CmsSessionContext.addSessoin(session);
			//记住状态需跳转到单独登陆页面，只需输入密码即可，密码不能保存在cookie里；
			if( null != dto.getRemember() && dto.getRemember().equals(Constant.TRUE) ){
				Cookie usercookie = new Cookie( Constant.BaseCode.COOKIE, dto.getUsername() );
				response.addCookie(usercookie);
				logger.info("已保存用户[{}]登陆状态",loginUser.getUsername());
			} else {  //删除Cookie
				Cookie usercookie = new Cookie( Constant.BaseCode.COOKIE, dto.getUsername() );
				usercookie.setMaxAge(Constant.NO);
				response.addCookie(usercookie);
			} 
			logger.info("用户[{}]已成功登录系统！",loginUser.getUsername());
			return mv;
		} catch (MyException e) {
			//登陆失败，返回失败信息
			logger.error("登陆失败，失败信息:[{}]",e.getMessage());
			//把异常信息返回到客户端
			mv.addObject(Constant.BaseCode.ERROR,e.getMessage());
			mv.setViewName( LOGIN );
			return mv;
		}
		
	}
	
	/**
	 * 获取所有能够访问的方法 
	 * @param rs 角色列表
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Set<String> getAllActions(List<Role> rs,HttpSession session) {
		Set<String> actions = new HashSet<String>(); //存储可以被访问的方法
		//获取该系统下的所有需要被权限控制的方法
		Map<String,Set<String>> allAuths = (Map<String,Set<String>>)session.getServletContext().getAttribute(Constant.AuthConstant.ALL_AUTHS);
		actions.addAll(allAuths.get( Constant.AuthConstant.AUTH_BASE )); //存入可以被所有角色访问的方法
		for( Role r : rs ) {
			if( r.getRoleType() == RoleType.ROLE_ADMIN ) continue;
			Set<String> auths = allAuths.get( r.getRoleType().name() );
			if( auths != null ) {
				actions.addAll( allAuths.get( r.getRoleType().name() ) ); //存入该角色可访问的方法
			} else {
				logger.warn( "角色[{}]没有任何权限", JsonUtils.object2String(r) );
			}
		}
		return actions;
	}
	
	/**
	 * 判断是否有指定的角色
	 * @param rs 角色列表
	 * @param rt 角色类型
	 * @return true or false
	 */
	private boolean isRole(List<Role> rs,RoleType rt) {
		for(Role r:rs) {
			if(r.getRoleType()==rt) return true;
		}
		return false;
	}
	
	/**
	 * 获取验证码
	 * @param resp
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping("/drawCheckCode")
	public void drawCheckCode(HttpServletResponse resp,HttpSession session) throws IOException {
		resp.setContentType(Constant.CONTENT_TYPE_IMG);
		int width = Constant.DRAW_CHECK_CODE_WIDTH;
		int height = Constant.DRAW_CHECK_CODE_HEIGHT;
		Captcha c = Captcha.getInstance();
		c.set(width, height);
		String checkcode = c.generateCheckcode();
		session.setAttribute(Constant.BaseCode.CHECK_CODE, checkcode);
		OutputStream os = resp.getOutputStream();
		ImageIO.write(c.generateCheckImg(checkcode), Constant.JPG, os);
	}
	
	/*@RequestMapping("/upload")
	public @ResponseBody AjaxObj upload(MultipartFile attach){
		System.out.println(attach.getOriginalFilename());
		return new AjaxObj();
	}*/
	
}
