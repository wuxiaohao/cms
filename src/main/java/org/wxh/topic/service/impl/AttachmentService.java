package org.wxh.topic.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.common.Constant;
import org.wxh.basic.model.Pager;
import org.wxh.basic.model.SystemContext;
import org.wxh.topic.dao.IAttachmentDao;
import org.wxh.topic.model.Attachment;
import org.wxh.topic.service.IAttachmentService;

@Service("attachmentService")
public class AttachmentService implements IAttachmentService {
	
	private static Logger logger = LoggerFactory.getLogger(AttachmentService.class);
	
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
		realPath +=Constant.UrlConstant.UPLOAD_PATH; //附件的位置
		File file = new File(realPath+a.getNewName()); 
		//删除文件
		if(file.delete()) {
			logger.info("文件[{}],删除成功！",file.getName());
		} else {
			logger.info("文件[{}],删除失败！",file.getName());
		}
		//如果附件是图片，则还要删除该图片的缩略图
		if(a.getIsImg() == Constant.YES){
			new File(realPath + "thumbnail" + File.separator + file.getName()).delete();
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
		String path = realPath+Constant.UrlConstant.UPLOAD_PATH;//附件存放的位置
		//String thumbPath = path+"thumbnail/"; //缩略图存放的位置
		File fp = new File(path);
		//File tfp = new File(thumbPath);
//		logger.info(fp.exists());
//		logger.info(tfp.exists());
		if(!fp.exists()) fp.mkdirs();
		//if(!tfp.exists()) tfp.mkdirs();
		path = path+a.getNewName();
		//thumbPath = thumbPath+a.getNewName();
		//logger.info(path+","+thumbPath);
		FileUtils.copyInputStreamToFile(is, new File(path));
		//如果是图片
		/*if(a.getIsImg()==Constant.YES) {  
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
		}*/
		
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
		if(att.getIsIndexPic()==Constant.NO) {
			att.setIsIndexPic(Constant.YES);
		} else {
			att.setIsIndexPic(Constant.NO);
		}
		attachmentDao.update(att);
	}

	@Override
	public void updateAttachInfo(int aid) {
		Attachment att = attachmentDao.load(aid);
		if(att.getIsAttach()==Constant.NO) {
			att.setIsAttach(Constant.YES);
		} else {
			att.setIsAttach(Constant.NO);
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
	@Override
	public List<Attachment> listAttachmentByIndexPic(int num) {
		return attachmentDao.listAttachmentByIndexPic(num);
	}

}
