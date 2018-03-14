/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.PurchaseReceiveBill;

/**
 * 收货单DAO接口
 * @author www
 * @version 2016-11-10
 */
@MyBatisDao
public interface PurchaseReceiveBillDao extends CrudDao2<PurchaseReceiveBill> {
	
}