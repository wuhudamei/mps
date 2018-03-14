
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.ArrangeCheckDoneDetailQuery;
import cn.damei.dao.modules.ArrangeCheckDoneDetailQueryDao;


@Service
@Transactional(readOnly = true)
public class ArrangeCheckDoneDetailQueryService extends CrudService<ArrangeCheckDoneDetailQueryDao, ArrangeCheckDoneDetailQuery> {

	public ArrangeCheckDoneDetailQuery get(String id) {
		return super.get(id);
	}
	
	public List<ArrangeCheckDoneDetailQuery> findList(ArrangeCheckDoneDetailQuery arrangeCheckDoneDetailQuery) {
		return super.findList(arrangeCheckDoneDetailQuery);
	}
	
	public Page<ArrangeCheckDoneDetailQuery> findPage(Page<ArrangeCheckDoneDetailQuery> page, ArrangeCheckDoneDetailQuery arrangeCheckDoneDetailQuery) {
		return super.findPage(page, arrangeCheckDoneDetailQuery);
	}
	
	@Transactional(readOnly = false)
	public void save(ArrangeCheckDoneDetailQuery arrangeCheckDoneDetailQuery) {
		super.save(arrangeCheckDoneDetailQuery);
	}
	
	@Transactional(readOnly = false)
	public void delete(ArrangeCheckDoneDetailQuery arrangeCheckDoneDetailQuery) {
		super.delete(arrangeCheckDoneDetailQuery);
	}


	public   List<String> findPqcByBillId(String qcBillId){

		return dao.findPqcByBillId(qcBillId);
	}
	
}