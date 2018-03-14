/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ProjectBulletin;

/**
 * 订单管理DAO接口
 * @author llp
 * @version 2016-10-09
 */
@MyBatisDao
public interface ProjectBulletinDao extends CrudDao2<ProjectBulletin> {

	List<ProjectBulletin> getByIdAndNodePlanOrderId(Integer id);

	List<ProjectBulletin> getByShowViewOrderId(Integer orderId);

	ProjectBulletin getById(Integer orderId);
	
}