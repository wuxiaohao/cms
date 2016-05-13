package org.wxh.topic.service.impl;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.common.Constant;
import org.wxh.basic.exception.CmsException;
import org.wxh.basic.model.Pager;
import org.wxh.topic.dao.IAttachmentDao;
import org.wxh.topic.dao.IChannelDao;
import org.wxh.topic.dao.ITopicDao;
import org.wxh.topic.model.Attachment;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.Topic;
import org.wxh.topic.model.dto.TopicDto;
import org.wxh.topic.service.ITopicService;
import org.wxh.user.dao.IUserDao;
import org.wxh.user.model.User;
import org.wxh.util.PropertiesUtil;

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
	public void add(Topic topic, int cid, User u, Integer[] aids) {
		Channel c = channelDao.load(cid);
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
	public void add(Topic topic, int cid, User u) {
		add(topic,cid,u,null);
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
	public void update(Topic topic) {
		topicDao.update(topic);
	}

	@Override
	public Topic load(int id) {
		return topicDao.load(id);
	}

	@Override
	public Pager<Topic> find(Integer cid, String title, Integer status) {
		return topicDao.find(cid, title, status);
	}

	@Override
	public Pager<Topic> find(Integer uid, Integer cid, String title,
			Integer status) {
		return topicDao.find(uid, cid, title, status);
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
	public void updateStatus(int tid,User u) {
		Topic t = topicDao.load(tid);
		if(t.getStatus()==Constant.NO) {
			t.setStatus(Constant.YES);
			t.setPublishDate(new Date());
			t.setAuditor(u.getNickname());
		}
		else {
			t.setStatus(Constant.NO);
			t.setPublishDate(null);
			t.setAuditor(null);
		}
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
		boolean check = false;
		//加载indexChannel.properties文件
		Properties prop = PropertiesUtil.getInstance().load("indexChannel");
		Enumeration en = prop.propertyNames();
		while( en.hasMoreElements() ) {
			String key = en.nextElement().toString(); //key
			String[] value = prop.getProperty(key+"").split("_"); //value
			int _cid = Integer.parseInt( value[0] ); //栏目id
			if( cid == _cid ) {
				check = true;
				break;
			}
		}
		return check;
		/*return topicDao.isUpdateIndex(cid);*/
	}
	
	@Override
	public Topic loadLastedTopicByColumn(int cid) {
		return topicDao.loadLastedTopicByColumn(cid);
	}
	@Override
	public List<Topic> listTopic() {
		return topicDao.listTopic();
	}
	@Override
	public void getPreAndNextTopic(TopicDto dto) {
		//获取上一篇文章的id
		Integer preId = topicDao.getPreTopic( dto.getCid(), dto.getPublishDate() );
		dto.setPreId(preId); 
		//获取下一篇文章的id
		Integer nextId = topicDao.getNextTopic( dto.getCid(), dto.getPublishDate() );
		dto.setNextId(nextId);
	}

}
