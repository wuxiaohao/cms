package org.wxh.topic.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.exception.CmsException;
import org.wxh.basic.model.Pager;
import org.wxh.topic.dao.IAttachmentDao;
import org.wxh.topic.dao.IChannelDao;
import org.wxh.topic.dao.ITopicDao;
import org.wxh.topic.model.Attachment;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.Topic;
import org.wxh.topic.service.ITopicService;
import org.wxh.user.dao.IUserDao;
import org.wxh.user.model.User;

@Service("topicService")
public class TopicService implements ITopicService {
	@Autowired
	private ITopicDao topicDao;
	@Autowired
	private IAttachmentDao attachmentDao;
	@Autowired
	private IChannelDao channelDao;
	@Autowired
	private IUserDao userDao;
	
	
	public IChannelDao getChannelDao() {
		return channelDao;
	}
	public void setChannelDao(IChannelDao channelDao) {
		this.channelDao = channelDao;
	}
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	public ITopicDao getTopicDao() {
		return topicDao;
	}
	public void setTopicDao(ITopicDao topicDao) {
		this.topicDao = topicDao;
	}

	public IAttachmentDao getAttachmentDao() {
		return attachmentDao;
	}
	public void setAttachmentDao(IAttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}
	
	private void addTopicAtts(Topic topic,Integer[] aids) {
		if(aids!=null) {
			for(Integer aid:aids) {
				Attachment a = attachmentDao.load(aid);
				if(a==null) continue;
				a.setTopic(topic);
			}
		}
	}

	@Override
	public void add(Topic topic, int cid, int uid, Integer[] aids) {
		Channel c = channelDao.load(cid);
		User u = userDao.load(uid);
		if(c==null||u==null)throw new CmsException("要添加的文章必须有用户和栏目");
		topic.setAuthor(u.getNickname());
		topic.setCname(c.getName());
		topic.setCreateDate(new Date());
		topic.setChannel(c);
		topic.setUser(u);
		topicDao.add(topic);
		addTopicAtts(topic, aids);
	}

	@Override
	public void add(Topic topic, int cid, int uid) {
		add(topic,cid,uid,null);
	}

	@Override
	public void delete(int id) {
		List<Attachment> atts = attachmentDao.listByTopic(id);  //获取所有附件信息
		attachmentDao.deleteByTopic(id); //删除附件
		topicDao.delete(id);  //删除文章
		//删除硬盘上面的文件
		for(Attachment a:atts) {
			AttachmentService.deleteAttachFiles(a);
		}
	}

	@Override
	public void update(Topic topic, int cid, Integer[] aids) {
		Channel c = channelDao.load(cid);
		if(c==null)throw new CmsException("要更新的文章必须有用户和栏目");
		topic.setCname(c.getName());
		topic.setChannel(c);
		topicDao.update(topic);
		addTopicAtts(topic, aids);
	}

	@Override
	public void update(Topic topic, int cid) {
		update(topic,cid,null);
	}

	@Override
	public Topic load(int id) {
		return topicDao.load(id);
	}

	@Override
	public Pager<Topic> find(Integer status) {
		return topicDao.find(status);
	}

	@Override
	public Pager<Topic> find(Integer uid,Integer status) {
		return topicDao.find(uid,status);
	}
	
	@Override
	public List<Topic> list(Integer cid, String title, Integer status) {
		return topicDao.list(cid, title, status);
	}
	@Override
	public List<Topic> list(Integer uid, Integer cid, String title,
			Integer status) {
		return topicDao.list(uid, cid, title, status);
	}

	@Override
	public Pager<Topic> searchTopicByKeyword(String keyword) {
		return topicDao.searchTopicByKeyword(keyword);
	}

	@Override
	public Pager<Topic> searchTopic(String con) {
		return topicDao.searchTopic(con);
	}

	@Override
	public Pager<Topic> findRecommendTopic(Integer ci) {
		return topicDao.findRecommendTopic(ci);
	}
	@Override
	public void updateStatus(int tid) {
		Topic t = topicDao.load(tid);
		if(t.getStatus()==0) t.setStatus(1);
		else t.setStatus(0);
		topicDao.update(t);
	}
	@Override
	public List<Topic> listTopicByChannelAndNumber(int cid, int num) {
		return topicDao.listTopicByChannelAndNumber(cid, num);
	}
	@Override
	public List<Topic> listTopicByChannel(int cid) {
		return topicDao.listTopicsByChannel(cid);
	}
	@Override
	public boolean isUpdateIndex(int cid) {
		return topicDao.isUpdateIndex(cid);
	}
	
	@Override
	public Topic loadLastedTopicByColumn(int cid) {
		return topicDao.loadLastedTopicByColumn(cid);
	}
}
