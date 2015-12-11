package org.wxh.topic.service.impl;

import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.Thumbnails.Builder;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.common.GlobalResult;
import org.wxh.basic.model.Pager;
import org.wxh.basic.model.SystemContext;
import org.wxh.topic.dao.IAttachmentDao;
import org.wxh.topic.model.Attachment;
import org.wxh.topic.service.IAttachmentService;

@Service("attachmentService")
public class AttachmentService implements IAttachmentService {
	
	private static final Logger logger = Logger.getLogger(AttachmentService.class);
	
	@Autowired
	private IAttachmentDao attachmentDao;
	
	public IAttachmentDao getAttachmentDao() {
		return attachmentDao;
	}
	public void setAttachmentDao(IAttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}
	
	public static void deleteAttachFiles(Attachment a) {
		String realPath = SystemContext.getRealPath(); //文件的绝对路径
		realPath +=GlobalResult.UPLOAD_PATH; //附件的位置
		logger.info(realPath+a.getNewName());
		File file = new File(realPath+a.getNewName()); 
		//删除文件
		if(file.delete()) {
			logger.info(file.getName() + " 删除了!");
		} else {
			logger.info("删除失败!");
		}
		//如果附件是图片，则还要删除该图片的缩略图
		if(a.getIsImg() == 1){
			new File(realPath+"thumbnail/"+file.getName()).delete();
		}
	}
	
	@Override
	public void add(Attachment a,InputStream is) throws IOException {
		try {
			attachmentDao.add(a);
			addFile(a,is);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void addFile(Attachment a,InputStream is) throws IOException {
		//进行文件的存储
		String realPath = SystemContext.getRealPath();
		String path = realPath+GlobalResult.UPLOAD_PATH;//附件存放的位置
		String thumbPath = path+"thumbnail/"; //缩略图存放的位置
		File fp = new File(path);
		File tfp = new File(thumbPath);
//		logger.info(fp.exists());
//		logger.info(tfp.exists());
		if(!fp.exists()) fp.mkdirs();
		if(!tfp.exists()) tfp.mkdirs();
		path = path+a.getNewName();
		thumbPath = thumbPath+a.getNewName();
		logger.info(path+","+thumbPath);
		//如果是图片
		if(a.getIsImg()==1) {  
			BufferedImage oldBi = ImageIO.read(is);
			int width = oldBi.getWidth();
			Builder<BufferedImage> bf = Thumbnails.of(oldBi);
			if(width>GlobalResult.IMG_WIDTH) { 
				bf.scale((double)GlobalResult.IMG_WIDTH/(double)width);
			} else {
				bf.scale(1.0f);
			}
			bf.toFile(path);
			//缩略图的处理
			//1、将原图进行等比例压缩
			BufferedImage tbi = Thumbnails.of(oldBi)
						.scale((GlobalResult.THUMBNAIL_WIDTH*1.2)/width).asBufferedImage();
			//2、进行切割并且保持
			Thumbnails.of(tbi).scale(1.0f)
				.sourceRegion(Positions.CENTER, GlobalResult.THUMBNAIL_WIDTH, GlobalResult.THUMBNAIL_HEIGHT)
				.toFile(thumbPath);
		} else {
			FileUtils.copyInputStreamToFile(is, new File(path));
		}
		
	}

	@Override
	public void delete(int id) {
		Attachment a = attachmentDao.load(id);
		attachmentDao.delete(id);
		deleteAttachFiles(a);
	}

	@Override
	public Attachment load(int id) {
		return attachmentDao.load(id);
	}

	@Override
	public Pager<Attachment> findNoUseAttachment() {
		return attachmentDao.findNoUseAttachment();
	}

	@Override
	public void clearNoUseAttachment() {
		for(Attachment a :attachmentDao.findNoUseAttachmentByList()){ //删除附件
			deleteAttachFiles(a);
		}
		attachmentDao.clearNoUseAttachment();//删除数据库信息
	}

	@Override
	public List<Attachment> listByTopic(int tid) {
		return attachmentDao.listByTopic(tid);
	}

	@Override
	public List<Attachment> listIndexPic(int num) {
		return attachmentDao.listIndexPic(num);
	}

	@Override
	public Pager<Attachment> findChannelPic(int cid) {
		return attachmentDao.findChannelPic(cid);
	}

	@Override
	public List<Attachment> listAttachByTopic(int tid) {
		return attachmentDao.listAttachByTopic(tid);
	}

	@Override
	public void updateIndexPic(int aid) {
		Attachment att = attachmentDao.load(aid);
		if(att.getIsIndexPic()==0) {
			att.setIsIndexPic(1);
		} else {
			att.setIsIndexPic(0);
		}
		attachmentDao.update(att);
	}

	@Override
	public void updateAttachInfo(int aid) {
		Attachment att = attachmentDao.load(aid);
		if(att.getIsAttach()==0) {
			att.setIsAttach(1);
		} else {
			att.setIsAttach(0);
		}
		attachmentDao.update(att);
	}

	@Override
	public Pager<Attachment> listAllPic() {
		return attachmentDao.listAllIndexPic();
	}

	@Override
	public long findNoUseAttachmentNum() {
		return attachmentDao.findNoUseAttachmentNum();
	}

}
