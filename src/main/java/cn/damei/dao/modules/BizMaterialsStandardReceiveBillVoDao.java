package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBillVo;

@MyBatisDao
public interface BizMaterialsStandardReceiveBillVoDao extends CrudDao2<BizMaterialsStandardReceiveBillVo>{

	BizMaterialsStandardReceiveBillVo findByCode(String code);

	BizMaterialsStandardReceiveBillVo findBySettleBillId(Integer billId);

	BizMaterialsStandardReceiveBillVo findByOrderId(Integer orderId);

}
