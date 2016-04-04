package org.wxh.topic.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.common.Constant;
import org.wxh.basic.exception.CmsException;
import org.wxh.basic.model.Pager;
import org.wxh.basic.model.SystemContext;
import org.wxh.topic.dao.IChannelDao;
import org.wxh.topic.dao.IVideoDao;
import org.wxh.topic.model.Channel;
import org.wxh.topic.model.Video;
import org.wxh.topic.service.IVideoService;
import org.wxh.user.model.User;

@Service("videoService")
public class VideoService implements IVideoService{
	private static Logger logger = LoggerFactory.getLogger(VideoService.class);
	
	@Autowired
	private IVideoDao videoDao;
	@Autowired
	private IChannelDao channelDao;
	
	@Override
	public Pager<Video> find(Integer cid, String title, Integer status) {
		return videoDao.find(cid, title, status);
	}

	@Override
	public Pager<Video> find(Integer uid, Integer cid, String title,Integer status) {
		return videoDao.find(uid, cid, title, status);
	}

	@Override
	public void updateStatus(int vid, User u) {
		 Video v = videoDao.load(vid);
		 if(v.getStatus() == Constant.NO ){
			v.setStatus(Constant.YES);
			v.setPublishDate(new Date());
			v.setAuditor(u.getNickname());
		 } else {
			v.setStatus(Constant.NO);
			v.setPublishDate(null);
			v.setAuditor(null);
		 }
		 videoDao.update(v);
	}

	@Override
	public Video load(int id) {
		return videoDao.loadVideo(id);
	}

	public Video loadCash(int id) {
		return videoDao.load(id);
	}
	
	@Override
	public void add(Video video, int cid, User u) {
		Channel c = channelDao.load(cid);
		if(c==null||u==null)throw new CmsException("必须有用户和栏目");
		if(video.getAuthor() == null || video.getAuthor() == ""){ //如果没有指定作者名称，则默认为创建者的名称
			video.setAuthor(u.getNickname());
		}
		video.setUser(u);
		video.setCname(c.getName());
		video.setCreateDate(new Date());
		video.setChannel(c);
		videoDao.add(video);
	}

	@Override
	public void delete(int id) {
		Video video = videoDao.load(id);
		videoDao.delete(id);//删除数据库的视频信息
		deleteVideo(video);//删除硬盘上面的视频
	}
	/**
	 * 删除视频和缩略图
	 * @param video
	 */
	private void deleteVideo(Video video) {
		String realPath = SystemContext.getRealPath();//获取绝对路径
		realPath +=Constant.UrlConstant.UPLOAD_VIDEO; //视频的位置
		File file = new File(realPath+video.getVideoName());
		if(file.delete()) {
			logger.info("文件[{}],删除成功！",file.getName());
		} else {
			logger.info("文件[{}],删除失败！",file.getName());
		}
		//删除缩略图
		new File(realPath+"thumbnail"+file.separator+video.getPicName()).delete();
	}
	/**
	 * 删除视频
	 * @param video
	 */
	private void deleteVideoOnly(Video video) {
		String realPath = SystemContext.getRealPath();//获取绝对路径
		realPath +=Constant.UrlConstant.UPLOAD_VIDEO; //视频的位置
		File file = new File(realPath+video.getVideoName());
		if(file.delete()) {
			logger.info("文件[{}],删除成功！",file.getName());
		} else {
			logger.info("文件[{}],删除失败！",file.getName());
		}
	}
	/**
	 * 删除缩略图
	 * @param video
	 */
	private void deletePicOnly(Video video) {
		String realPath = SystemContext.getRealPath();//获取绝对路径
		realPath +=Constant.UrlConstant.UPLOAD_VIDEO_THUM; //视频缩略图的位置
		File file = new File(realPath+video.getPicName());
		if(file.delete()) {
			logger.info("文件[{}],删除成功！",file.getName());
		} else {
			logger.info("文件[{}],删除失败！",file.getName());
		}
	}

	@Override
	public void update(Video vNew,Video vOld, int cid) {
		Channel c = channelDao.load(cid);
		if(c == null) throw new CmsException("要更新的视频新闻必须有用户和栏目");
		vNew.setCname(c.getName());
		vNew.setChannel(c);
		videoDao.update(vNew);
		if(!vNew.getVideoName().equals(vOld.getVideoName())) {
			deleteVideoOnly(vOld);//删除原来的视频
		}
		if(!vNew.getVideoName().equals(vOld.getVideoName())) {
			deletePicOnly(vOld);//删除原来的缩略图
		}
	}

	@Override
	public void addVideo(Video v, InputStream inputStream) throws IOException{
		try {
			addFile(v,inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addFile(Video v, InputStream is) throws IOException {
		String realPath = SystemContext.getRealPath();
		String path = realPath+Constant.UrlConstant.UPLOAD_VIDEO;
		File fp = new File(path);
		if(!fp.exists()) fp.mkdirs();
		path = path+v.getVideoName();
		FileUtils.copyInputStreamToFile(is, new File(path));
	}

	@Override
	public void addPic(String picName, InputStream is) throws IOException {
		String realPath = SystemContext.getRealPath();
		String path = realPath+Constant.UrlConstant.UPLOAD_VIDEO_THUM;//新闻图片存放的位置
		File fp = new File(path);
		if(!fp.exists()) fp.mkdirs();
		path = path+picName;
		FileUtils.copyInputStreamToFile(is, new File(path));
	}

	@Override
	public List<Video> listVideoByNum(int cid, int num) {
		return videoDao.listVideoByNum(cid,num);
	}

	@Override
	public Pager<Video> findVideoByCid(int cid) {
		return videoDao.findVideoByCid(cid);
	}

	@Override
	public void update(Video video) {
		videoDao.update( video );
	}

}
