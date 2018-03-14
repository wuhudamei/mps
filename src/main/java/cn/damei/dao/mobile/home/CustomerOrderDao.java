package cn.damei.dao.mobile.home;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.home.CustomerOrder;
import cn.damei.entity.mobile.home.OrderTeam;

@MyBatisDao
public interface CustomerOrderDao extends CrudDao2<CustomerOrder>{

	List<CustomerOrder> findOrderByPhone(String username);

	CustomerOrder findByOrderId(Integer orderId);

	List<OrderTeam> findTeamByOrderId(Integer orderId);

}
