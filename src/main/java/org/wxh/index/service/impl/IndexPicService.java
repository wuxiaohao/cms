package org.wxh.index.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.common.Constant;
import org.wxh.basic.model.Pager;
import org.wxh.basic.model.SystemContext;
import org.wxh.index.dao.IIndexPicDao;
import org.wxh.index.model.IndexPic;
import org.wxh.index.service.IIndexPicService;

@Service("indexPicService")
public class IndexPicService implements IIndexPicService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IIndexPicDao indexPicDao;
		
	public IIndexPicDao getIndexPicDao() {
		return indexPicDao;
	}
	public void setIndexPicDao(IIndexPicDao indexPicDao) {
		this.indexPicDao = indexPicDao;
	}

	@Override
	public void add(IndexPic indexPic) {
		indexPic.setCreateDate(new Date());
		indexPicDao.add(indexPic);
	}

	@Override
	public void update(IndexPic indexPic) {
		indexPicDao.update(indexPic);
	}

	@Override
	public void delete(int id) {
		IndexPic pic = indexPicDao.load(id);
		String rp = SystemContext.getRealPath();
		String pp = rp + Constant.UrlConstant.INDEX_PIC_PATH + pic.getNewName();
		new File(pp).delete();
		indexPicDao.delete(id);
	}

	@Override
	public void updateStatus(int id) {
		IndexPic ip = indexPicDao.load(id);
		if(ip.getStatus()==Constant.NO) ip.setStatus(Constant.YES);
		else ip.setStatus(0);
		indexPicDao.update(ip);
	}

	@Override
	public IndexPic load(int id) {
		return indexPicDao.load(id);
	}

	@Override
	public List<IndexPic> listIndexPicByNum(int num) {
		return indexPicDao.listIndexPicByNum(num);
	}

	@Override
	public Pager<IndexPic> findIndexPic() {
		return indexPicDao.findIndexPic();
	}

	@Override
	public List<String> listAllIndexPicName() {
		return indexPicDao.listAllIndexPicName();
	}

	@Override
	public void cleanNoUseIndexPic(List<String> pics) throws IOException {
		String rp = SystemContext.getRealPath();
		//首先删除临时文件夹
		File temp = new File(rp + Constant.UrlConstant.INDEX_PIC_TMP_PATH);
		FileUtils.deleteDirectory(temp);
		logger.info(rp + Constant.UrlConstant.INDEX_PIC_THU_PATH);
		//其次删除缩略图和首页图片
		for(String f:pics) {
			new File(rp + Constant.UrlConstant.INDEX_PIC_THU_PATH + f).delete();
			new File(rp + Constant.UrlConstant.INDEX_PIC_PATH + f).delete();
		}
	}

	@Override
	public Map<String, Integer> getMinAdnMaxPos() {
		return indexPicDao.getMinAdnMaxPos();
	}
	@Override
	public void updatePos(int id, int oldPos, int newPos) {
		indexPicDao.updatePos(id, oldPos, newPos);
	}
	@Override
	public void savePic(String newName, InputStream is) throws IOException {
		String realPath = SystemContext.getRealPath();
		String path = realPath + Constant.UrlConstant.FILE_PATH + File.separator;//新闻图片存放的位置
		//创建临时文件存放的位置
		File fp = new File( path ); 
		if( !fp.exists() ) fp.mkdirs();
		path = path + newName;
		logger.info(path);
		FileUtils.copyInputStreamToFile(is, new File(path));
	}

}
