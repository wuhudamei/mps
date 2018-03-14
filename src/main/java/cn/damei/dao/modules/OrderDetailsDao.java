package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderDetails;
import cn.damei.entity.modules.OrderDetailsInstallPlan;
import cn.damei.entity.modules.OrderDtailsEmployee;
@MyBatisDao
public interface OrderDetailsDao extends CrudDao2<OrderDetails>{

	public OrderDetails findOrderDtailsById(String id);

	public List<OrderDetailsInstallPlan> findIntallPlanByOrderId(String orderId);

	public List<OrderDtailsEmployee> findEmployeeByOrderId(String orderId);

	public String findInspector(String orderId);

}
