
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmStarCommissionLog;


@MyBatisDao
public interface BizPmStarCommissionLogDao extends CrudDao2<BizPmStarCommissionLog> {

	Integer insert1(BizPmStarCommissionLog bizPmStarCommissionLog);
	
}