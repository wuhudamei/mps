package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ExportBudgetCost;

/**
 * 预算成本Dao
 * Created by hyh on 2017/11/30.
 */
@MyBatisDao
public interface ExportBudgetCostDao extends CrudDao2<ExportBudgetCost> {
}
