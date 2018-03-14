package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsSwitchByOrder;
import cn.damei.entity.modules.Dict;

@MyBatisDao
public interface BizMaterialsSwitchByOrderDao extends CrudDao2<BizMaterialsSwitchByOrder>{

	BizMaterialsSwitchByOrder findByOrderId(Integer orderId);
	List<BizMaterialsSwitchByOrder> findHejiByOrderId(Integer orderId);
	List<BizMaterialsSwitchByOrder> findMingxiByOrderId(Integer orderId);
	List<BizMaterialsSwitchByOrder> export(BizMaterialsSwitchByOrder bizMaterialsSwitchByOrder);
    List<Dict> findPurchaseStatus();
}
