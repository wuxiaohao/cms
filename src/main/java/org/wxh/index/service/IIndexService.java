package org.wxh.index.service;

public interface IIndexService {
	/**
	 * 重新生成顶部栏目信息
	 */
	public void generateTop();
	/**
	 * 重新生成底部信息
	 */
	public void generateBottom();
	public void generateBody();
	
}
