/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmAttendSalarySummaryBill;
import cn.damei.entity.modules.BizPmAttendSalarySummaryBillRel;

/**
 * 月度工资单批次DAO接口
 * @author wl
 * @version 2017-08-10
 */
@MyBatisDao
public interface BizPmAttendSalarySummaryBillDao extends CrudDao<BizPmAttendSalarySummaryBill> {

	void insertBillRel(BizPmAttendSalarySummaryBillRel bobo);
	
}