
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmAttendSalarySummaryBill;
import cn.damei.entity.modules.BizPmAttendSalarySummaryBillRel;


@MyBatisDao
public interface BizPmAttendSalarySummaryBillDao extends CrudDao<BizPmAttendSalarySummaryBill> {

	void insertBillRel(BizPmAttendSalarySummaryBillRel bobo);
	
}