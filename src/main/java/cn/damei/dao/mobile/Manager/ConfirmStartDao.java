
package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ConfirmStartOrder;


@MyBatisDao
public interface ConfirmStartDao extends CrudDao<ConfirmStartOrder>{
	
	List<ConfirmStartOrder> queryList(Integer id);

	ConfirmStartOrder getByOrderId(Integer orderId);

	boolean updateByOrderStatusNumber(String orderStatusNumber, String orderStatusDescription, 
			String actualStartDate,String orderId);

	List<ConfirmStartOrder> queryByManagerIdList(Integer managerId);

	ConfirmStartOrder getByManagerId(Integer managerId);

	void updateOrderModified(int i, Integer id);

	
	
}