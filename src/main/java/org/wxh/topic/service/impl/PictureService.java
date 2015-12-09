package org.wxh.topic.service.impl;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.wxh.basic.common.GlobalResult;
import org.wxh.basic.model.SystemContext;
import org.wxh.topic.model.Picture;
import org.wxh.topic.service.IPictureService;

@Service("pictureService")
public class PictureService implements IPictureService{

	private static final Logger logger = Logger.getLogger(AttachmentService.class);

	public static void deletePictures(Picture a) {
		String realPath = SystemContext.getRealPath();//获取绝对路径
		realPath +=GlobalResult.UPLOAD_PICTURE; //图片的位置
		logger.info(realPath+a.getPicName());
		File file = new File(realPath+a.getPicName()); 
		//删除文件
		if(file.delete()) {
			logger.info(file.getName() + " 删除了!");
		} else {
			logger.info("删除失败!");
		}
	}
}
