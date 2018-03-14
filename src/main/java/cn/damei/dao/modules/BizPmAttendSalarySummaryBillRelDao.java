/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmAttendSalarySummaryBillRel;

/**
 * 工资单批次审批DAO接口
 * @author wl
 * @version 2017-08-10
 */
@MyBatisDao
public interface BizPmAttendSalarySummaryBillRelDao extends CrudDao<BizPmAttendSalarySummaryBillRel> {

	List<BizPmAttendSalarySummaryBillRel> getSummaryBillList(
			BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel);

	void updateStatus(BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel);

	List<BizPmAttendSalarySummaryBillRel> getSummaryBill(
			BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel);

	List<BizPmAttendSalarySummaryBillRel> getSummaryBillDetail(
			BizPmAttendSalarySummaryBillRel bizPmAttendSalarySummaryBillRel);
	
}