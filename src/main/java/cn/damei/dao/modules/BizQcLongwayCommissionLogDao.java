
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcLongwayCommissionLog;


@MyBatisDao
public interface BizQcLongwayCommissionLogDao extends CrudDao2<BizQcLongwayCommissionLog> {

	Integer insert1(BizQcLongwayCommissionLog bizQcLongwayCommissionLog);
	
	BizQcLongwayCommissionLog queryCommissionLogByParam(BizQcLongwayCommissionLog bizQcLongwayCommissionLog);
	
}