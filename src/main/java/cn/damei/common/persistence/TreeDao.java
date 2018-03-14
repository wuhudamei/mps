
package cn.damei.common.persistence;

import java.util.List;


public interface TreeDao<T extends TreeEntity<T>> extends CrudDao<T> {


	public List<T> findByParentIdsLike(T entity);


	public int updateParentIds(T entity);
	
}