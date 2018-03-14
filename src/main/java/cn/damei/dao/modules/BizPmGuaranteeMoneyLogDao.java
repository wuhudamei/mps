
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmGuaranteeMoneyLog;


@MyBatisDao
public interface BizPmGuaranteeMoneyLogDao extends CrudDao2<BizPmGuaranteeMoneyLog> {

	BizPmGuaranteeMoneyLog findByEmployeeId(Integer pmEmployeeId);

	Integer insert1(BizPmGuaranteeMoneyLog gml);
	
	BizPmGuaranteeMoneyLog findByParam(Map<String,Object> param);
	
}