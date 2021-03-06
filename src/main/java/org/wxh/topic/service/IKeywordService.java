package org.wxh.topic.service;


import java.util.List;

import org.wxh.basic.model.Pager;
import org.wxh.topic.model.Keyword;

/**
 * 关键字业务层
 * @author wxh
 *
 */
public interface IKeywordService {
	public void addOrUpdate(String name);
	/**
	 * 获取引用次数大于等于某个数的关键字
	 * @param num 
	 * @return
	 */
	public List<Keyword> getKeywordByTimes(int num);
	/**
	 * 获取引用次数最多的num个关键字
	 * @param num 关键字个数
	 * @return
	 */
	public List<Keyword> getMaxTimesKeyword(int num);
	/**
	 * 查找没有使用的关键字
	 * @return
	 */
	public Pager<Keyword> findNoUseKeyword();
	/**
	 * 清空没有使用的关键字
	 */
	public void clearNoUseKeyword();
	/**
	 * 查找正在被引用的关键字
	 * @return
	 */
	public List<Keyword> findUseKeyword();
	/**
	 * 根据某个条件从keyword表中查询关键字
	 * @param con
	 * @return
	 */
	public List<Keyword> listKeywordByCon(String con);
	/**
	 * 根据某个条件从keyword表中查询关键字
	 * @param con
	 * @return
	 */
	public List<String> listKeywordStringByCon(String con);
}
