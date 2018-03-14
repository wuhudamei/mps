package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderDisclose;


@MyBatisDao
public interface OrderDiscloseDao extends CrudDao2<OrderDisclose>{

	void insertByDisclose(OrderDisclose dis);

	Integer findByOrderId(Integer orderId);

}
