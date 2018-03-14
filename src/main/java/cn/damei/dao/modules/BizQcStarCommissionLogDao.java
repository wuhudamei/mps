
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcStarCommissionLog;


@MyBatisDao
public interface BizQcStarCommissionLogDao extends CrudDao2<BizQcStarCommissionLog> {

	Integer insert1(BizQcStarCommissionLog bizQcStarCommissionLog);
	
}