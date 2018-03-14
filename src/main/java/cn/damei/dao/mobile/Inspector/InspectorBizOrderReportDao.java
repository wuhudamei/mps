package cn.damei.dao.mobile.Inspector;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderReport;

/**
 * 质检返单上报Dao
 * @author hyh
 *
 */
@MyBatisDao
public interface InspectorBizOrderReportDao extends CrudDao2<BizOrderReport>{

	public Integer getBizOrderReportByCustomerPhone(String customerPhone);

	public List<BizOrderReport> queryOrderReportByParam(BizOrderReport bizOrderReport);
}
