package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BudgetDailyTableDao;
import cn.damei.entity.modules.BudgetDailyTable;


@Service
@Transactional(readOnly = true)
public class BudgetDailyTableService extends CrudService<BudgetDailyTableDao, BudgetDailyTable> {

	@Autowired
	private BudgetDailyTableDao budgetDailyTableDao;
	
	public Integer findStayCreatePkgOrdCount(BudgetDailyTable budgetDailyTable){
		return budgetDailyTableDao.findStayCreatePkgOrdCount(budgetDailyTable);
	}
	
	public Integer findStayAuditPkgCount(BudgetDailyTable budgetDailyTable){
		return budgetDailyTableDao.findStayAuditPkgCount(budgetDailyTable);
	}
	
	public List<BudgetDailyTable> findBudgetDailyTBList(BudgetDailyTable budgetDailyTable){
		return budgetDailyTableDao.findBudgetDailyTBList(budgetDailyTable);
	}
}
