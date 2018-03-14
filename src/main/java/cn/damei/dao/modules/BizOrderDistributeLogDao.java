/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderDistributeLog;

/**
 * 订单分配记录DAO接口
 * @author 汪文
 * @version 2017-03-08
 */
@MyBatisDao
public interface BizOrderDistributeLogDao extends CrudDao2<BizOrderDistributeLog> {
	
	public List<BizOrderDistributeLog> queryOrderPmDistributeLogByOrderId(Integer orderId);
}