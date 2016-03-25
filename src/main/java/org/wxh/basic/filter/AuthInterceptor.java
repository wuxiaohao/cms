package org.wxh.basic.filter;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.wxh.basic.exception.CmsException;
import org.wxh.basic.util.JsonUtils;
import org.wxh.user.model.User;
import org.wxh.util.JsonUtil;

/**
 * 权限过滤器
 * @author wxh
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

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
		//获取请求访问的路径：包名+类名+方法名
		String resource_url = hm.getBean().getClass().getName()+"."+hm.getMethod().getName();
		logger.info("请求访问资源的路径：{}", resource_url );
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
				if(!actions.contains( resource_url )) {
					logger.error( "没有权限访问，用户信息：{}", JsonUtils.object2String(user) );
				}
			}
		}
		return super.preHandle(request, response, handler);
	}
}
