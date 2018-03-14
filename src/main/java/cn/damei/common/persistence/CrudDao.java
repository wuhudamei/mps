
package cn.damei.common.persistence;

import java.util.List;

import cn.damei.entity.modules.BizProjectInstallItem;


public interface CrudDao<T> extends BaseDao {


	public T get(String id);
	

	public T get(T entity);
	

	public List<T> findList(T entity);

	public List<T> findList1(T entity);
	

	public List<T> findAllList(T entity);
	

	@Deprecated
	public List<T> findAllList();
	

	public int insert(T entity);
	

	public int update(T entity);
	

	@Deprecated
	public int delete(String id);
	

	public int delete(T entity);
	public List<BizProjectInstallItem> getAllInstallItemByStoreId(String storeId);
	
}