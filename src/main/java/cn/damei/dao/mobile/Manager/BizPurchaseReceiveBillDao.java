package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.BizPurchaseReceiveBill;
import cn.damei.entity.mobile.Manager.BizPurchaseReceiveBillVo;

@MyBatisDao
public interface BizPurchaseReceiveBillDao extends CrudDao2<BizPurchaseReceiveBill>{

	BizPurchaseReceiveBill findByCode(String code);

	List<BizPurchaseReceiveBillVo> queryReceiveBill(Integer employeeId ,String type);

	BizPurchaseReceiveBill queryById(Integer id);

	/**
	 * 根据采购单ID查询最新的一条收货记录
	 * @param purchaseId
	 * @return
	 */
	BizPurchaseReceiveBillVo findNewReceiveBill(Integer purchaseId);

}
