package org.wxh.topic.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.wxh.basic.dao.BaseDao;
import org.wxh.topic.dao.IPictureDao;
import org.wxh.topic.model.Attachment;
import org.wxh.topic.model.Picture;
import org.wxh.topic.model.PictureTopic;

@Repository("pictureDao")
public class PictureDao extends BaseDao<Picture> implements IPictureDao{
	private String getPictureSelect() {
		return "select new Picture(a.id,a.picName,a.picNameOld," +
				"a.size,a.suffix,a.orders,a.pictureTopic.id,a.pictureTopic.title,a.pictureTopic.publishDate,a.pictureTopic.author)";
	}
	@Override
	public List<Picture> listByPicTopic(int tid) {
		String hql = getPictureSelect()+" from Picture a where a.pictureTopic.id=?";
		return this.list(hql,tid);
	}
	@Override
	public void deleteByPicTopic(int tid) {
		String hql = "delete Picture a where a.pictureTopic.id=?";
		this.updateByHql(hql, tid);
	}
	
}
