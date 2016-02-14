package org.wxh.index.service;

public interface IIndexService {
	/**
	 * 重新生成顶部栏目信息head
	 */
	public void generateTop();
	/**
	 * 重新生成底部信息bottmo
	 */
	public void generateBottom();
	/**
	 * 重新生成首页body
	 */
	public void generateBody();
	/**
	 * 重新生成超链接页面
	 */
	public void generateLink();
	
}
