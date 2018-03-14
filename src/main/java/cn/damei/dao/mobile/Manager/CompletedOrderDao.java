/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.CompletedOrder;

/**
 * 订单管理DAO接口
 * @author wyb
 * @version 2016-09-08
 */
@MyBatisDao
public interface CompletedOrderDao extends CrudDao2<CompletedOrder>{

	List<CompletedOrder> queryList(Integer itemManagerID);

	boolean updateByStatus(String orderstatusValue, String orderstatusRemark, String orderID);

	CompletedOrder getByID(Integer id);
}