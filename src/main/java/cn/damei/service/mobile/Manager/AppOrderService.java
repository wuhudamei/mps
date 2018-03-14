
package cn.damei.service.mobile.Manager;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.AppOrderDao;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.entity.mobile.Manager.AppOrderCadfile;


@Service
@Transactional(readOnly = true)
public class AppOrderService extends CrudService2<AppOrderDao, AppOrder> {
	
	@Autowired
	private AppOrderDao appOrderDao;
	

	public List<AppOrder> findAppOrderByitemManager(AppOrder appOrder) {
		return dao.findOrderByitemManager(appOrder);
	}

	public List<String> selectState(int itemManagerId){
		return dao.selectState(itemManagerId);
	}

	public AppOrder getOrder(Integer id) {
		return dao.getOrder(id);
	}
	

	@Transactional(readOnly = false)
	public boolean updateDelayType(String typeValue, String orderId) {
		return (appOrderDao.updateDelayType(typeValue,orderId)) ? true :false;
	}
	

	public String findHouseType(AppOrder appOrder) {
		
		return dao.findHouseType(appOrder);
	}
	

	public List<AppOrderCadfile> selectCadfile(Integer id) {
		return dao.selectCadfile(id);
	}

	public AppOrder queryOrderByOrderTaskpackageId(Integer orderTaskpackageId){
		return dao.queryOrderByOrderTaskpackageId(orderTaskpackageId);
	}
	public String getOrderStatusByMaterislType(String orderId,String materislType){
	return	dao.getOrderStatusByMaterislType(orderId, materislType);
	}
}