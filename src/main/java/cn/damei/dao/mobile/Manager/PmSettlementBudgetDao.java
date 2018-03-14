package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.PmSettlementBudget;

/**
 * 项目经理订单结算金额预览Dao
 * @author hyh
 *
 */
@MyBatisDao
public interface PmSettlementBudgetDao extends CrudDao2<PmSettlementBudget>{

	public List<PmSettlementBudget> queryPmSettlementBudgetByManagerId(Integer managerId);
	
	public int checkQcBillByOrderId(int orderId);
}
