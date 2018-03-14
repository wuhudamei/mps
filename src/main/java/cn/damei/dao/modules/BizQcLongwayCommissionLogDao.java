/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcLongwayCommissionLog;

/**
 * 质检员远程费记录DAO接口
 * @author 汪文文
 * @version 2017-02-13
 */
@MyBatisDao
public interface BizQcLongwayCommissionLogDao extends CrudDao2<BizQcLongwayCommissionLog> {

	Integer insert1(BizQcLongwayCommissionLog bizQcLongwayCommissionLog);
	
	BizQcLongwayCommissionLog queryCommissionLogByParam(BizQcLongwayCommissionLog bizQcLongwayCommissionLog);
	
}