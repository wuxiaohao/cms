package org.wxh.basic.filter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * session监听器
 * @author wxh
 *
 */
public class CmsSessionListener implements HttpSessionListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		CmsSessionContext.removeSession( event.getSession() );
		logger.info("移除了Session:[{}]",event.getSession().getId());
	}

}
