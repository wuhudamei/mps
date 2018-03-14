/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmGuaranteeMoneyLog;

/**
 * 质保金日志DAO接口
 * @author 汪文文
 * @version 2017-01-05
 */
@MyBatisDao
public interface BizPmGuaranteeMoneyLogDao extends CrudDao2<BizPmGuaranteeMoneyLog> {

	BizPmGuaranteeMoneyLog findByEmployeeId(Integer pmEmployeeId);

	Integer insert1(BizPmGuaranteeMoneyLog gml);
	
	BizPmGuaranteeMoneyLog findByParam(Map<String,Object> param);
	
}