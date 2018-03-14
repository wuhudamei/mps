/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderFinishProjectBill;

/**
 * 订单管理DAO接口
 * @author wyb
 * @version 2016-09-08
 */
@MyBatisDao
public interface OrderFinishProjectBillDao extends CrudDao2<OrderFinishProjectBill>{

	void insertBill(OrderFinishProjectBill projectBill);

	OrderFinishProjectBill queryListByOrderID(Integer orderID);

	OrderFinishProjectBill getByOrderID(Integer orderID);

	boolean updateByMore(String realFinishProjectDate, String orderID, Integer managerID, String updateDate);


	void deleteUselessPicsByRelatedId(String billId);
}