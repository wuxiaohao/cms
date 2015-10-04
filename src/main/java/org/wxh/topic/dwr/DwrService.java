package org.wxh.topic.dwr;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.wxh.index.service.ICmsLinkService;
import org.wxh.index.service.IIndexPicService;
import org.wxh.topic.model.Topic;
import org.wxh.topic.service.IAttachmentService;
import org.wxh.topic.service.ITopicService;
import org.wxh.user.service.IGroupService;


public class DwrService implements IDwrService{
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private IIndexPicService indexPicService;
	@Autowired
	private ICmsLinkService cmsLinkService;

	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}
	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	public IGroupService getGroupService() {
		return groupService;
	}
	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}
	public IIndexPicService getIndexPicService() {
		return indexPicService;
	}
	public void setIndexPicService(IIndexPicService indexPicService) {
		this.indexPicService = indexPicService;
	}
	public ICmsLinkService getCmsLinkService() {
		return cmsLinkService;
	}
	public void setCmsLinkService(ICmsLinkService cmsLinkService) {
		this.cmsLinkService = cmsLinkService;
	}
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
	
	@Override
	public void updatePicPos(int id, int oldPos, int newPos) {
		try {
			indexPicService.updatePos(id, oldPos, newPos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void updateLinkPos(int id, int oldPos, int newPos) {
		try {
			cmsLinkService.updatePos(id, oldPos, newPos);
		} catch (Exception e) {
			
		}
	}

}
