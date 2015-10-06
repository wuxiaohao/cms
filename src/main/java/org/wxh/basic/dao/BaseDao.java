package org.wxh.basic.dao;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.wxh.basic.model.Pager;
import org.wxh.basic.model.SystemContext;

/**
 * dao基类
 * @author wxh
 *
 */
@SuppressWarnings("unchecked")
public class BaseDao<T> implements IBaseDao<T> {
	
	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * 创建一个Class的对象来获取泛型的class
	 */
	private Class<?> clz;
	
	public Class<?> getClz() {
		if(clz==null) {
			//获取泛型的Class对象
			clz = ((Class<?>)
					(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 添加对象
	 */
	@Override
	public T add(T t) {
		getSession().save(t);
		return t;
	}

	/**
	 * 修改对象
	 */
	@Override
	public void update(T t) {
		getSession().update(t);
	}
	
	@Override
	public void saveOrupdate(T t) {
		this.getSession().saveOrUpdate(t);
	}

	/**
	 * 删除对象
	 */
	public void delete(int id) {
		getSession().delete(this.load(id));
	}

	/**
	 * 根据id加载对象
	 */
	public T load(int id) {
		return (T)getSession().load(getClz(), id);
	}

//-------------不分页的列表对象-----------------------
	/**
	 * 不分页列表对象
	 * @param hql 查询对象的hql
	 * @param args 查询参数
	 * @return 一组不分页的列表对象
	 */
	@Override
	public List<T> list(String hql, Object[] args) {
		return this.list(hql, args, null);
	}
	/**
	 * 不分页列表对象
	 * @param hql 查询对象的hql
	 * @param args 查询参数
	 * @return 一组不分页的列表对象
	 */
	@Override
	public List<T> list(String hql, Object arg) {
		return this.list(hql, new Object[]{arg});
	}
	/**
	 * 不分页列表对象
	 * @param hql 查询对象的hql
	 * @return 一组不分页的列表对象
	 */
	@Override
	public List<T> list(String hql) {
		return this.list(hql,null);
	}
	/**
	 * 基于别名和查询参数来查询一组列表对象
	 * @param hql
	 * @param args 查询参数
	 * @param alias 别名对象
	 * @return
	 */
	@Override
	public List<T> list(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql); //初始化排序
		Query query = getSession().createQuery(hql);  
		setAliasParameter(query, alias);//设置别名
		setParameter(query, args);//设置参数
		return query.list();
	}
	/**
	 * 基于别名来查询一组列表对象
	 * @param hql
	 * @param alias 别名对象
	 * @return
	 */
	@Override
	public List<T> listByAlias(String hql, Map<String, Object> alias) {
		return this.list(hql, null, alias);
	}
	
//-------------分页的列表对象-----------------------
	/**
	 * 分页列表对象
	 * @param hql 查询对象的hql
	 * @param args 查询参数
	 * @return 一组分页的列表对象
	 */
	@Override
	public Pager<T> find(String hql, Object[] args) {
		return this.find(hql, args, null);
	}
	/**
	 * 分页列表对象
	 * @param hql 查询对象的hql
	 * @param args 查询参数
	 * @return 一组分页的列表对象
	 */
	@Override
	public Pager<T> find(String hql, Object arg) {
		return this.find(hql, new Object[]{arg});
	}
	/**
	 * 分页列表对象
	 * @param hql 查询对象的hql
	 * @return 一组分页的列表对象
	 */
	@Override
	public Pager<T> find(String hql) {
		return this.find(hql,null);
	}
	/**
	 * 基于别名和查询参数来查询一组列表对象（分页）
	 * @param hql
	 * @param args 查询参数
	 * @param alias 别名对象
	 * @return
	 */
	@Override
	public Pager<T> find(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql);  //初始化排序
		String cq = getCountHql(hql,true);//获取得到总记录数的hql语句
		Query cquery = getSession().createQuery(cq); 
		Query query = getSession().createQuery(hql);
		//设置别名参数
		setAliasParameter(query, alias);
		setAliasParameter(cquery, alias);
		//设置参数
		setParameter(query, args);
		setParameter(cquery, args);
		
		Pager<T> pages = new Pager<T>(); //创建分页对象
		setPagers(query,pages); //设置分页
		List<T> datas = query.list();  //获取到分页数据
		pages.setDatas(datas); //把数据传入分页对象
		long total = (Long)cquery.uniqueResult();
		pages.setTotal(total);  //把总记录数传入分页对象
		
		return pages;
	}
	/**
	 * 基于别名来查询一组列表对象（分页）
	 * @param hql
	 * @param alias 别名对象
	 * @return
	 */
	@Override
	public Pager<T> findByAlias(String hql, Map<String, Object> alias) {
		return this.find(hql,null, alias);
	}

//-------------根据hql查询对象----------------
	/**
	 * 根据hql查询对象
	 * @param hql 查询语句
	 * @param args 查询参数
	 * @return
	 */
	@Override
	public Object queryObject(String hql, Object[] args) {
		return this.queryObject(hql, args,null);
	}
	/**
	 * 根据hql查询对象
	 * @param hql 查询语句
	 * @param arg 查询参数
	 * @return
	 */
	@Override
	public Object queryObject(String hql, Object arg) {
		return this.queryObject(hql, new Object[]{arg});
	}
	/**
	 * 根据hql查询对象
	 * @param hql 查询语句
	 * @return
	 */
	@Override
	public Object queryObject(String hql) {
		return this.queryObject(hql,null);
	}
	/**
	 * 基于别名和查询参数的查询一个对象
	 * @param hql
	 * @param args 查询参数
	 * @param alias 别名对象
	 * @return
	 */
	@Override
	public Object queryObject(String hql, Object[] args,Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.uniqueResult();
	}
	/**
	 * 基于别名来查询一个对象
	 * @param hql
	 * @param alias 别名对象
	 * @return
	 */
	@Override
	public Object queryObjectByAlias(String hql, Map<String, Object> alias) {
		return this.queryObject(hql,null,alias);
	}

//-------------根据hql更新对象----------------
	/**
	 * 根据hql更新对象
	 * @param hql hql语句
	 * @param args 参数对象数组
	 */
	@Override
	public void updateByHql(String hql, Object[] args) {
		Query query = getSession().createQuery(hql);
		setParameter(query, args);
		query.executeUpdate();
	}
	/**
	 * 根据hql更新对象
	 * @param hql hql语句
	 * @param arg 参数
	 */
	@Override
	public void updateByHql(String hql, Object arg) {
		this.updateByHql(hql,new Object[]{arg});
	}
	/**
	 * 根据hql更新对象
	 * @param hql 
	 */
	@Override
	public void updateByHql(String hql) {
		this.updateByHql(hql,null);
	}

//----------根据sql查询对象，不包含关联对象---------
	@Override
	public <N extends Object>List<N> listBySql(String sql, Object[] args, Class<?> clz,boolean hasEntity) {
		return this.listBySql(sql, args, null, clz, hasEntity);
	}
	@Override
	public <N extends Object>List<N> listBySql(String sql, Object arg, Class<?> clz,
			boolean hasEntity) {
		return this.listBySql(sql, new Object[]{arg}, clz, hasEntity);
	}
	@Override
	public <N extends Object>List<N> listBySql(String sql, Class<?> clz,
			boolean hasEntity) {
		return this.listBySql(sql, null, clz, hasEntity);
	}
	
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
			Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
		sql = initSort(sql); //初始化排序
		SQLQuery sq = getSession().createSQLQuery(sql);
		setAliasParameter(sq, alias);//设置别名参数
		setParameter(sq, args);//设置参数
		if(hasEntity) { //如果是hibernate所管理的实体
			sq.addEntity(clz);
		} else 
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		return sq.list();
	}

	public <N extends Object>List<N> listByAliasSql(String sql, Map<String, Object> alias,
			Class<?> clz, boolean hasEntity) {
		return this.listBySql(sql, null, alias, clz, hasEntity);
	}

//----------根据sql查询对象，不包含关联对象（分页）---------
	public <N extends Object>Pager<N> findBySql(String sql, Object[] args, Class<?> clz,
			boolean hasEntity) {
		return this.findBySql(sql, args, null, clz, hasEntity);
	}

	public <N extends Object>Pager<N> findBySql(String sql, Object arg, Class<?> clz,
			boolean hasEntity) {
		return this.findBySql(sql, new Object[]{arg}, clz, hasEntity);
	}

	public <N extends Object>Pager<N> findBySql(String sql, Class<?> clz, boolean hasEntity) {
		return this.findBySql(sql, null, clz, hasEntity);
	}

	public <N extends Object>Pager<N> findBySql(String sql, Object[] args,
			Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
		sql = initSort(sql);//初始化排序
		String cq = getCountHql(sql,false);
		SQLQuery sq = getSession().createSQLQuery(sql);
		SQLQuery cquery = getSession().createSQLQuery(cq);
		setAliasParameter(sq, alias);
		setAliasParameter(cquery, alias);
		setParameter(sq, args);
		setParameter(cquery, args);
		Pager<N> pages = new Pager<N>();
		setPagers(sq, pages);
		if(hasEntity) {
			sq.addEntity(clz);
		} else {
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}
		List<N> datas = sq.list();
		pages.setDatas(datas);
		long total = ((BigInteger)cquery.uniqueResult()).longValue();
		pages.setTotal(total);
		return pages;
	}

	public <N extends Object>Pager<N> findByAliasSql(String sql, Map<String, Object> alias,
			Class<?> clz, boolean hasEntity) {
		return this.findBySql(sql, null, alias, clz, hasEntity);
	}

//------------------------------------------------------------------------
	
	
	/**
	 * 设置分页
	 */
	@SuppressWarnings("rawtypes")
	private void setPagers(Query query,Pager pages) {
		Integer pageSize = SystemContext.getPageSize();//获取分页大小
		Integer pageOffset = SystemContext.getPageOffset(); //获取分页的起始页
		if(pageOffset == null || pageOffset < 0) pageOffset = 0;
		if(pageSize == null || pageSize < 0) pageSize = 15;
		pages.setOffset(pageOffset);	//把起始页传入分页对象
		pages.setSize(pageSize);	//把分页大小传入分页对象
		query.setFirstResult(pageOffset).setMaxResults(pageSize); //获取数据
	}
	
	/**
	 * 设置排序
	 */
	private String initSort(String hql) {
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		if(sort != null && !"".equals(sort.trim())) {
			hql+=" order by "+sort;
			if(!"desc".equals(order)) hql+=" asc";
			else hql+=" desc";
		}
		return hql;
	}
	
	/**
	 * 设置别名
	 */
	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query query,Map<String,Object> alias) {
		if(alias != null) {
			Set<String> keys = alias.keySet();
			for(String key : keys) {
				Object val = alias.get(key);
				if(val instanceof Collection) {  //如果查询条件是列表
					query.setParameterList(key, (Collection)val);
				} else {
					query.setParameter(key, val);
				}
			}
		}
	}
	
	/**
	 * 设置参数
	 */
	private void setParameter(Query query,Object[] args) {
		if(args != null && args.length > 0) {
			int index = 0;
			for(Object arg : args) {
				query.setParameter(index++, arg);
			}
		}
	}
	
	/**
	 * 获取总记录数
	 */
	private String getCountHql(String hql,boolean isHql) {
		String end = hql.substring(hql.indexOf("from")); //得到from之后的字符串
		String c = "select count(*) "+end;
		if(isHql)
			c.replaceAll("fetch", "");  //去除fetch
		return c;
	}

	@Override
	public void delete(List<T> t) {
		for(int i=0, size=t.size(); i<size; i++) {
			this.delete(t.get(i));
		}
	}
	
	public void delete(T t) {
		this.getSession().delete(t);
	}
	
}
