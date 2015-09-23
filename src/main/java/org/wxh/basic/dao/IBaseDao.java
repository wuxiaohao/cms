package org.wxh.basic.dao;

import java.util.List;
import java.util.Map;

import org.wxh.basic.model.Pager;


/**
 * 公共的DAO处理对象，这个对象中包含了Hibernate的所有基本操作和对SQL的操作
 * @author wxh
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	/**
	 * 添加对象
	 * @param t
	 * @return
	 */
	public T add(T t);
	/**
	 * 更新对象
	 * @param t
	 */
	public void update(T t);
	/**
	 * 添加或更新对象
	 * @param t
	 */
	public void saveOrupdate(T t);
	/**
	 * 根据id删除对象
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 删除实体
	 */
	public void delete(T entity);
	
	/**
	 * 删除实体集
	 */
	public void delete(List<T> t);
	
	
	/**
	 * 根据id加载对象
	 * @param id
	 * @return
	 */
	public T load(int id);
	
	/**
	 * 不分页列表对象
	 * @param hql 查询对象的hql
	 * @param args 查询参数
	 * @return 一组不分页的列表对象
	 */
	public List<T> list(String hql, Object[] args);
	public List<T> list(String hql, Object arg);
	public List<T> list(String hql);
	/**
	 * 基于别名和查询参数来查询一组列表对象
	 * @param hql
	 * @param args 查询参数
	 * @param alias 别名对象
	 * @return
	 */
	public List<T> list(String hql, Object[] args, Map<String, Object> alias);
	/**
	 * 基于别名来查询一组列表对象
	 * @param hql
	 * @param alias 别名对象
	 * @return
	 */
	public List<T> listByAlias(String hql, Map<String, Object> alias);
	
	/**
	 * 分页列表对象
	 * @param hql 查询对象的hql
	 * @param args 查询参数
	 * @return 一组不分页的列表对象
	 */
	public Pager<T> find(String hql, Object[] args);
	public Pager<T> find(String hql, Object arg);
	public Pager<T> find(String hql);
	/**
	 * 基于别名和查询参数来查询一组列表对象（分页）
	 * @param hql
	 * @param args 查询参数
	 * @param alias 别名对象
	 * @return
	 */
	public Pager<T> find(String hql, Object[] args, Map<String, Object> alias);
	/**
	 * 基于别名来查询一组列表对象（分页）
	 * @param hql
	 * @param alias 别名对象
	 * @return
	 */
	public Pager<T> findByAlias(String hql, Map<String, Object> alias);
	
	/**
	 * 根据hql查询对象
	 */
	public Object queryObject(String hql, Object[] args);
	public Object queryObject(String hql, Object arg);
	public Object queryObject(String hql);
	/**
	 * 基于别名和查询参数的查询一个对象
	 * @param hql
	 * @param args 查询参数
	 * @param alias 别名对象
	 * @return
	 */
	public Object queryObject(String hql, Object[] args,Map<String, Object> alias);
	/**
	 * 基于别名来查询一个对象
	 * @param hql
	 * @param alias 别名对象
	 * @return
	 */
	public Object queryObjectByAlias(String hql, Map<String, Object> alias);
	
	/**
	 * 根据hql更新对象
	 */
	public void updateByHql(String hql, Object[] args);
	public void updateByHql(String hql, Object arg);
	public void updateByHql(String hql);
	
	/**
	 * 根据sql查询对象，不包含关联对s
	 */
	public <N extends Object>List<N> listBySql(String sql, Object[] args, Class<?> clz,boolean hasEntity);
	public <N extends Object>List<N> listBySql(String sql, Object arg, Class<?> clz,boolean hasEntity);
	public <N extends Object>List<N> listBySql(String sql, Class<?> clz,boolean hasEntity);
	/**
	 * 根据sql查询对象，不包含关联对象
	 * @param sql
	 * @param args 查询条件
	 * @param alias 
	 * @param clz 查询的实体对象
	 * @param hasEntity 该对象是否是hibernate所管理的实体
	 * @return
	 */
	public <N extends Object>List<N> listBySql(String sql, Object[] args,
			Map<String, Object> alias, Class<?> clz, boolean hasEntity);
	public <N extends Object>List<N> listByAliasSql(String sql, Map<String, Object> alias,
			Class<?> clz, boolean hasEntity);
	
	/**
	 * 根据sql查询对象，不包含关联对象（分页）
	 */
	public <N extends Object>Pager<N> findBySql(String sql, Object[] args, Class<?> clz,
			boolean hasEntity);
	public <N extends Object>Pager<N> findBySql(String sql, Object arg, Class<?> clz,
			boolean hasEntity);
	public <N extends Object>Pager<N> findBySql(String sql, Class<?> clz, boolean hasEntity);
	public <N extends Object>Pager<N> findBySql(String sql, Object[] args,
			Map<String, Object> alias, Class<?> clz, boolean hasEntity);
	public <N extends Object>Pager<N> findByAliasSql(String sql, Map<String, Object> alias,
			Class<?> clz, boolean hasEntity);
	
	
}

