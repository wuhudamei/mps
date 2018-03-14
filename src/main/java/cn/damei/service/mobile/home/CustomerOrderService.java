package cn.damei.service.mobile.home;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.home.CustomerOrderDao;
import cn.damei.entity.mobile.home.CustomerOrder;
import cn.damei.entity.mobile.home.OrderTeam;

@Service
@Transactional(readOnly=true)
public class CustomerOrderService extends CrudService2<CustomerOrderDao, CustomerOrder>{

	public List<CustomerOrder> findOrderByPhone(String username) {
		
		return dao.findOrderByPhone(username);
	}

	public CustomerOrder findByOrderId(Integer orderId) {
		
		return dao.findByOrderId(orderId);
	}

	public List<OrderTeam> findTeamByOrderId(Integer orderId) {

		return dao.findTeamByOrderId(orderId);
	}

}
