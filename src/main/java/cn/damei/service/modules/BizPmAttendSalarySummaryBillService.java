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
import cn.damei.entity.modules.BizPmAttendSalarySummaryBill;
import cn.damei.entity.modules.BizPmAttendSalarySummaryBillRel;
import cn.damei.dao.modules.BizPmAttendSalarySummaryBillDao;

/**
 * 月度工资单批次Service
 * @author wl
 * @version 2017-08-10
 */
@Service
@Transactional(readOnly = true)
public class BizPmAttendSalarySummaryBillService extends CrudService<BizPmAttendSalarySummaryBillDao, BizPmAttendSalarySummaryBill> {
	
	@Autowired
	private BizPmAttendSalarySummaryBillDao bizPmAttendSalarySummaryBillDao;
	
	public BizPmAttendSalarySummaryBill get(String id) {
		return super.get(id);
	}
	
	public List<BizPmAttendSalarySummaryBill> findList(BizPmAttendSalarySummaryBill bizPmAttendSalarySummaryBill) {
		return super.findList(bizPmAttendSalarySummaryBill);
	}
	
	public Page<BizPmAttendSalarySummaryBill> findPage(Page<BizPmAttendSalarySummaryBill> page, BizPmAttendSalarySummaryBill bizPmAttendSalarySummaryBill) {
		return super.findPage(page, bizPmAttendSalarySummaryBill);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmAttendSalarySummaryBill bizPmAttendSalarySummaryBill) {
		super.save(bizPmAttendSalarySummaryBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmAttendSalarySummaryBill bizPmAttendSalarySummaryBill) {
		super.delete(bizPmAttendSalarySummaryBill);
	}
	
	@Transactional(readOnly = false)
	public void insertBillRel(BizPmAttendSalarySummaryBillRel bobo) {
		bizPmAttendSalarySummaryBillDao.insertBillRel(bobo);
	}
	
}