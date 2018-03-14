package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsWallAndFloorByOrder;
import cn.damei.entity.modules.Dict;

@MyBatisDao
public interface BizMaterialsWallAndFloorByOrderDao extends CrudDao2<BizMaterialsWallAndFloorByOrder>{

	BizMaterialsWallAndFloorByOrder findByOrderId(Integer orderId);
	List<BizMaterialsWallAndFloorByOrder> findHejiByOrderId(Integer orderId);
	List<BizMaterialsWallAndFloorByOrder> findMingxiByOrderId(Integer orderId);
    List<Dict> findPurchaseStatus();
}
