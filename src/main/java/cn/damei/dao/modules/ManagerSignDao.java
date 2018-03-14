
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.BizBusinessSign;
import cn.damei.entity.mobile.Manager.SignDetail;
import cn.damei.entity.modules.BizPmAttendDayOrder;
import cn.damei.entity.modules.ManagerSign;


@MyBatisDao
public interface ManagerSignDao extends CrudDao2<ManagerSign> {
	public String getIsValidByOrderIdAndManagerIdAndSignDate(Map map);
	public void insertDayOrderBySignDetail(SignDetail signDetail);
	public void updateDayOrderBySignDetail(SignDetail signDetail);
	public void insertDayOrderByBizBusinessSign(BizBusinessSign sign);
	public void updateDayOrderByBizBusinessSign(BizBusinessSign sign);
	public List<BizPmAttendDayOrder> findList1(BizPmAttendDayOrder bizPmAttendDayOrder);
	public void updateIsValiddById(String id,String isValid );
	public String getMonthStatusByDayOrderId(String id);

    Boolean isApplyAttendMonth(String id);
}