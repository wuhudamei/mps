/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmStarCommissionLog;

/**
 * biz_pm_star_commission_logDAO接口
 * @author 汪文文
 * @version 2017-02-14
 */
@MyBatisDao
public interface BizPmStarCommissionLogDao extends CrudDao2<BizPmStarCommissionLog> {

	Integer insert1(BizPmStarCommissionLog bizPmStarCommissionLog);
	
}