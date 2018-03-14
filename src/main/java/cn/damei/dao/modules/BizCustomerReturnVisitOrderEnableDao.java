/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCustomerReturnVisitOrderEnable;

/**
 * 客户回访管理DAO接口
 * @author lft
 * @version 2017-08-28
 */
@MyBatisDao
public interface BizCustomerReturnVisitOrderEnableDao extends CrudDao2<BizCustomerReturnVisitOrderEnable> {
	Integer selectCount(@Param("orderId")Integer id);
}