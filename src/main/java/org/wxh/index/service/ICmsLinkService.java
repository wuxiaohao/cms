package org.wxh.index.service;

import java.util.List;
import java.util.Map;

import org.wxh.basic.model.Pager;
import org.wxh.index.model.CmsLink;

/**
 * 超链接管理的业务层
 * @author wxh
 *
 */
public interface ICmsLinkService {
	/**
	 * 添加超链接
	 * @param cl
	 */
	public void add(CmsLink cl);
	/**
	 * 根据id删除超链接
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 修改超链接
	 * @param cl
	 */
	public void update(CmsLink cl);
	/**
	 * 根据id获取超链接
	 * @param id
	 * @return
	 */
	public CmsLink load(int id);
	/**
	 * 根据类型获取超链接，如果type为空就获取所有的超链接，排序方式根据pos
	 * @param type
	 * @return
	 */
	public Pager<CmsLink> findByType(String type);
	/**
	 * 获取某个类型中的所有链接，不进行分页
	 * @param type
	 * @return
	 */
	public List<CmsLink> listByType(String type);
	/**
	 * 获取超链接的所有类型
	 * @return
	 */
	public List<String> listAllType();
	/**
	 * 获取最大和最小的排序号
	 * @return
	 */
	public Map<String,Integer> getMinAndMaxPos();
	/**
	 * 更新排序
	 * @param id
	 * @param oldPos
	 * @param newPos
	 */
	public void updatePos(int id,int oldPos,int newPos);
	
}