/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.ConfirmStartOrder;

/**
 * 订单管理DAO接口
 * @author wyb
 * @version 2016-09-08
 */
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