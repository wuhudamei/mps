
package cn.damei.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.DataEntity;
import cn.damei.common.persistence.Page;


@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {


	@Autowired
	protected D dao;


	public T get(String id) {
		return dao.get(id);
	}


	public T get(T entity) {
		return dao.get(entity);
	}


	public List<T> findList(T entity) {
		return dao.findList(entity);
	}

	public List<T> findList1(T entity) {
		return dao.findList1(entity);
	}


	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}
	
	public Page<T> findPage1(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList1(entity));
		return page;
	}


	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getIsNewRecord()) {
			entity.preInsert();
			dao.insert(entity);
		} else {
			entity.preUpdate();
			dao.update(entity);
		}
	}


	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}

}
