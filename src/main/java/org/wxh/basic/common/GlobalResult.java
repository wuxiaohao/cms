package org.wxh.basic.common;

import org.wxh.basic.model.SystemContext;

/**
 * 全局结果定义
 * @author wxh
 */
public interface GlobalResult {

	public static final String ROOT_NAME = "网站系统栏目";
	
	public static final int ROOT_ID = 0;
	
	public final static int IMG_WIDTH = 900;
	public final static int THUMBNAIL_WIDTH = 150;
	public final static int THUMBNAIL_HEIGHT = 110;
	/**
	 * 附件路径
	 * SystemContext.getRealPath()+UPLOAD_PATH
	 */
	public final static String UPLOAD_PATH="/resources/upload/";
	/**
	 * 首页图片的路径
	 * 
	 */
	public final static String FILE_PATH="/resources/indexPic";
	
	public final static int T_W = 120;
	
}
