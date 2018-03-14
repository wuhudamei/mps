
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmAttendSalarySummaryBillRel;


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