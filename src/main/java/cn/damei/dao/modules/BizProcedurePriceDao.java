
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizProcedurePrice;


@MyBatisDao
public interface BizProcedurePriceDao extends CrudDao<BizProcedurePrice> {

	BizProcedurePrice getByProcedureNo(String procedureNo,String stroeId,String contractStartDate,String procedureNo1,String stroeId1,String projectMode);
	
}