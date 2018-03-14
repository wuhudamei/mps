/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizPmAttendSalarySummaryBillRel;
import cn.damei.dao.modules.BizPmAttendSalarySummaryBillRelDao;

/**
 * 工资单批次审批Service
 * @author wl
 * @version 2017-08-10
 */
@Service
@Transactional(readOnly = true)
public class BizPmAttendSalarySummaryBillRelService extends CrudService<BizPmAttendSalarySummaryBillRelDao, BizPmAttendSalarySummaryBillRel> {
	
	@Autowired
	private BizPmAttendSalarySummaryBillRelDao bizPmAttendSalarySummaryBillRelDao;
	
	public BizPmAttendSalarySummaryBillRel get(String id) {
		return super.get(id);
	}
	
	public List<BizPmAttendSalarySummaryBillRel> findList(BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel) {
		return super.findList(bizPmAttendSalarySummaryBillRel);
	}
	
	public Page<BizPmAttendSalarySummaryBillRel> findPage(Page<BizPmAttendSalarySummaryBillRel> page, BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel) {
		return super.findPage(page, bizPmAttendSalarySummaryBillRel);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel) {
		super.save(bizPmAttendSalarySummaryBillRel);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel) {
		super.delete(bizPmAttendSalarySummaryBillRel);
	}

	public Page<BizPmAttendSalarySummaryBillRel> getSummaryBillList(Page<BizPmAttendSalarySummaryBillRel> page, BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel) {
		bizPmAttendSalarySummaryBillRel.setPage(page);
		return page.setList(bizPmAttendSalarySummaryBillRelDao.getSummaryBillList(bizPmAttendSalarySummaryBillRel));
	}
	@Transactional(readOnly = false)
	public void updateStatus(BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel) {
		bizPmAttendSalarySummaryBillRelDao.updateStatus(bizPmAttendSalarySummaryBillRel);
	}

	public Page<BizPmAttendSalarySummaryBillRel> getSummaryBill(Page<BizPmAttendSalarySummaryBillRel> page,
			BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel) {
		bizPmAttendSalarySummaryBillRel.setPage(page);
		return page.setList(bizPmAttendSalarySummaryBillRelDao.getSummaryBill(bizPmAttendSalarySummaryBillRel));
	}

	public Page<BizPmAttendSalarySummaryBillRel> getSummaryBillDetail(Page<BizPmAttendSalarySummaryBillRel> page,
			BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel) {
		bizPmAttendSalarySummaryBillRel.setPage(page);
		return page.setList(bizPmAttendSalarySummaryBillRelDao.getSummaryBillDetail(bizPmAttendSalarySummaryBillRel));
	}
	
}