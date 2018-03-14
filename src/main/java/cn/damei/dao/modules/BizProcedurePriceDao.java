/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizProcedurePrice;

/**
 * 工序价格管理DAO接口
 * @author 魏建勇
 * @version 2016-09-03
 */
@MyBatisDao
public interface BizProcedurePriceDao extends CrudDao<BizProcedurePrice> {

	BizProcedurePrice getByProcedureNo(String procedureNo,String stroeId,String contractStartDate,String procedureNo1,String stroeId1,String projectMode);
	
}