
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmOwnpayLog;


@MyBatisDao
public interface BizPmOwnpayLogDao extends CrudDao2<BizPmOwnpayLog> {

	Integer insert1(BizPmOwnpayLog bizPmOwnpayLog);
	
}