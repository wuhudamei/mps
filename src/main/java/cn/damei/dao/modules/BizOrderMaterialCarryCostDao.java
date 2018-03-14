package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderMaterialCarryCost;

/**
 * 订单材料搬运运输费Dao
 * @author hyh
 *
 */
@MyBatisDao
public interface BizOrderMaterialCarryCostDao extends CrudDao2<BizOrderMaterialCarryCost>{

	public BizOrderMaterialCarryCost queryOrderMaterialCarryCostByOrderId(Integer orderId);
}
