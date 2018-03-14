package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsSortedByOrder;
import cn.damei.entity.modules.Dict;

@MyBatisDao
public interface BizMaterialsSortedByOrderDao extends CrudDao2<BizMaterialsSortedByOrder>{

	BizMaterialsSortedByOrder findByOrderId(Integer orderId);
	List<BizMaterialsSortedByOrder> findHejiByOrderId(Integer orderId);
	List<BizMaterialsSortedByOrder> findMingxiByOrderId(Integer orderId);
	List<BizMaterialsSortedByOrder> export(BizMaterialsSortedByOrder bizMaterialsSortedByOrder);
    List<Dict> findPurchaseStatus();
}
