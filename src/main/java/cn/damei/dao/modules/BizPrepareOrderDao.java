
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPrepareOrder;
import cn.damei.entity.modules.OrdertaskingCount;


@MyBatisDao
public interface BizPrepareOrderDao extends CrudDao2<BizPrepareOrder> {

	long findPrepareOrderCount(BizPrepareOrder temp);

	List<OrdertaskingCount> ordertaskingCount(OrdertaskingCount ordertaskingCount);

	Integer findMaterialsChoiceBillId(String orderNumber);

	void updateCadOrderId(String orderNumber, String id);

	String findAuditorId(String auditorName, String auditorPhone, int i);
	
}