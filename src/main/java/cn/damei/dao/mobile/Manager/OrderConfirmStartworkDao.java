
package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderConfirmStartwork;


@MyBatisDao
public interface OrderConfirmStartworkDao extends CrudDao2<OrderConfirmStartwork>{

	void insertConfirmStartwork(OrderConfirmStartwork sWork);

	Integer findByOrderId(Integer orderId);
	
}