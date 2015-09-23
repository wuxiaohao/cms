package org.wxh.basic.filter;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CmsSessionListener implements HttpSessionListener {
	
	private static final Logger logger = Logger.getLogger(CmsSessionListener.class);

	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		CmsSessionContext.removeSession(event.getSession());
		logger.info("移除了Session:"+event.getSession().getId());
	}

}
