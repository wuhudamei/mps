package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.EmpTaskpackageSettlement;
import cn.damei.entity.mobile.Manager.OrderTaskpackageSettlement;
import cn.damei.entity.mobile.Manager.OrderTaskpackageVo;


@MyBatisDao
public interface OrderTaskpackageSettlementDao  extends CrudDao2<OrderTaskpackageSettlement>{

	public OrderTaskpackageVo queryTaskpackageSettlement(Integer id);


	public List<EmpTaskpackageSettlement> queryTaskpackageEmpDetail(Integer groupId);
	

	public List<EmpTaskpackageSettlement> queryUpdateTaskpackageEmpDetail(Map<String, Object> map);
	

	public OrderTaskpackageSettlement queryTaskpackageSettlementByNo(String settlementNo);
	

	public OrderTaskpackageSettlement queryTaskpackageSettlementByOrderTaskpackageId(Integer orderTaskpackageId);


	public Double queryQcWorkerPublishAmountTotal(Integer orderTaskpackageId);
}