package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderSettlementSchedule;


@MyBatisDao
public interface OrderSettlementScheduleDao extends CrudDao2<OrderSettlementSchedule>{
	
	public OrderSettlementSchedule queryMidwaySettlementScheduleByOrderId(int orderId);
	
	public OrderSettlementSchedule queryCompleteSettlementScheduleByOrderId(int orderId);
	
	public List<OrderSettlementSchedule> queryOrderSettlementScheduleList(OrderSettlementSchedule orderSettlementSchedule);

}
