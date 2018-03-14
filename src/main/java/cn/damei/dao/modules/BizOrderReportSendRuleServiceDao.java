/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.entity.modules.BizOrderReportSendRuleServiceMember;

/**
 * 返单客服轮训规则客服人员DAO接口
 * @author Liwancai
 * @version 2017-05-06
 */
@MyBatisDao
public interface BizOrderReportSendRuleServiceDao extends CrudDao<BizOrderReportSendRuleServiceMember> {
	
	/**
	 * 根据规则ID删除客服人员
	 * @param sendRuleId，规则ID
	 */
	void deleteBySendRuleId(Integer sendRuleId);
	
	/**
	 * 批量插入客服人员
	 * @param list
	 */
	void batchInsert(List<BizOrderReportSendRuleServiceMember> list);
	
	/**
	 * 根据规则ID查询客服人员
	 * @param sendRuleId，规则ID
	 * @return
	 */
	List<BizOrderReportSendRuleServiceMember> selectServiceMembersBySendRuleId(Integer sendRuleId);
	
	/**
	 * 查询未过期规则相关客服人员
	 * @return
	 */
	List<Map<String,String>> selectServiceMembersForPendingRule();
	
	/**
	 * 返单分配客服查询，获取当前分配情况
	 * send_rule_id:1,max_service_index:3,service_index:
	 * @return
	 */
	Map<String,Object> selectOrderReportDistributionCustomer();
	
	/**
	 *	根据规则ID更新客服人员分配标示
	 * @param map
	 */
	void updateBySendRuleId(Integer sendRuleId);
	
	/**
	 * 根据规则id以及服务顺序更新分配信息
	 * @param map
	 * @return
	 */
	void distributionCustomer(Map<String,Object> map);
	
	/**
	 * 根据规则id以及服务顺序查询服务对象
	 * @param map
	 * @return
	 */
	BizEmployee selectCustomerServiceByMap(Map<String,Object> map);
}