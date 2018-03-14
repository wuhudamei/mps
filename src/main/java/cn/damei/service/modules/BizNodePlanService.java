
package cn.damei.service.modules;

import java.util.List;

import cn.damei.entity.modules.BizNodePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizNodePlanDao;


@Service
@Transactional(readOnly = true)
public class BizNodePlanService extends CrudService2<BizNodePlanDao, BizNodePlan> {

	@Autowired
	private BizNodePlanDao BizNodePlanDao;
	
	public BizNodePlan get(Integer id) {
		return super.get(id);
	}
	
	public List<BizNodePlan> findList(BizNodePlan bizNodePlan) {
		return super.findList(bizNodePlan);
	}
	
	public Page<BizNodePlan> findPage(Page<BizNodePlan> page, BizNodePlan bizNodePlan) {
		return super.findPage(page, bizNodePlan);
	}
	
	@Transactional(readOnly = false)
	public void save(BizNodePlan bizNodePlan) {
		super.save(bizNodePlan);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizNodePlan bizNodePlan) {
		super.delete(bizNodePlan);
	}

	public List<BizNodePlan> getByOrderIdListInIndex(List<Integer> list) {
		return BizNodePlanDao.getByOrderIdListInIndex(list);
	}


	public List<BizNodePlan> getbyOrderIDList() {

		return BizNodePlanDao.getbyOrderIDList();
	}

}