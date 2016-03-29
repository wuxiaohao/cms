package org.wxh.basic.filter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * session监听器
 * @author wxh
 *
 */
public class CmsSessionListener implements HttpSessionListener {
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		CmsSessionContext.removeSession( event.getSession() );
	}

}
