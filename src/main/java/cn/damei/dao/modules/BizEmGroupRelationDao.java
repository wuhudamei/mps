/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizEmgrouprelation;

/**
 * 工人组管理DAO接口
 * 
 * @author qhy
 * @version 2016-09-01
 */
@MyBatisDao
public interface BizEmGroupRelationDao extends CrudDao<BizEmgrouprelation> {
	// 任务包，预留
	// public List<BizEmployeegroupVO>findGroupList();
	// 保存工人组关联关系表
	public void insertEmpGroupRelation(BizEmgrouprelation bizEmployeegroup);

	public void deleteRelation(Integer id);

	public void deleteEmgrouprelationByGroupId(String groupId);

}