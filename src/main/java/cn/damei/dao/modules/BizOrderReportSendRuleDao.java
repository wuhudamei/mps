/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderReportSendRule;

/**
 * 返单客服轮训规则业务处理DAO接口
 * @author Liwancai
 * @version 2017-05-06
 */
@MyBatisDao
public interface BizOrderReportSendRuleDao extends CrudDao<BizOrderReportSendRule> {
	
	/**
	 * 根据规则状态查询规则信息
	 * @return
	 */
	List<BizOrderReportSendRule> selectByStatus(String _parameter);
	
	/**
	 * 更新规则状态以及其他信息
	 * @param params
	 */
	void updateRuleStatusByMap(Map<String,Object> params);
}