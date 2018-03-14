
package cn.damei.dao.mobile.Manager;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.BizBusinessSign;


@MyBatisDao
public interface BizBusinessSignDao extends CrudDao2<BizBusinessSign> {
	

	public BizBusinessSign queryEmployeeIsCheck(Map<String, Object> map);
	public String getCheckDatetimeByOrderId(Integer orderId);
	public String getOrderIdBypPackId(Integer packId);
	public String getIsValidByOrderIdAndManagerIdAndSignDate(Map map);
	public void insertDayOrderByBizBusinessSign(BizBusinessSign sign);
	
	public void updateDayOrderByBizBusinessSign(BizBusinessSign sign);
}