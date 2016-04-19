package org.wxh.basic.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wxh.basic.common.Constant;
import org.wxh.user.model.User;

/**
 * session池
 * @author wxh
 *
 */
public class CmsSessionContext {
	
	private static Logger logger = LoggerFactory.getLogger(CmsSessionContext.class);
	
	private static final Map<String,HttpSession> ctx = new HashMap<String,HttpSession>();
	
	//单例
	private CmsSessionContext(){}
	
	public static void addSessoin(HttpSession session) {
		User user = (User) session.getAttribute(Constant.BaseCode.LOGIN_USER);
		ctx.put(session.getId(), session);
		logger.info("已新增用户:[{}]的登录状态",user.getUsername());
	}
	
	public static void removeSession(HttpSession session) {
		User user = (User) session.getAttribute(Constant.BaseCode.LOGIN_USER);
		logger.info("已移除用户:[{}]的登录状态",user.getUsername());
		ctx.remove(session.getId());
	}
	
	public static HttpSession getSession(String sessionId) {
		return ctx.get(sessionId);
	}
}
