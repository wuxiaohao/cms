package org.wxh.index.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.wxh.basic.model.Pager;
import org.wxh.index.model.IndexPic;
/**
 * 首页宣传图片的业务层
 * @author wxh
 *
 */
public interface IIndexPicService {
	/**
	 * 添加首页宣传图片
	 * @param indexPic
	 */
	public void add(IndexPic indexPic);
	/**
	 * 修改首页宣传图片
	 * @param indexPic
	 */
	public void update(IndexPic indexPic);
	/**
	 * 删除首页宣传图片
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 更新首页宣传图片的状态
	 * @param id
	 */
	public void updateStatus(int id);
	/**
	 * 加载首页宣传图片
	 * @param id
	 * @return
	 */
	public IndexPic load(int id);
	/**
	 * 根据数量来获取首页宣传图片信息
	 * @param num
	 * @return
	 */
	public List<IndexPic> listIndexPicByNum(int num);
	/**
	 * 获取首页图片信息
	 * @return
	 */
	public Pager<IndexPic> findIndexPic();
	/**
	 * 获取所有的首页图片名称
	 * @return
	 */
	public List<String> listAllIndexPicName();
	/**
	 * 清空没有被使用的首页图片
	 * @param pics
	 * @throws IOException
	 */
	public void cleanNoUseIndexPic(List<String> pics) throws IOException;
	/**
	 * 获取所有首页图片中最低位置和最高位置
	 * @return
	 */
	public Map<String,Integer> getMinAdnMaxPos();
	/**
	 * 更新首页图片的排序
	 * @param id
	 * @param oldPos
	 * @param newPos
	 */
	public void updatePos(int id, int oldPos, int newPos);
	/**
	 * 保存图片
	 * @param newName 
	 * @param inputStream
	 */
	public void savePic(String newName, InputStream inputStream) throws IOException;
}
