package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BudgetDailyTable;


@MyBatisDao
public interface BudgetDailyTableDao extends CrudDao<BudgetDailyTable> {

	public Integer findStayCreatePkgOrdCount(BudgetDailyTable budgetDailyTable);
	
	public Integer findStayAuditPkgCount(BudgetDailyTable budgetDailyTable);
	
	public List<BudgetDailyTable> findBudgetDailyTBList(BudgetDailyTable budgetDailyTable);
	
}
