package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.CompletedOrderDao;
import cn.damei.entity.mobile.Manager.CompletedOrder;


@Service
@Transactional(readOnly = true)
public class CompletedOrderService extends CrudService2<CompletedOrderDao, CompletedOrder>{
	
	@Autowired
	private CompletedOrderDao completedOrderDao;

	@Transactional(readOnly = false)
	public List<CompletedOrder> queryList(Integer itemManagerID) {
		return completedOrderDao.queryList(itemManagerID);
	}


	@Transactional(readOnly = false)
	public String updateByStatus(String orderstatusValue, String orderstatusRemark, String orderID) {
		return completedOrderDao.updateByStatus(orderstatusValue,orderstatusRemark,orderID) ? "0" : "3";
	}

	public CompletedOrder getByID(Integer id) {

		return completedOrderDao.getByID(id);
	}
	

}
