package cn.damei.service.mobile.Inspector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.entity.mobile.Inspector.InspectorBalanceEntity;
import cn.damei.dao.mobile.Inspector.InspectorBalanceOfOrderDao;
import cn.damei.entity.mobile.Manager.OrderSignVo;

@Service
@Transactional(readOnly=true)
public class InspectBalanceOfOrderService {

	@Autowired
	private InspectorBalanceOfOrderDao dao;
	
public List<InspectorBalanceEntity> findBalanceDetailByOrderId(InspectorBalanceEntity vo){
	
	return dao.findBalanceDetailByOrderId(vo);
}
	
	public List<OrderSignVo> orderByInspectorId(Integer inspectId){
		
		return dao.orderByInspectorId(inspectId);
	}
}
