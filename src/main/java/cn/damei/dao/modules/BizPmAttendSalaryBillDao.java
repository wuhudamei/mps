/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmAttendSalaryBill;

/**
 * 考勤月度工资单DAO接口
 * @author wl
 * @version 2017-08-07
 */
@MyBatisDao
public interface BizPmAttendSalaryBillDao extends CrudDao<BizPmAttendSalaryBill> {

	List<BizPmAttendSalaryBill> findSalaryBillAuditingList(BizPmAttendSalaryBill bizPmAttendSalaryBill);

	void updateStatus(BizPmAttendSalaryBill bizPmAttendSalaryBill);

	List<BizPmAttendSalaryBill> findSalaryBillAuditingBatchList(BizPmAttendSalaryBill bizPmAttendSalaryBill);

	void updateStatusBySummaryId(BizPmAttendSalaryBill bizPmAttendSalaryBill);

	List<BizPmAttendSalaryBill> findSalaryBillAuditingDetail(BizPmAttendSalaryBill bizPmAttendSalaryBill);

	List<BizPmAttendSalaryBill> findSalaryBillAuditingDetailList(BizPmAttendSalaryBill bizPmAttendSalaryBill);

	BizPmAttendSalaryBill findAttendSalaryList(BizPmAttendSalaryBill bizPmAttendSalaryBill);

	BizPmAttendSalaryBill findSalaryBill(BizPmAttendSalaryBill bizPmAttendSalaryBill);

	void deleSalaryBill(BizPmAttendSalaryBill bizPmAttendSalaryBill);

	void deleteSalaryBillRel(BizPmAttendSalaryBill bizPmAttendSalaryBill);

	void deleteSalarySummary();

	void deletePmAttendSalaryBill();

	void deletePmAttendSalaryBillRel(BizPmAttendSalaryBill bizPmAttendSalaryBill);

	List<BizPmAttendSalaryBill> findSalaryBillAuditingBatchList2(BizPmAttendSalaryBill bizPmAttendSalaryBill);

    void updateStatusByIds(List<BizPmAttendSalaryBill> list);
}