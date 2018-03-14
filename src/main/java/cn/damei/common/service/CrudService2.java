
package cn.damei.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.DataEntity2;
import cn.damei.common.persistence.Page;


@Transactional(readOnly = true)
public abstract class CrudService2<D extends CrudDao2<T>, T extends DataEntity2<T>> extends BaseService {


	@Autowired
	protected D dao;


	public T get(Integer id) {
		return dao.get(id);
	}


	public T get(T entity) {
		return dao.get(entity);
	}


	public List<T> findList(T entity) {
		return dao.findList(entity);
	}


	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}


	@Transactional(readOnly = false)
	public void save(T entity) {
		if (null==entity.getId()) {
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
