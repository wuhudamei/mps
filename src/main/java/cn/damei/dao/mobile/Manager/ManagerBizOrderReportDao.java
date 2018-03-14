package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderReport;

@MyBatisDao
public interface ManagerBizOrderReportDao extends CrudDao2<BizOrderReport>{

	public Integer getPhoneCountByCustomerPhone(String customerPhone);
	
	public List<BizOrderReport> queryOrderReportByParam(BizOrderReport bizOrderReport);
}
