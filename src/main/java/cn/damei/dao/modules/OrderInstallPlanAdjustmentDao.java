
package cn.damei.dao.modules;


import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderInstallPlanAdjustment;


@MyBatisDao
public interface OrderInstallPlanAdjustmentDao extends CrudDao2<OrderInstallPlanAdjustment> {


	List<OrderInstallPlanAdjustment> findInstallList(OrderInstallPlanAdjustment orderInstallPlanAdjustment);


	List<OrderInstallPlanAdjustment> findChecksizeList(OrderInstallPlanAdjustment orderInstallPlanAdjustment);


	
}