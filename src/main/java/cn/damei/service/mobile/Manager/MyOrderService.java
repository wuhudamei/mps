package cn.damei.service.mobile.Manager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.MyOrderDao;
import cn.damei.entity.mobile.Manager.MyOrder;




@Service
@Transactional(readOnly = true)
public class MyOrderService extends CrudService2<MyOrderDao, MyOrder>{
	
	public int findCountByManagerName(Integer id) {
		return dao.findCount(id);
	}

	public int findCountByManagerNameAndOrderStatus(Integer id) {
		return dao.findBuildingCount(id);
	}
}
