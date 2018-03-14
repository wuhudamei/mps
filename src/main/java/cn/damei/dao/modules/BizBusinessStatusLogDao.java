
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizBusinessStatusLog;


@MyBatisDao
public interface BizBusinessStatusLogDao extends CrudDao2<BizBusinessStatusLog> {


	List<BizBusinessStatusLog> findInstallStatusLog(BizBusinessStatusLog bizBusinessStatusLog);
	
	BizBusinessStatusLog queryOrderPmSettleLog(Map<String,Object> param);

	List<BizBusinessStatusLog> findListByVarchar(BizBusinessStatusLog log);

	List<BizBusinessStatusLog> findMyList(BizBusinessStatusLog log);


	Integer findFiveTimeApplyCount(BizBusinessStatusLog bizBusinessStatusLog);
}