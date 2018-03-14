
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.InstallPlanQuery;


@MyBatisDao
public interface InstallPlanQueryDao extends CrudDao<InstallPlanQuery> {
	
	
	public List<InstallPlanQuery> findDetailByOrderId(Integer orderId);
}