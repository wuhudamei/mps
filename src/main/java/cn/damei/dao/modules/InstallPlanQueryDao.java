/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.InstallPlanQuery;

/**
 * 安装项计划查询DAO接口
 * @author 梅浩
 * @version 2017-02-06
 */
@MyBatisDao
public interface InstallPlanQueryDao extends CrudDao<InstallPlanQuery> {
	
	
	public List<InstallPlanQuery> findDetailByOrderId(Integer orderId);
}