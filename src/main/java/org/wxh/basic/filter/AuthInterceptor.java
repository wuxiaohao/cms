package org.wxh.basic.filter;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.wxh.basic.common.Constant;
import org.wxh.basic.exception.CmsException;
import org.wxh.basic.util.JsonUtils;
import org.wxh.user.model.User;

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
		/**
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
		String resource_url = hm.getBean().getClass().getName() + "." + hm.getMethod().getName();
		User user = (User)session.getAttribute(Constant.BaseCode.LOGIN_USER);
		//如果用户没登陆，则重定向到登陆页面
		if( user == null ) {
			logger.error("用户未登陆，已被系统拦截，用户信息：[{}]",JsonUtils.object2String(user));
			response.sendRedirect( request.getContextPath() + "/login" );
			return false;
		} else {
			logger.info("用户[{}]请求访问资源的路径：[{}]", new Object[]{user.getUsername(),resource_url} );
			boolean isAdmin = (Boolean)session.getAttribute(Constant.AuthConstant.IS_ADMIN);
			//如果不是超级管理员
			if(!isAdmin) {
				//不是超级管理人员，就需要判断是否有权限访问某些功能
				Set<String> actions = (Set<String>)session.getAttribute(Constant.AuthConstant.ALL_ACTIONS);  //得到该用户能访问的所有方法名称
				if(!actions.contains( resource_url )) {
					//logger.error( "没有权限访问:[{}]，用户信息：[{}]", new Object[]{resource_url,JsonUtils.object2String(user)} );
					logger.error( "没有权限访问:[{}]", resource_url );
					throw new CmsException("没有权限访问该功能");
				}
			}
		}
		return super.preHandle(request, response, handler);
	}
}
