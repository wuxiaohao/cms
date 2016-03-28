package org.wxh.basic.filter;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.wxh.basic.common.Constant;
import org.wxh.basic.util.JsonUtils;
import org.wxh.user.auth.AuthUtil;
import org.wxh.util.BaseInfoUtil;

/**
 * 初始化Servlet
 * @author wxh
 *
 */
public class InitServlet extends HttpServlet {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final long serialVersionUID = 1L;
	private static WebApplicationContext wc;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//初始化spring的工厂
		wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		//初始化系统该系统下的所有需要被权限控制的方法
		Map<String,Set<String>> auths = AuthUtil.initAuth();
		this.getServletContext().setAttribute(Constant.AuthConstant.ALL_AUTHS, auths);
		//初始化网站基本信息
		this.getServletContext().setAttribute(Constant.BaseCode.BASE_INFO, BaseInfoUtil.getInstacne().read());
		logger.info( "系统初始化成功，已初始化的权限信息：[{}]",JsonUtils.map2String( auths ) );
	}
	
	public static WebApplicationContext getWc() {
		return wc;
	}

}
