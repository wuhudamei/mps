
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderReportSendRule;


@MyBatisDao
public interface BizOrderReportSendRuleDao extends CrudDao<BizOrderReportSendRule> {
	

	List<BizOrderReportSendRule> selectByStatus(String _parameter);
	

	void updateRuleStatusByMap(Map<String,Object> params);
}