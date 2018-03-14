/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 考勤单
 * Service
 * @author cgh
 */
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
	
	/**
	 * 修改以生成考勤批次的status和批次id
	 * @param ids
	 * @param status
	 * @param attendBatch
	 */
	@Transactional
	public void updateStatus(String [] ids,String status,String attendBatchId){
		bizAttendBilldao.updateStatus(ids,status,attendBatchId);
	}
	
	@Transactional
	public void save(BizAttendBill t) {
		BizAttendBill bill = new BizAttendBill();
		//没有考勤批次id
		bill.setAttendBatchId(BizAttendBillConstantUtil.NO_KQPCNO);
		//获取Sequence
		String sequence = sysSequenceService.getSequence(BizAttendBillConstantUtil.KQ_NO);
		//考勤单号
		String kqNo = sequence.substring(0,2);
		//顺序码
		String No = sequence.substring(2);
		//时间
		String date = BizAttendBillConstantUtil.getDate(new Date());
		//考勤单编号
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
		//以生成考勤单
		bill.setStatus(BizAttendBillConstantUtil.ALREADY_ATTEND_SHEET);
		//状态日期时间
		bill.setStatusDatetime(new Date());
		//状态备注
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
	
	/**
	 * 批量更新batchid
	 * @param list
	 */
	@Transactional
	public void updateBatchIds(List<BizAttendBill> list){
		bizAttendBilldao.updateBatchIds(list);
	}
}