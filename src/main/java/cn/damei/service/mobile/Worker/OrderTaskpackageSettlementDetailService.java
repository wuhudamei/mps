package cn.damei.service.mobile.Worker;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Worker.OrderTaskpackageSettlementDetailDao;
import cn.damei.entity.mobile.Worker.OrderTaskpackageSettlementDetail;

@Service
@Transactional(readOnly = true)
public class OrderTaskpackageSettlementDetailService extends CrudService2<OrderTaskpackageSettlementDetailDao, OrderTaskpackageSettlementDetail>{

	public List<OrderTaskpackageSettlementDetail> findByOrderTaskpackageId(Integer orderTaskpackageId) {
		
		return dao.findByOrderTaskpackageId(orderTaskpackageId);
	}

	public OrderTaskpackageSettlementDetail findByGroupIdAndTaskPackageId(Integer id, Integer taskPackageId) {
		
		return dao.findByGroupIdAndTaskPackageId(id,taskPackageId);
	}

	@Transactional(readOnly = false)
	public void updateStatus(Integer id, String status,Date date) {
		
		dao.updateStatus(id,status,date);
	}

	public OrderTaskpackageSettlementDetail queryEntityByCondition(OrderTaskpackageSettlementDetail detail){
		return dao.queryEntityByCondition(detail);
	}

	@Transactional(readOnly = false)
	public void update(OrderTaskpackageSettlementDetail detail) {
		dao.update(detail);
	}
}
