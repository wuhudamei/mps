/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.Backlog;
import cn.damei.entity.modules.BizBusinessUrgePayEntity;

/**
 * 业务催缴DAO接口
 * @author lzm
 * @version 2017-07-20
 */
@MyBatisDao
public interface BizBusinessUrgePayDao extends CrudDao<BizBusinessUrgePayEntity> {
	Backlog getOrderInfo(Integer orderId);
}