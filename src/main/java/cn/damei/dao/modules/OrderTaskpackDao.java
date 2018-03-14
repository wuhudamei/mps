
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderTaskpack;
import cn.damei.entity.modules.Dict;


@MyBatisDao
public interface OrderTaskpackDao extends CrudDao<OrderTaskpack> {

	OrderTaskpack getByOrderId(Integer orderId);

	public boolean updateByOrderStatusAndTaskpackageStatus(String orderStatusNumber, String orderStatusNumberRemark,
			String orderTaskPackStatus,String orderId);

	void updateManager(Integer orderId, String managerName, Integer managerId);

	boolean updateTotal(Double sum, String taskpackageID, Double laborSum, Double auxiliarySum);

	boolean updateIsCreateTaskpackage(String orderTaskPackStatus, String orderId);

	public List<Dict> getOrderStatus(Integer number);
	
	public Integer getStayOrdCount(OrderTaskpack orderTaskpack);

	List<OrderTaskpack> myFindList(OrderTaskpack orderTaskpack);
}