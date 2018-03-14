package cn.damei.dao.mobile.Inspector;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.PqcOrderTaskpackageSettlement;


@MyBatisDao
public interface PqcOrderTaskpackageSettlementDao  extends CrudDao2<PqcOrderTaskpackageSettlement>{
	

	public PqcOrderTaskpackageSettlement querySettlementByOrderTaskpackageId(Integer orderTaskpackageId);
}