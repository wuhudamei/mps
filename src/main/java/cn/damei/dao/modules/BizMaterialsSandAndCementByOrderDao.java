package cn.damei.dao.modules;

import java.util.List;
import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsSandAndCementByOrder;
import cn.damei.entity.modules.Dict;

@MyBatisDao
public interface BizMaterialsSandAndCementByOrderDao extends CrudDao2<BizMaterialsSandAndCementByOrder>{

	BizMaterialsSandAndCementByOrder findByOrderId(Integer orderId);
	List<BizMaterialsSandAndCementByOrder> findHejiByOrderId(Integer orderId);
	List<BizMaterialsSandAndCementByOrder> findMingxiByOrderId(Integer orderId);
	List<BizMaterialsSandAndCementByOrder> export(BizMaterialsSandAndCementByOrder BizMaterialsSandAndCementByOrder);
    List<Dict> findPurchaseStatus();
}
