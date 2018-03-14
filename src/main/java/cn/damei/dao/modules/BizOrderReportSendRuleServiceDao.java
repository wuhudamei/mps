
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizOrderReportSendRuleServiceMember;


@MyBatisDao
public interface BizOrderReportSendRuleServiceDao extends CrudDao<BizOrderReportSendRuleServiceMember> {
	

	void deleteBySendRuleId(Integer sendRuleId);
	

	void batchInsert(List<BizOrderReportSendRuleServiceMember> list);
	

	List<BizOrderReportSendRuleServiceMember> selectServiceMembersBySendRuleId(Integer sendRuleId);
	

	List<Map<String,String>> selectServiceMembersForPendingRule();
	

	Map<String,Object> selectOrderReportDistributionCustomer();
	

	void updateBySendRuleId(Integer sendRuleId);
	

	void distributionCustomer(Map<String,Object> map);
	

	BizEmployee selectCustomerServiceByMap(Map<String,Object> map);
}