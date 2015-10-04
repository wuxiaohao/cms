package org.wxh.basic.filter;

import org.apache.log4j.Logger;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.wxh.basic.exception.CmsException;
import org.wxh.user.model.User;

/**
 * 权限过滤器
 * @author wxh
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		/*
		 * 如果使用uploadify进行文件的上传，由于flash的bug问题，会产生一个新的session，此时验证失败
		 * 所以必须在此处获取一个原有的session，然后重置session信息
		 */
		String sid = request.getParameter("sid");
		if(sid!=null&&!"".equals(sid.trim())) {
			//当sid有值，就表示是通过uploadify上传文件，此时就应该获取原有的session
			session = CmsSessionContext.getSession(sid);
		}
		HandlerMethod hm = (HandlerMethod)handler;
		logger.info(hm.getBean().getClass().getName()+"."+hm.getMethod().getName());
		User user = (User)session.getAttribute("loginUser");
		//如果用户没登陆，则重定向到登陆页面
		if(user==null) {
			response.sendRedirect(request.getContextPath()+"/login");
		} else {
			boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
			//如果不是超级管理员
			if(!isAdmin) {
				//不是超级管理人员，就需要判断是否有权限访问某些功能
				Set<String> actions = (Set<String>)session.getAttribute("allActions");  //得到该用户能访问的所有方法名称
				String aname = hm.getBean().getClass().getName()+"."+hm.getMethod().getName(); //得到要访问的方法名称
				if(!actions.contains(aname)) throw new CmsException("没有权限访问该功能");
			}
		}
		return super.preHandle(request, response, handler);
	}
}
