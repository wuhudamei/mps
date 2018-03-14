/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPrepareOrder;
import cn.damei.entity.modules.OrdertaskingCount;

/**
 * 预备订单表DAO接口
 * @author wyb
 * @version 2017-03-15
 */
@MyBatisDao
public interface BizPrepareOrderDao extends CrudDao2<BizPrepareOrder> {

	long findPrepareOrderCount(BizPrepareOrder temp);

	List<OrdertaskingCount> ordertaskingCount(OrdertaskingCount ordertaskingCount);

	Integer findMaterialsChoiceBillId(String orderNumber);

	void updateCadOrderId(String orderNumber, String id);

	String findAuditorId(String auditorName, String auditorPhone, int i);
	
}