package cn.damei.service.mobile.Manager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.OrderDao;
import cn.damei.entity.modules.Order;

@Service
@Transactional(readOnly = true)
public class ManagerOrderService extends CrudService<OrderDao, Order>{
	
	
}
