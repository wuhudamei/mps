package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.CompletedOrderDao;
import cn.damei.entity.mobile.Manager.CompletedOrder;

/**
 * 订单交底
 * 确认竣工
 * @author llp
 */
@Service
@Transactional(readOnly = true)
public class CompletedOrderService extends CrudService2<CompletedOrderDao, CompletedOrder>{
	
	@Autowired
	private CompletedOrderDao completedOrderDao;

	@Transactional(readOnly = false)
	public List<CompletedOrder> queryList(Integer itemManagerID) {
		return completedOrderDao.queryList(itemManagerID);
	}

	/**
	 * 根据订单编号修改订单状态为300
	 * @param orderstatus300Value
	 * @param orderstatus300ValueRemark
	 * @param orderID
	 * @return
	 */
	@Transactional(readOnly = false)
	public String updateByStatus(String orderstatusValue, String orderstatusRemark, String orderID) {
		return completedOrderDao.updateByStatus(orderstatusValue,orderstatusRemark,orderID) ? "0" : "3";
	}

	public CompletedOrder getByID(Integer id) {
		// TODO Auto-generated method stub
		return completedOrderDao.getByID(id);
	}
	

}
