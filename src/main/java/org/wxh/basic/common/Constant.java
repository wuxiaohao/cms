package org.wxh.basic.common;

import java.io.File;

import org.wxh.basic.model.SystemContext;

/**
 * 常量
 * @author wxh
 *
 */
public interface Constant {

	public static final String ROOT_NAME = "网站系统栏目";

	public static final int ROOT_ID = 0;
	/**新闻t图片宽度*/
	public final static int IMG_WIDTH = 900;
	public final static int THUMBNAIL_WIDTH = 150;
	public final static int THUMBNAIL_HEIGHT = 110;
	
	/** 友情链接图片的宽度*/
	public final static int LINKPIC_WIDTH = 222;
	/** 友情链接图片的高度*/
	public final static int LINKPIC_HEIGHT = 60;
	/**用户头像的宽度*/
	public final static int ICON_WIDTH = 150;
	/** 用户头像的高度*/
	public final static int ICON_HEIGHT = 150;
	/**用户头像宽度缩略图*/
	public final static int ICON_WIDTH_THUMBNAIL = 29;
	/** 用户头像高度缩略图*/
	public final static int ICON_HEIGHT_THUMBNAIL = 29;

	public final static int T_W = 120;
	/** 验证码的宽度*/
	public final static int DRAW_CHECK_CODE_WIDTH = 150;
	/**验证妈的高度*/
	public final static int DRAW_CHECK_CODE_HEIGHT = 40;
	
	/** 图片类型*/
	public final static String JPG= "jpg";
	/**utf-8字符编码*/
	public static final String CONTENT_TYPE = "text/plain;charset=utf-8";
	public static final String FALSE = "false";
	public static final String TRUE = "true";
	
	/**
	 * 权限常量
	 */
	public interface AuthConstant {
		/**基础权限*/
		public static final String AUTH_BASE = "base";
		/**文章发布员*/
		public static final String ROLE_PUBLISH = "ROLE_PUBLISH";
		/**文章审核员*/
		public static final String ROLE_AUDIT = "ROLE_AUDIT";
		/**普通管理员*/
		public static final String ROLE_COMMADMIN = "ROLE_COMMADMIN";
		/**能够被当前用户访问的所有权限信息*/
		public static final String ALL_ACTIONS = "allActions";
		/**是否是文章审核人*/
		public static final String IS_AUDIT = "isAudit";
		/**是否是文章发布人*/
		public static final String IS_PUBLISH = "isPublish";
		/**系统下所有权限信息*/
		public static final String ALL_AUTHS = "allAuths";
		/**是否是超级管理员*/
		public static final String IS_ADMIN = "isAdmin";
	}
	
	/**
	 * URL常量
	 */
	public interface UrlConstant {
		
		/**附件路径   SystemContext.getRealPath()+UPLOAD_PATH */
		public final static String UPLOAD_PATH = File.separator + "resources" + File.separator + "upload" + File.separator;
		/**图片新闻的图片路径*/
		public final static String UPLOAD_PICTURE = File.separator + "resources" + File.separator + "picTopic" + File.separator;
		/**视频新闻的视频路径*/
		public final static String UPLOAD_VIDEO = File.separator + "resources" + File.separator + "video" + File.separator;
		/**首页图片的路径*/
		public final static String FILE_PATH = File.separator + "resources" + File.separator + "indexPic";
		/**用户头像的路径*/
		public final static String ICON_PATH = File.separator + "resources" + File.separator + "userIcon";
		/**友情链接图片的路径*/
		public final static String LINK_PATH = File.separator + "resources" + File.separator + "linkPic";
		/**首页宣传图片的路径*/
		public final static String INDEX_PIC_PATH = File.separator + "resources" + File.separator + "indexPic" + File.separator;
		/**首页宣传图片的缩略图路径*/
		public final static String INDEX_PIC_THU_PATH = File.separator + "resources" + File.separator + "indexPic" + File.separator + "thumbnail" + File.separator;
		/**首页宣传图片的临时图片路径*/
		public final static String INDEX_PIC_TMP_PATH = File.separator + "resources" + File.separator +"indexPic" + File.separator + "temp";
	}
	
	interface BaseCode {
		/**返回成功的标识*/
		public static final String SUCCESS ="success";
		/**返回失败的标识*/
		public static final String ERROR = "error";
		/**返回失败代码标识符*/
		public static final String ERROR_CODE = "errorCode";
		/**登录用户标*/
		public static final String LOGIN_USER = "loginUser";
		/**生成的验证码*/
		public static final String CHECK_CODE = "cc";
		/**网站基本信息*/
		public static final String BASE_INFO = "baseInfo";
		/**最大值*/
		public static final String MAX ="max";
		/**最小值*/
		public static final String MIN ="min";
		/***/
		public static final String COOKIE = "cms_cookie_username";
	}
	
}
