package org.wxh.topic.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.exception.CmsException;
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
		List<Picture> pts = pictureDao.listByPicTopic(id);//获取该组图所有图片信息
		pictureDao.deleteByPicTopic(id);  //删除该组图的所有图片
		pictureTopicDao.delete(id);  //删除组图信息
		//删除硬盘上面的图片
		for(Picture a:pts) {
			PictureService.deletePictures(a);
		}
	}

	@Override
	public void add(PictureTopic pt, int cid, User u, Integer[] pics) {
		Channel c = channelDao.load(cid);
		if(c==null||u==null)throw new CmsException("必须有用户和栏目");
		pt.setAuthor(u.getNickname());
		pt.setCname(c.getName());
		pt.setCreateDate(new Date());
		pt.setChannel(c);
		pt.setUser(u);
		pictureTopicDao.add(pt);//添加到组图信息到数据库
		addTopicPictures(pt, pics);
		
	}
	
	@Override
	public void update(PictureTopic pt, int cid, Integer[] pics) {
		Channel c = channelDao.load(cid);
		if(c==null)throw new CmsException("要更新的组图新闻必须有用户和栏目");
		pt.setCname(c.getName());
		pt.setChannel(c);
		pictureTopicDao.update(pt);
		addTopicPictures(pt, pics);
	}

	/**
	 * 关联组图信息和图片
	 * @param pt
	 * @param pics  图片id数组
	 */
	private void addTopicPictures(PictureTopic pt, Integer[] pics) {
		if(pics != null) {
			for(Integer pic:pics) {
				Picture p = pictureDao.load(pic);
				if(p == null) continue;
				p.setPictureTopic(pt);
			}
		}
	}

}
