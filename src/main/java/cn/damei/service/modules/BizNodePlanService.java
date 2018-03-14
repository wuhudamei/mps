/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import cn.damei.entity.modules.BizNodePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizNodePlanDao;

/**
 * 进度节点Service
 * @author llp
 * @version 2016-10-10
 */
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

	/***获取所有订单的节点**/
	public List<BizNodePlan> getbyOrderIDList() {
		// TODO Auto-generated method stub
		return BizNodePlanDao.getbyOrderIDList();
	}

}