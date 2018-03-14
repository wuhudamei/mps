package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.OrderSettlementScheduleDao;
import cn.damei.entity.mobile.Manager.OrderSettlementSchedule;


@Service
@Transactional(readOnly = false)
public class OrderSettlementScheduleService extends CrudService2<OrderSettlementScheduleDao, OrderSettlementSchedule> {

	public OrderSettlementSchedule queryMidwaySettlementScheduleByOrderId(int orderId) {
		return dao.queryMidwaySettlementScheduleByOrderId(orderId);
	}

	public OrderSettlementSchedule queryCompleteSettlementScheduleByOrderId(int orderId) {
		return dao.queryCompleteSettlementScheduleByOrderId(orderId);
	}
	
	public List<OrderSettlementSchedule> queryOrderSettlementScheduleList(OrderSettlementSchedule orderSettlementSchedule){
		return dao.queryOrderSettlementScheduleList(orderSettlementSchedule);
	}

}
