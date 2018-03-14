
package cn.damei.service.modules;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BizAttendBillConstantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizAttendBillDao;
import cn.damei.entity.modules.BizAttendBill;
import cn.damei.common.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class BizAttendBillService extends CrudService2<BizAttendBillDao, BizAttendBill> {

	@Autowired
	private BizAttendBillDao bizAttendBilldao;
	
	@Autowired
	private SysSequenceService sysSequenceService;
	
	public List<BizAttendBill> findNoBillList(BizAttendBill bizAttendBill){
		return bizAttendBilldao.findNoBillList(bizAttendBill);
	}
	
	
	public List<BizAttendBill> findBillList(BizAttendBill bizAttendBill){
		return bizAttendBilldao.findBillList(bizAttendBill);
	}
	

	@Transactional
	public void updateStatus(String [] ids,String status,String attendBatchId){
		bizAttendBilldao.updateStatus(ids,status,attendBatchId);
	}
	
	@Transactional
	public void save(BizAttendBill t) {
		BizAttendBill bill = new BizAttendBill();

		bill.setAttendBatchId(BizAttendBillConstantUtil.NO_KQPCNO);

		String sequence = sysSequenceService.getSequence(BizAttendBillConstantUtil.KQ_NO);

		String kqNo = sequence.substring(0,2);

		String No = sequence.substring(2);

		String date = BizAttendBillConstantUtil.getDate(new Date());

		bill.setAttendBillCode(kqNo+date+No);
		bill.setAttendEmployeeId(t.getAttendEmployeeId());
		bill.setAttendEmployeeRole(t.getAttendEmployeeRole());
		
		bill.setAttendMonth(t.getAttendMonth());
		
		bill.setAttendDaysTotal(t.getAttendDaysTotal());
		bill.setAttendDaysWhole(t.getAttendDaysWhole());
		
		bill.setAttendDaysHalf(t.getAttendDaysHalf());
		bill.setBasicSalary(t.getBasicSalary());
		bill.setAttendRemarks(t.getAttendRemarks());
		
		bill.setAttendDaysMust(t.getAttendDaysMust());
		
		bill.setLeaveDaysThing(t.getLeaveDaysThing());
		bill.setLeaveDaysSick(t.getLeaveDaysSick());
		bill.setLeaveDaysAnnual(t.getLeaveDaysAnnual());
		bill.setRestDays(t.getRestDays());

		bill.setStatus(BizAttendBillConstantUtil.ALREADY_ATTEND_SHEET);

		bill.setStatusDatetime(new Date());

		bill.setStatusRemarks(t.getStatusRemarks());
		bill.setCreateBy(UserUtils.getUser());
		bill.setCreateDate(new Date());
		bizAttendBilldao.insert(bill);
	}
	
	@Transactional
	public void update(BizAttendBill bizAttendBill){
		bizAttendBilldao.update(bizAttendBill);
	}
	
	public List<BizAttendBill> findBizAttendBillListByBatchId(Integer batchId){
		
		return bizAttendBilldao.findBizAttendBillListByBatchId(batchId);
	}
	

	@Transactional
	public void updateBatchIds(List<BizAttendBill> list){
		bizAttendBilldao.updateBatchIds(list);
	}
}