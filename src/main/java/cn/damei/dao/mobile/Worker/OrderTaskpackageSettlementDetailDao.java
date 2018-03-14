package cn.damei.dao.mobile.Worker;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.OrderTaskpackageSettlementDetail;

@MyBatisDao
public interface OrderTaskpackageSettlementDetailDao extends CrudDao2<OrderTaskpackageSettlementDetail>{

	List<OrderTaskpackageSettlementDetail> findByOrderTaskpackageId(Integer orderTaskpackageId);

	OrderTaskpackageSettlementDetail findByGroupIdAndTaskPackageId(Integer id, Integer taskPackageId);

	void updateStatus(Integer id, String status,Date date);

	public OrderTaskpackageSettlementDetail queryEntityByCondition(OrderTaskpackageSettlementDetail detail);
}
