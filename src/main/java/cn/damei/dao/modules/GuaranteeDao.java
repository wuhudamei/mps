package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.Guarantee;

@MyBatisDao
public interface GuaranteeDao extends CrudDao2<Guarantee>{

	Guarantee findGuaranteeBySettlementId(Integer id);
}
