package org.wxh.basic.filter;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.wxh.user.auth.AuthUtil;
import org.wxh.util.BaseInfoUtil;

/**
 * 初始化Servlet
 * @author wxh
 *
 */
public class InitServlet extends HttpServlet {

	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);
	
	private static final long serialVersionUID = 1L;
	private static WebApplicationContext wc;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//初始化spring的工厂
		wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		//初始化系统的角色所访问的功能信息
		Map<String,Set<String>> auths = AuthUtil.initAuth("org.wxh.topic.controller");
		auths.putAll(AuthUtil.initAuth("org.wxh.user.controller"));
		this.getServletContext().setAttribute("allAuths", auths);
		//初始化网站基本信息
		this.getServletContext().setAttribute("baseInfo", BaseInfoUtil.getInstacne().read());
		logger.info("------------------------系统初始化成功:"+auths+"-----------------------------");
	}
	
	public static WebApplicationContext getWc() {
		return wc;
	}

}
