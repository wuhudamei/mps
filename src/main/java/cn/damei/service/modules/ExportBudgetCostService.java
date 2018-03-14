package cn.damei.service.modules;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.ExportBudgetCostDao;
import cn.damei.entity.modules.ExportBudgetCost;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 预算成本Service
 * Created by hyh on 2017/11/30.
 */
@Service
@Transactional(readOnly = true)
public class ExportBudgetCostService extends CrudService2<ExportBudgetCostDao, ExportBudgetCost> {
}
