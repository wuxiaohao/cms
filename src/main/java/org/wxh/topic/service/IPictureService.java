package org.wxh.topic.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.wxh.topic.model.Attachment;
import org.wxh.topic.model.Picture;

/**
 * 新闻图片附件业务层
 * @author wxh
 *
 */
public interface IPictureService {
	/**
	 * 添加图片
	 * @param pic 图片对象
	 * @param inputStream 输入流
	 * @throws IOException
	 */
	void add(Picture pic, InputStream inputStream)throws IOException;
	/**
	 * 删除图片
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 获取某个文章的图片
	 * @param tid
	 * @return
	 */
	public List<Picture> listByPicTopic(int id);

}
