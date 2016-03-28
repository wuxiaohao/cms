package org.wxh.topic.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.common.Constant;
import org.wxh.basic.model.SystemContext;
import org.wxh.topic.dao.IPictureDao;
import org.wxh.topic.model.Picture;
import org.wxh.topic.service.IPictureService;

@Service("pictureService")
public class PictureService implements IPictureService{

	private static Logger logger = LoggerFactory.getLogger(PictureService.class);
	
	@Autowired
	private IPictureDao pictureDao;

	public static void deletePictures(Picture a) {
		String realPath = SystemContext.getRealPath();//获取绝对路径
		realPath +=Constant.UrlConstant.UPLOAD_PICTURE; //图片的位置
		File file = new File(realPath+a.getPicName()); 
		//删除文件
		if(file.delete()) {
			logger.info("文件[{}],删除成功！",file.getName());
		} else {
			logger.info("文件[{}],删除失败！",file.getName());
		}
		//删除该图片的缩略图
		new File(realPath+"thumbnail"+File.separator+file.getName()).delete();
	}

	@Override
	public void add(Picture pic, InputStream inputStream) throws IOException{
		try {
			pictureDao.add(pic);
			addFile(pic,inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	private void addFile(Picture pic,InputStream is) throws IOException {
		//进行文件的存储
		String realPath = SystemContext.getRealPath();
		String path = realPath+Constant.UrlConstant.UPLOAD_PICTURE;//新闻图片存放的位置
		String thumbPath = path+"thumbnail"+File.separator; //缩略图存放的位置
		File fp = new File(path);
		File tfp = new File(thumbPath);
//		logger.info(fp.exists());
//		logger.info(tfp.exists());
		if(!fp.exists()) fp.mkdirs();
		if(!tfp.exists()) tfp.mkdirs();
		path = path+pic.getPicName();
		thumbPath = thumbPath+pic.getPicName();
		logger.info(path+","+thumbPath);
		//
		BufferedImage oldBi = ImageIO.read(is);
		int width = oldBi.getWidth();
		Builder<BufferedImage> bf = Thumbnails.of(oldBi);
		if(width>Constant.IMG_WIDTH) { 
			bf.scale((double)Constant.IMG_WIDTH/(double)width);
		} else {
			bf.scale(1.0f);
		}
		bf.toFile(path);
		//缩略图的处理
		//1、将原图进行等比例压缩
		BufferedImage tbi = Thumbnails.of(oldBi)
					.scale((Constant.THUMBNAIL_WIDTH*1.2)/width).asBufferedImage();
		//2、进行切割并且保持
		Thumbnails.of(tbi).scale(1.0f)
			.sourceRegion(Positions.CENTER, Constant.THUMBNAIL_WIDTH, Constant.THUMBNAIL_HEIGHT)
			.toFile(thumbPath);
	}

	@Override
	public void delete(int id) {
		Picture pic = pictureDao.load(id);
		pictureDao.delete(id);
		if(pic.getOrders() != 0) {
			//重新生成排序
			pictureDao.updateOrder(pic.getPictureTopic().getId(),pic.getOrders());
		}
		deletePictures(pic);
	}

	@Override
	public List<Picture> listByPicTopic(int id) {
		return pictureDao.listByPicTopic(id);
	}

	@Override
	public void updateNameAndSort(String[] picNameOlds, Integer[] pics) {
		pictureDao.updateNameAndSort(picNameOlds,pics);
	}

}
