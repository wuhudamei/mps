/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcStarCommissionLog;

/**
 * 质检员星级提成记录DAO接口
 * @author 汪文文
 * @version 2017-02-13
 */
@MyBatisDao
public interface BizQcStarCommissionLogDao extends CrudDao2<BizQcStarCommissionLog> {

	Integer insert1(BizQcStarCommissionLog bizQcStarCommissionLog);
	
}