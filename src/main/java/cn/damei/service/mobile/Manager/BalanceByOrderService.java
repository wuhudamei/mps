package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.dao.mobile.Manager.BalanceByOrderDao;
import cn.damei.entity.mobile.Manager.balanceDetail;
import cn.damei.entity.mobile.Manager.PmSettlementBudget;
import cn.damei.entity.mobile.Manager.OrderSignVo;

@Service
@Transactional(readOnly=true)
public class BalanceByOrderService {

	
	@Autowired
	private BalanceByOrderDao  dao;
	
	
	public List<balanceDetail> findBalanceDetailByOrderId(OrderSignVo  vo){
		
		return dao.findBalanceDetailByOrderId(vo);
	}
	public List<PmSettlementBudget> orderByManagerId(PmSettlementBudget pmSettlementBudget){
		
		return dao.orderByManagerId(pmSettlementBudget);
	}
}
