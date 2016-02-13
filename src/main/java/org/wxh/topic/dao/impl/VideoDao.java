package org.wxh.topic.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.wxh.basic.dao.BaseDao;
import org.wxh.basic.model.Pager;
import org.wxh.topic.dao.IVideoDao;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.Video;

@Repository("videoDao")
public class VideoDao extends BaseDao<Video> implements IVideoDao{

	@Override
	public Pager<Video> find(Integer cid, String title, Integer status) {
		return find(null,cid,title,status);
	}

	@Override
	public Pager<Video> find(Integer uid, Integer cid, String title,Integer status) {
		StringBuilder hql = new StringBuilder();
		hql.append(getVideoSelect()+" from Video v where 1=1 and v.channel.type="+ChannelType.VIDEO_NEW.ordinal());
		if(status != null) {
			hql.append(" and v.status="+status);
		}
		if(title != null) {
			hql.append(" and v.title like '%"+title+"%'");
		}
		if(uid != null && uid > 0) {
			hql.append(" and v.user.id="+uid);
		}
		if(cid != null && cid > 0) {
			hql.append(" and v.channel.id="+cid);
		}
		return this.find(hql.toString());
	}
	
	private String getVideoSelect() {
		return "select new Video(v.id,v.title,v.size,v.publishDate,v.createDate,v.author,v.cname,v.auditor,v.status,v.viewCount)";
	}

	@Override
	public Video loadVideo(int id) {
		Video v = (Video) queryObject("from Video v where v.id=?", id);
		this.getSession().clear();
		return v;
	}

	@Override
	public List<Video> listVideoByNum(int cid, int num) {
		String hql = "from Video v where v.status=1 and v.channel.id=?";
		return this.getSession().createQuery(hql).
					setParameter(0, cid).setFirstResult(0).setMaxResults(num).list();
	}

}
