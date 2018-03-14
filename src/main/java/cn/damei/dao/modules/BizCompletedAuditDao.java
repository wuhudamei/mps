package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCompletedAudit;

/**
 *	订单
 *	biz_order
 *	确认竣工
 */
@MyBatisDao
public interface BizCompletedAuditDao extends CrudDao2<BizCompletedAudit>{

	boolean updateOrderStatus(String orderstatus, String orderstatusRemark, Integer id);
	
	public int checkOrderTaskpackByOrderId(int orderId);

}