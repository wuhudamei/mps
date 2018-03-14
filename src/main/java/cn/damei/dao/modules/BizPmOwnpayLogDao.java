/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmOwnpayLog;

/**
 * 自主支配日志表DAO接口
 * @author wyb
 * @version 2017-02-15
 */
@MyBatisDao
public interface BizPmOwnpayLogDao extends CrudDao2<BizPmOwnpayLog> {

	Integer insert1(BizPmOwnpayLog bizPmOwnpayLog);
	
}