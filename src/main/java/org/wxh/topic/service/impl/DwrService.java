package org.wxh.topic.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.index.service.ICmsLinkService;
import org.wxh.index.service.IIndexPicService;
import org.wxh.index.service.IIndexService;
import org.wxh.topic.service.IAttachmentService;
import org.wxh.topic.service.IDwrService;
import org.wxh.topic.service.IPictureService;
import org.wxh.user.service.IGroupService;

@Service("dwrService")
public class DwrService implements IDwrService{
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private IIndexPicService indexPicService;
	@Autowired
	private ICmsLinkService cmsLinkService;
	@Autowired
	private IPictureService pictureService;
	@Autowired
	private IIndexService indexService;

	/**
	 * 添加GroupChannel对象
	 * @param group
	 * @param channel
	 */
	@Override
	public void addGroupChannel(int gid, int cid) {
		groupService.addGroupChannel(gid, cid);
	}
	/**
	 * 删除用户栏目
	 * @param gid
	 * @param cid
	 */
	@Override
	public void deleteGroupChannel(int gid, int cid) {
		groupService.deleteGroupChannel(gid, cid);
	}
	/**
	 * 更新是否是主页信息
	 * @param aid
	 */
	@Override
	public void updateIndexPic(int aid) {
		attachmentService.updateIndexPic(aid);
	}
	/**
	 * 更新是否是主页信息(同时刷新模板界面)
	 * @param aid
	 */
	@Override
	public void updateIndexPicFush(int aid) {
		attachmentService.updateIndexPic(aid);
		indexService.generateBody();//重新生成首页
	}

	/**
	 * 更新是否是附件信息
	 * @param aid
	 */
	@Override
	public void updateAttachInfo(int aid) {
		attachmentService.updateAttachInfo(aid);
	}
	/**
	 * 删除附件信息
	 * @param id
	 */
	@Override
	public void deleteAttach(int id) {
		attachmentService.delete(id);
	}
	/**
	 * 删除图片信息
	 */
	@Override
	public void deletePicture(int id) {
		pictureService.delete(id);
	}
	
	@Override
	public void updatePicPos(int id, int oldPos, int newPos) {
		try {
			indexPicService.updatePos(id, oldPos, newPos);
			indexService.generateBody();//重新生成首页
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void updateLinkPos(int id, int oldPos, int newPos) {
		try {
			cmsLinkService.updatePos(id, oldPos, newPos);
			indexService.generateLink();//重新生成首页
		} catch (Exception e) {
			
		}
	}

}
