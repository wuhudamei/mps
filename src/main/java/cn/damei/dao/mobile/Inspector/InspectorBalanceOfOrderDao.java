package cn.damei.dao.mobile.Inspector;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.InspectorBalanceEntity;
import cn.damei.entity.mobile.Manager.OrderSignVo;

@MyBatisDao
public interface InspectorBalanceOfOrderDao {

	public List<InspectorBalanceEntity> findBalanceDetailByOrderId(InspectorBalanceEntity vo);
	
	public List<OrderSignVo> orderByInspectorId(Integer inspectId);
}
