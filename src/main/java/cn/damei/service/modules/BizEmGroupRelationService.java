/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizEmGroupRelationDao;
import cn.damei.entity.modules.BizEmgrouprelation;

/**
 * 工人组管理Service
 * 
 * @author qhy
 * @version 2016-09-01
 */
@Service
public class BizEmGroupRelationService extends CrudService<BizEmGroupRelationDao, BizEmgrouprelation> {

	@Resource
	private BizEmGroupRelationDao bizEmGroupRelationDao;

	@Transactional(readOnly = false)
	public void InsertEmpGroupRelations(List<BizEmgrouprelation> bizEmgrouprelation, String groupId) {

		for (BizEmgrouprelation emp : bizEmgrouprelation) {
			emp.setGroupId(groupId);// q
			bizEmGroupRelationDao.insertEmpGroupRelation(emp);
		}
	}

	@Transactional(readOnly = false)
	public void deleteRelation(Integer id) {
		bizEmGroupRelationDao.deleteRelation(id);
	}

	/**
	 * 根据工人组ID删除工人组工人关系 deleteRelationByGroupId:(这里用一句话描述这个方法的作用). <br/>
	 * 
	 * @author Administrator
	 * @param groupId
	 * @since JDK 1.6
	 */
	@Transactional(readOnly = false)
	public void deleteEmgrouprelationByGroupId(String groupId) {
		bizEmGroupRelationDao.deleteEmgrouprelationByGroupId(groupId);
	}

}