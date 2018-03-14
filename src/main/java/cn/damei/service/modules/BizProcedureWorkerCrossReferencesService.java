
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizProcedureWorkerCrossReferences;
import cn.damei.dao.modules.BizProcedureWorkerCrossReferencesDao;


@Service
@Transactional(readOnly = true)
public class BizProcedureWorkerCrossReferencesService extends CrudService<BizProcedureWorkerCrossReferencesDao, BizProcedureWorkerCrossReferences> {

	public BizProcedureWorkerCrossReferences get(String id) {
		return super.get(id);
	}
	
	public List<BizProcedureWorkerCrossReferences> findList(BizProcedureWorkerCrossReferences bizProcedureWorkerCrossReferences) {
		return super.findList(bizProcedureWorkerCrossReferences);
	}
	
	public Page<BizProcedureWorkerCrossReferences> findPage(Page<BizProcedureWorkerCrossReferences> page, BizProcedureWorkerCrossReferences bizProcedureWorkerCrossReferences) {
		return super.findPage(page, bizProcedureWorkerCrossReferences);
	}
	
	@Transactional(readOnly = false)
	public void save(BizProcedureWorkerCrossReferences bizProcedureWorkerCrossReferences) {
		super.save(bizProcedureWorkerCrossReferences);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizProcedureWorkerCrossReferences bizProcedureWorkerCrossReferences) {
		super.delete(bizProcedureWorkerCrossReferences);
	}
	
}