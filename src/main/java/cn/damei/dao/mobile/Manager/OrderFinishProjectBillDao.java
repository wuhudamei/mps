
package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderFinishProjectBill;


@MyBatisDao
public interface OrderFinishProjectBillDao extends CrudDao2<OrderFinishProjectBill>{

	void insertBill(OrderFinishProjectBill projectBill);

	OrderFinishProjectBill queryListByOrderID(Integer orderID);

	OrderFinishProjectBill getByOrderID(Integer orderID);

	boolean updateByMore(String realFinishProjectDate, String orderID, Integer managerID, String updateDate);


	void deleteUselessPicsByRelatedId(String billId);
}