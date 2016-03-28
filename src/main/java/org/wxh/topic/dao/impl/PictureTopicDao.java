package org.wxh.topic.dao.impl;

import org.springframework.stereotype.Repository;
import org.wxh.basic.dao.BaseDao;
import org.wxh.basic.model.Pager;
import org.wxh.topic.dao.IPictureTopicDao;
import org.wxh.topic.model.ChannelType;
import org.wxh.topic.model.PictureTopic;
import org.wxh.topic.model.dto.PictureDto;

@Repository("pictureTopicDao")
public class PictureTopicDao extends BaseDao<PictureTopic> implements IPictureTopicDao{

	@Override
	public Pager<PictureTopic> find(Integer cid, String title, Integer status) {
		return find(null,cid,title,status);
	}

	@Override
	public Pager<PictureTopic> find(Integer uid, Integer cid, String title,
			Integer status) {
		String hql = getPictureTopicSelect()+" from PictureTopic t where 1=1 and t.channel.type="+ChannelType.IMG_NEW.ordinal();
		if(status!=null) {
			hql+=" and t.status="+status;
		}
		if(title!=null&&!title.equals("")) {
			hql+=" and t.title like '%"+title+"%'";
		}
 		if(uid!=null&&uid>0) {
			hql+=" and t.user.id="+uid;
		}
		if(cid!=null&&cid>0) {
			hql+=" and t.channel.id="+cid;
		}
		return this.find(hql);
	}
	
	private String getPictureTopicSelect() {
		return "select new PictureTopic(t.id,t.title,t.explain,t.publishDate,t.createDate,t.author,t.status,t.recommend,t.cname,t.auditor,t.pictureId)";
	}

	@Override
	public Pager<PictureDto> findPicTopByCid(int cid) {
		String sql = "select pt.pt_id as id,pt.pt_title as title,pt.pt_view_count as viewCount,"
				+ "DATE_FORMAT(pt.pt_publish_date,'%Y-%m-%d') as publishDate,p.p_pic_name as picName "
				+ "from t_picture_topic pt JOIN t_picture p ON pt.pt_pictureId=p.p_id "
				+ "where pt.pt_status=1 and pt.pt_channel=? ORDER BY pt.pt_publish_date desc";
		return this.findBySql(sql, cid, PictureDto.class, false);
	}
}
