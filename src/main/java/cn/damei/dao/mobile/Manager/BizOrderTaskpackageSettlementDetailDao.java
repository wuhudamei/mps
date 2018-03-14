package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageSettlementDetail;

import java.util.Map;


@MyBatisDao
public interface BizOrderTaskpackageSettlementDetailDao extends CrudDao2<BizOrderTaskpackageSettlementDetail>{


	public int updateSettlementDetail(BizOrderTaskpackageSettlementDetail detail);

	public BizOrderTaskpackageSettlementDetail querySettlementDetailByTaskId(Map<String, Object> map);
}
