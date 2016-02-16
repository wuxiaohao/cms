package org.wxh.index.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wxh.basic.common.GlobalResult;
import org.wxh.basic.model.Pager;
import org.wxh.basic.model.SystemContext;
import org.wxh.index.model.CmsLink;
import org.wxh.index.service.ICmsLinkService;
import org.wxh.index.dao.ICmsLinkDao;

@Service("cmsLinkService")
public class CmsLinkService implements ICmsLinkService {
	
	private static final Logger logger = Logger.getLogger(IndexPicService.class);
	
	@Autowired
	private ICmsLinkDao cmsLinkDao;
	
	public ICmsLinkDao getCmsLinkDao() {
		return cmsLinkDao;
	}
	public void setCmsLinkDao(ICmsLinkDao cmsLinkDao) {
		this.cmsLinkDao = cmsLinkDao;
	}

	@Override
	public void add(CmsLink cl) {
		cmsLinkDao.add(cl);
	}

	@Override
	public void delete(int id) {
		cmsLinkDao.delete(id);
	}

	@Override
	public void update(CmsLink cl) {
		cmsLinkDao.update(cl);
	}

	@Override
	public CmsLink load(int id) {
		return cmsLinkDao.load(id);
	}

	@Override
	public Pager<CmsLink> findByType(String type) {
		return cmsLinkDao.findByType(type);
	}

	@Override
	public List<CmsLink> listByType(String type) {
		return cmsLinkDao.listByType(type);
	}

	@Override
	public List<String> listAllType() {
		return cmsLinkDao.listAllType();
	}

	@Override
	public Map<String, Integer> getMinAndMaxPos() {
		return cmsLinkDao.getMinAndMaxPos();
	}

	@Override
	public void updatePos(int id, int oldPos, int newPos) {
		cmsLinkDao.updatePos(id, oldPos, newPos);
	}
	@Override
	public void savePic(String newName, InputStream is) throws IOException {
		String realPath = SystemContext.getRealPath();
		String path = realPath + GlobalResult.LINK_PATH + "/";//新闻图片存放的位置
		//创建临时文件存放的位置
		File fp = new File( path ); 
		if( !fp.exists() ) fp.mkdirs();
		path = path + newName;
		logger.info(path);
		FileUtils.copyInputStreamToFile(is, new File(path));
	}
	@Override
	public List<CmsLink> listAllLink() {
		return cmsLinkDao.listAllLink();
	}

}
