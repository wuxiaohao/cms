package org.wxh.topic.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.wxh.basic.model.Pager;
import org.wxh.topic.model.Attachment;

/**
 * 文章附件业务层
 * @author wxh
 *
 */
public interface IAttachmentService {
	/**
	 * 添加附件
	 * @param a
	 * @param is
	 * @throws IOException
	 */
	public void add(Attachment a,InputStream is)throws IOException;
	/**
	 * 删除附件
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 加载附件
	 * @param id
	 * @return
	 */
	public Attachment load(int id);
	/**
	 * 获取没有被引用的附件
	 * @return
	 */
	public Pager<Attachment> findNoUseAttachment();
	/**
	 * 清空没有被引用的附件
	 */
	public void clearNoUseAttachment();
	/**
	 * 获取某个文章的附件
	 * @param tid
	 * @return
	 */
	public List<Attachment> listByTopic(int tid);
	/**
	 * 根据一个数量获取首页图片的附件信息
	 * @param num
	 * @return
	 */
	public List<Attachment> listIndexPic(int num);
	/**
	 * 获取某个栏目中的附件图片信息
	 * @param cid
	 * @return
	 */
	public Pager<Attachment> findChannelPic(int cid);
	/**
	 * 获取某篇文章的属于附件类型的附件对象
	 * @param tid
	 * @return
	 */
	public List<Attachment> listAttachByTopic(int tid);
	/**
	 * 更新是否为主页图片
	 * @param aid
	 */
	public void updateIndexPic(int aid);
	/**
	 * 更新是否是附件信息
	 * @param aid
	 */
	public void updateAttachInfo(int aid);
	/**
	 * 获取已发布的图片类型文章
	 * @return
	 */
	public Pager<Attachment> listAllPic();
	/**
	 * 获取没有被引用的附件的数量
	 * @return
	 */
	public long findNoUseAttachmentNum();
	
}
