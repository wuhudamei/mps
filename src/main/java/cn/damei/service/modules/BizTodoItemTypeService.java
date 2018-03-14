
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizTodoItemType;
import cn.damei.dao.modules.BizTodoItemTypeDao;


@Service
@Transactional(readOnly = true)
public class BizTodoItemTypeService extends CrudService<BizTodoItemTypeDao, BizTodoItemType> {

	public BizTodoItemType get(String id) {
		return super.get(id);
	}
	
	public List<BizTodoItemType> findList(BizTodoItemType bizTodoItemType) {
		return super.findList(bizTodoItemType);
	}
	
	public Page<BizTodoItemType> findPage(Page<BizTodoItemType> page, BizTodoItemType bizTodoItemType) {
		return super.findPage(page, bizTodoItemType);
	}
	
	@Transactional(readOnly = false)
	public void save(BizTodoItemType bizTodoItemType) {
		super.save(bizTodoItemType);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizTodoItemType bizTodoItemType) {
		super.delete(bizTodoItemType);
	}


	public    List<Map<String,String>> findIdByBusinessType(String relatedBusinessType){

		return dao.findIdByBusinessType(relatedBusinessType);
	}

	public List<Map<String,String>> findRelatedBusinessTypeByStoreIdProjectMode(String storeId, String projectMode){

		return dao.findRelatedBusinessTypeByStoreIdProjectMode(storeId,projectMode);
	}
	
}