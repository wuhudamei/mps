package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.OrderDetailsDao;
import cn.damei.entity.modules.OrderDetails;
import cn.damei.entity.modules.OrderDetailsInstallPlan;
import cn.damei.entity.modules.OrderDtailsEmployee;
@Service
public class OrderDetailsService extends CrudService2<OrderDetailsDao,OrderDetails>{
@Autowired
private OrderDetailsDao dao;
	public OrderDetails findOrderDtailsById(String id) {

		return dao.findOrderDtailsById(id);
	}
	public List<OrderDetailsInstallPlan> findIntallPlanByOrderId(String orderId) {

		return dao.findIntallPlanByOrderId(orderId);
	}
	public List<OrderDtailsEmployee> findEmployeeByOrderId(String orderId) {

		return dao.findEmployeeByOrderId(orderId);
	}
	public String findInspector(String orderId) {

		return dao.findInspector(orderId);
	}

}
