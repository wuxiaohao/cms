package org.wxh.basic.common;

import java.io.File;

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
	 * 友情链接图片的宽度
	 */
	public final static int LINKPIC_WIDTH = 222;
	/**
	 * 友情链接图片的高度
	 */
	public final static int LINKPIC_HEIGHT = 60;
	/**
	 * 用户头像的宽度
	 */
	public final static int ICON_WIDTH = 150;
	/**
	 * 用户头像的高度
	 */
	public final static int ICON_HEIGHT = 150;
	/**
	 * 用户头像宽度缩略图
	 */
	public final static int ICON_WIDTH_THUMBNAIL = 29;
	/**
	 * 用户头像高度缩略图
	 */
	public final static int ICON_HEIGHT_THUMBNAIL = 29;
	
	/**
	 * 附件路径
	 * SystemContext.getRealPath()+UPLOAD_PATH
	 */
	public final static String UPLOAD_PATH= File.separator+"resources"+File.separator+"upload"+File.separator;
	/**
	 * 图片新闻的图片路径
	 */
	public final static String UPLOAD_PICTURE= File.separator+"resources"+File.separator+"picTopic"+File.separator;
	/**
	 * 视频新闻的视频路径
	 */
	public final static String UPLOAD_VIDEO= File.separator+"resources"+File.separator+"video"+File.separator;
	/**
	 * 首页图片的路径
	 * 
	 */
	public final static String FILE_PATH= File.separator+"resources"+File.separator+"indexPic";
	/**
	 * 用户头像的路径
	 * 
	 */
	public final static String ICON_PATH= File.separator+"resources"+File.separator+"userIcon";
	/**
	 * 友情链接图片的路径
	 * 
	 */
	public final static String LINK_PATH= File.separator+"resources"+File.separator+"linkPic";
	
	public final static int T_W = 120;
	
}
