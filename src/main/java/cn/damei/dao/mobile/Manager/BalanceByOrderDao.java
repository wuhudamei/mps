package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.balanceDetail;
import cn.damei.entity.mobile.Manager.PmSettlementBudget;
import cn.damei.entity.mobile.Manager.OrderSignVo;

@MyBatisDao
public interface BalanceByOrderDao {

	
	
	public List<balanceDetail> findBalanceDetailByOrderId(OrderSignVo vo);
	
	public List<PmSettlementBudget> orderByManagerId(PmSettlementBudget pmSettlementBudget);

}
