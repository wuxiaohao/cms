package org.wxh.topic.dwr;

/**
 * dwr相关的业务类
 * 每新增一个业务方法，要在cms-servlet.xml中配置该方法
 * @author wxh
 *
 */
public interface IDwrService {
	/**
	 * 添加GroupChannel对象
	 * @param group
	 * @param channel
	 */
	public void addGroupChannel(int gid,int cid);
	
	/**
	 * 删除用户栏目
	 * @param gid
	 * @param cid
	 */
	public void deleteGroupChannel(int gid,int cid);
	
	/**
	 * 更新是否是主页信息
	 * @param aid
	 */
	public void updateIndexPic(int aid);
	
	/**
	 * 更新是否是附件信息
	 * @param aid
	 */
	public void updateAttachInfo(int aid);
	
	/**
	 * 删除附件信息
	 * @param id
	 */
	public void deleteAttach(int id);
	/**
	 * 更新首页图片的排序
	 * @param id 要更新的首页图片的id
	 * @param oldPos 原位置
	 * @param newPos 新位置
	 */
	public void updatePicPos(int id, int oldPos, int newPos);
	/**
	 * 更新超链接的排序
	 * @param id 要更新的超链接的id
	 * @param oldPos 原位置
	 * @param newPos 新位置
	 */
	public void updateLinkPos(int id,int oldPos,int newPos);
}
