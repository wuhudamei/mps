
package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizPmAttendSalaryBill;
import cn.damei.dao.modules.BizPmAttendSalaryBillDao;


@Service
@Transactional(readOnly = true)
public class BizPmAttendSalaryBillService extends CrudService<BizPmAttendSalaryBillDao, BizPmAttendSalaryBill> {
	
	@Autowired
	private BizPmAttendSalaryBillDao bizPmAttendSalaryBillDao;
	
	public BizPmAttendSalaryBill get(String id) {
		return super.get(id);
	}
	
	public List<BizPmAttendSalaryBill> findList(BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		return super.findList(bizPmAttendSalaryBill);
	}
	
	public Page<BizPmAttendSalaryBill> findPage(Page<BizPmAttendSalaryBill> page, BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		return super.findPage(page, bizPmAttendSalaryBill);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		super.save(bizPmAttendSalaryBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		super.delete(bizPmAttendSalaryBill);
	}

	public List<BizPmAttendSalaryBill> findSalaryBillAuditingList(BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		return bizPmAttendSalaryBillDao.findSalaryBillAuditingList(bizPmAttendSalaryBill);
	}
	
	@Transactional(readOnly = false)
	public void updateStatus(BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		bizPmAttendSalaryBillDao.updateStatus(bizPmAttendSalaryBill);
	}

	public List<BizPmAttendSalaryBill> findSalaryBillAuditingBatchList(Page<BizPmAttendSalaryBill> page,
			BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		return bizPmAttendSalaryBillDao.findSalaryBillAuditingBatchList(bizPmAttendSalaryBill);
	}
	@Transactional(readOnly = false)
	public void updateStatusBySummaryId(BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		bizPmAttendSalaryBillDao.updateStatusBySummaryId(bizPmAttendSalaryBill);
	}

	public Page<BizPmAttendSalaryBill> findSalaryBillAuditingDetail(Page<BizPmAttendSalaryBill> page,
			BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		bizPmAttendSalaryBill.setPage(page);
		page.setList(bizPmAttendSalaryBillDao.findSalaryBillAuditingDetail(bizPmAttendSalaryBill));
		return page;
	}

	public Page<BizPmAttendSalaryBill> findSalaryBillAuditingDetailList(Page<BizPmAttendSalaryBill> page,
			BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		bizPmAttendSalaryBill.setPage(page);
		page.setList(bizPmAttendSalaryBillDao.findSalaryBillAuditingDetailList(bizPmAttendSalaryBill));
		return page;
	}

	public BizPmAttendSalaryBill findAttendSalaryList(BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		return bizPmAttendSalaryBillDao.findAttendSalaryList(bizPmAttendSalaryBill);
	}

	public BizPmAttendSalaryBill findSalaryBill(BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		return bizPmAttendSalaryBillDao.findSalaryBill(bizPmAttendSalaryBill);
	}
	@Transactional(readOnly = false)
	public void deleSalaryBill(BizPmAttendSalaryBill bizPmAttendSalaryBill) {

		bizPmAttendSalaryBillDao.deleSalaryBill(bizPmAttendSalaryBill);
	}
	@Transactional(readOnly = false)
	public void deleteSalaryBillRel(BizPmAttendSalaryBill bizPmAttendSalaryBill) {

		bizPmAttendSalaryBillDao.deleteSalaryBillRel(bizPmAttendSalaryBill);
	}

	public void deleteSalarySummary() {
		bizPmAttendSalaryBillDao.deleteSalarySummary();
	}
	
	@Transactional(readOnly = false)
	public void deletePmAttendSalaryBill(BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		bizPmAttendSalaryBillDao.deletePmAttendSalaryBill();
	}
	@Transactional(readOnly = false)
	public void deletePmAttendSalaryBillRel(BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		bizPmAttendSalaryBillDao.deletePmAttendSalaryBillRel(bizPmAttendSalaryBill);
	}

	public List<BizPmAttendSalaryBill> findSalaryBillAuditingBatchList2(Page<BizPmAttendSalaryBill> page,
			BizPmAttendSalaryBill bizPmAttendSalaryBill) {

		return bizPmAttendSalaryBillDao.findSalaryBillAuditingBatchList2(bizPmAttendSalaryBill);
	}


	@Transactional(readOnly = false)
	public void updateStatusByIds(BizPmAttendSalaryBill bizPmAttendSalaryBill) {
		String[] ids = bizPmAttendSalaryBill.getSalaryBillIds().split(",");
		List<BizPmAttendSalaryBill> list = new ArrayList<BizPmAttendSalaryBill>();
		for (String id : ids) {
			BizPmAttendSalaryBill bill = new BizPmAttendSalaryBill();
			bill.setSalaryBillId(id);
			list.add(bill);
		}
		bizPmAttendSalaryBillDao.updateStatusByIds(list);
	}
}