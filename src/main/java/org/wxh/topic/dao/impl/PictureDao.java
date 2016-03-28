package org.wxh.topic.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.wxh.basic.dao.BaseDao;
import org.wxh.topic.dao.IPictureDao;
import org.wxh.topic.model.Picture;

@Repository("pictureDao")
public class PictureDao extends BaseDao<Picture> implements IPictureDao{
	private String getPictureSelect() {
		return "select new Picture(a.id,a.picName,a.picNameOld," +
				"a.size,a.suffix,a.orders,a.pictureTopic.id,a.pictureTopic.title,a.pictureTopic.publishDate,a.pictureTopic.author)";
	}
	@Override
	public List<Picture> listByPicTopic(int tid) {
		String hql = getPictureSelect()+" from Picture a where a.pictureTopic.id=? order by orders";
		return this.list(hql,tid);
	}
	@Override
	public void deleteByPicTopic(int tid) {
		String hql = "delete Picture a where a.pictureTopic.id=?";
		this.updateByHql(hql, tid);
	}
	@Override
	public void updateNameAndSort(String[] picNameOlds, Integer[] pics) {
		String hql = "update Picture p set p.picNameOld=?,p.orders=? where p.id=?";
		for(int i = 0; i < pics.length; i++) {
			this.updateByHql(hql, new Object[]{picNameOlds[i],i+1,pics[i]});
		}
	}
	@Override
	public void updateOrder(int id, int orders) {
		List<Picture> list = this.list(
				"from Picture p where p.pictureTopic.id=? and p.orders>?", new Object[]{id,orders});
		for(Picture p : list) {
			this.updateByHql("update Picture p set p.orders=p.orders-1 where p.id=?", p.getId());
		}
	}
	
}
