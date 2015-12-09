package org.wxh.topic.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.model.Pager;
import org.wxh.topic.dao.IChannelDao;
import org.wxh.topic.dao.IPictureDao;
import org.wxh.topic.dao.IPictureTopicDao;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.Picture;
import org.wxh.topic.model.PictureTopic;
import org.wxh.topic.model.Topic;
import org.wxh.topic.service.IPictureTopicService;
import org.wxh.user.dao.IUserDao;
import org.wxh.user.model.User;

@Service("pictureTopicService")
public class PictureTopicService implements IPictureTopicService{
	
	@Autowired
	private IPictureTopicDao pictureTopicDao;
	@Autowired
	private IPictureDao pictureDao;
	@Autowired
	private IChannelDao channelDao;
	@Autowired
	private IUserDao userDao;

	@Override
	public Pager<PictureTopic> find(Integer cid, String title, Integer status) {
		return pictureTopicDao.find(cid, title, status);
	}

	@Override
	public Pager<PictureTopic> find(Integer uid, Integer cid, String title,
			Integer status) {
		return pictureTopicDao.find(uid, cid, title, status);
	}

	@Override
	public void updateStatus(int tid, User u) {
		PictureTopic t = pictureTopicDao.load(tid);
		if(t.getStatus()==0) {
			t.setStatus(1);
			t.setPublishDate(new Date());
			t.setAuditor(u.getNickname());
		}
		else {
			t.setStatus(0);
			t.setPublishDate(null);
			t.setAuditor(null);
		}
		pictureTopicDao.update(t);
	}

	@Override
	public PictureTopic load(int id) {
		return pictureTopicDao.load(id);
	}

	@Override
	public void delete(int id) {
		List<Picture> pts = pictureDao.listByPicTopic(id);//获取所有附件信息
		pictureDao.delete(id);  //删除图片信息
		pictureTopicDao.delete(id);  //删除新闻图片信息
		//删除硬盘上面的文件
		for(Picture a:pts) {
			PictureService.deletePictures(a);
		}
	}

}
